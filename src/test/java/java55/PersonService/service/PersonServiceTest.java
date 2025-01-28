package java55.PersonService.service;

import java55.PersonService.person.dao.PersonRepository;
import java55.PersonService.person.dto.AddressDto;
import java55.PersonService.person.dto.EmployeeDto;
import java55.PersonService.person.model.Address;
import java55.PersonService.person.model.Employee;
import java55.PersonService.person.service.PersonService;
import java55.PersonService.person.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import java.util.concurrent.*;

public class PersonServiceTest {

//    @GetMapping("/salary/{min}/{max}")
//    public Iterable<EmployeeDto> findEmployeeBySalary ()



    private final PersonRepository personRepository = Mockito.mock(PersonRepository.class);
    private final PersonService personService = new PersonServiceImpl(new ModelMapper(), personRepository);

    @Test
    void testFindEmployeeBySalary() throws InterruptedException, ExecutionException{
        // Arrange
        Collection<Employee> mockEmployees = List.of(
                new Employee(1000, "Sarah", LocalDate.of(2000, 11, 23),
                        new Address("Rehovot", "Herzel", 2),
                        "Motorola", 4000),
                new Employee(2000, "John", LocalDate.of(2000, 11, 23),
                        new Address("Rehovot", "Herzel", 2),
                        "Motorola", 4000)
        );



        when(personRepository.findBySalaryBetween(3000, 5000)).thenReturn(mockEmployees);

        // ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int numberOfThreads = 10;
        List<Callable<Iterable<EmployeeDto>>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> personService.findEmployeeBySalary(3000, 5000));
        }

        List<Future<Iterable<EmployeeDto>>> results = executorService.invokeAll(tasks);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        // Assert
        for (Future<Iterable<EmployeeDto>> future : results) {
            Iterable<EmployeeDto> result = future.get();
            assertNotNull(result);
            assertEquals(2, ((List<EmployeeDto>) result).size());
        }

        verify(personRepository, times(numberOfThreads)).findBySalaryBetween(3000, 5000);
    }


    //    @GetMapping("/children")
    //    public Iterable<ChildDto> findAllChildren ()

}
