package com.restapi;

import com.restapi.Controller.PersonController;
import com.restapi.Model.Person;
import com.restapi.Repository.PersonRepository;
import com.restapi.Repository.PersonRepositoryIml;
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
public class PersonControllerImlTest {
    @Inject
    private PersonRepositoryIml personRepositoryIml;

    @Inject
    private PersonController personController;

    @MockBean(PersonRepositoryIml.class)
    PersonRepositoryIml personRepositoryIml() {
        return mock(PersonRepositoryIml.class);
    }



    @Test
    public void getAllPerson(){
        when(personRepositoryIml.getAllPerson()).thenReturn(Stream.of(new Person("hrash", "hrishikesh222@gmail.com", 23145),
                new Person("hrash", "hrishikesh222@gmail.com", 23145),
                new Person("hrash", "hrishikesh222@gmail.com", 23145)).collect(Collectors.toList()));

        Assertions.assertEquals(3,personController.getAllPerson().size());
    }
    @Test
    public void addPerson() {
        //arrange
        Person person = new Person(12,"hrishi","hrishikesh22@gmail.com", 2345);

        when(personController.addPerson(person)).thenReturn("Person saved successfully..!=");

        String result = personController.addPerson(person);

        assertEquals(result, "Person saved successfully..!=");
    }
    @Test
    public void updatePerson(){

        Person person = new Person(12,"hrishi","hrishikesh22@gmail.com",2345);
        person.setName("hrishi");
        Person newEmp= new Person("hrash","hrishikesh222@gmail.com",23145);

        when(personController.updatePerson(person,12)).thenReturn("Person Updated..!=");

        String result = personController.updatePerson(person,12);

        assertEquals(result,"Person Updated..!=" );
    }
    @Test
    public void deleteById(){
        Person person = new Person(12,"hrishi","hrishikesh22@gmail.com",1234);
        person.setId(12);

        when(personController.deleteById(12)).thenReturn("Person Deleted By Id..!=");

        String result = personController.deleteById(12);

        assertEquals(result, "Person Deleted By Id..!=");
    }
}
