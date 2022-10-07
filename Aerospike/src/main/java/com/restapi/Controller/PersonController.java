package com.restapi.Controller;

import com.restapi.Model.Person;
import com.restapi.Service.PersonService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/Person")
public class PersonController {

    @Inject
    PersonService employeeService;


    @Post(value = "/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(@Body Person person){
         return employeeService.addPerson(person);
    }
    @Get(value = "/GetallEmployee")
    public List<Person> getAllPerson(){
        return employeeService.getAllPerson();
    }
    @Get(value = "/getemployee/{id}")
    public Person getPersonById(@PathVariable("id") int id){
        return employeeService.getPersonById(id);

    }
    //@Transactional
    @Put(value = "/update/{id}")
    public String updatePerson(@Body Person person ,@PathVariable("id") int id ){
       return employeeService.updatePerson(person, id);

    }
    @Delete(value = "/delete/{id}")
    public String deleteById(int id){
        return employeeService.deleteById(id);
    }
}
