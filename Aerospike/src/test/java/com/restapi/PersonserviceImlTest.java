package com.restapi;

import com.restapi.Model.Person;
import com.restapi.Repository.PersonRepositoryIml;
import com.restapi.Service.PersonService;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class PersonserviceImlTest {
    @Inject
    private PersonRepositoryIml employeeRepositoryIml;

    @Inject
    private PersonService employeeService;

    @MockBean(PersonRepositoryIml.class)
    PersonRepositoryIml employeeRepositoryIml() {
        return mock(PersonRepositoryIml.class);
    }



    @Test
    public void getAllPerson(){
        when(employeeRepositoryIml.getAllPerson()).thenReturn(Stream.of(new Person(),
                new Person(),
                new Person()).collect(Collectors.toList()));

        Assertions.assertEquals(3,employeeService.getAllPerson().size());
    }
    @Test
    public void addPerson() {
        //arrange
        Person person = new Person(12,"hrishi","hrishikesh22@gmail.com", 2345);

        when(employeeService.addPerson(person)).thenReturn("Person saved successfully..!=");

        String result = employeeService.addPerson(person);

        assertEquals(result, "Person saved successfully..!=");
    }
    @Test
    public void updatePerson(){

    Person person = new Person(12,"hrishi","hrishikesh22@gmail.com",2345);
    person.setName("hrishi");
    Person newEmp= new Person(14,"hrishhh","Hrishikesh22@gmail.com",12345);

    when(employeeService.updatePerson(person,12)).thenReturn("Person Updated..!=");

    String result = employeeService.updatePerson(person,12);

    assertEquals(result,"Person Updated..!=" );
    }
    @Test
    public void deleteById(){
        Person person = new Person(12,"hrishi","hrishikesh22@gmail.com",1234);
        person.setId(12);

        when(employeeService.deleteById(12)).thenReturn("Person Deleted By Id..!=");

        String result = employeeService.deleteById(12);

        assertEquals(result, "Person Deleted By Id..!=");
    }
}

