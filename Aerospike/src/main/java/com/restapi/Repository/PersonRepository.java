package com.restapi.Repository;

import com.restapi.Model.Person;

import java.util.List;


public interface PersonRepository {
 public String addPerson(Person person);


 public List<Person> getAllPerson();

 public Person findById(int id);
 //public Person UpdatePerson(Person person, int id);

 public String updatePerson(Person person, int id);
 public String deleteById(int id);
}


