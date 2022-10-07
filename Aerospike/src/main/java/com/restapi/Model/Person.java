package com.restapi.Model;

import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;

//import javax.persistence.Id;


@AerospikeRecord(namespace= "test",set="Person")
public class Person {
    @AerospikeKey

    private int id;

    private String name;

    private String email;
    private int sal;

public Person(){
    
}

    public Person(int id, String name, String email, int sal) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sal = sal;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sal=" + sal +
                '}';
    }
}
