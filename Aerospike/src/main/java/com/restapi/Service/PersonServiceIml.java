package com.restapi.Service;

import com.restapi.Model.Person;
import com.restapi.Repository.PersonRepositoryIml;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PersonServiceIml implements PersonService {
    @Inject
    PersonRepositoryIml employeeRepository;
@Inject
EmailService emailRepository;
    @Override
    public String addPerson(Person person) {
        return employeeRepository.addPerson(person);


    }
   public List<Person> getAllPerson(){
        return employeeRepository.getAllPerson();
   }

    @Override
    public Person getPersonById(int id) {
        return employeeRepository.findById(id);
        //return get
    }

    @Override
    public String updatePerson(Person person, int id) {
        return employeeRepository.updatePerson(person, id);
    }
    public String deleteById(int id){
     return employeeRepository.deleteById(id);
    }

}
