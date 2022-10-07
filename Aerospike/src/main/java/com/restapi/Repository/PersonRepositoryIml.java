package com.restapi.Repository;

import com.restapi.Configuration.aeroMapperConfig;
import com.restapi.Email.EmailDetails;
import com.restapi.Model.Person;
import com.restapi.Service.EmailService;
import jakarta.inject.Inject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.List;
import java.util.Properties;

public class PersonRepositoryIml implements PersonRepository {
    @Inject
    aeroMapperConfig mapper;
    @Inject
    EmailService emailService;

    static final Object BOOTSTRAP_SERVERS = "localhost:9092";
    static final String TOPIC = "person";

    @Override

    public String addPerson(Person person) {
       mapper.getMapper().save(person);
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Person Data Added"+" "+person);
        kafkaProducer.send(record);
        kafkaProducer.close();
        EmailService.sendEmail(new EmailDetails("Person Information Alert !!!", "Congratulations, Person Info added "+person.getName()+", Your Person Id is "+person.getId(), person.getEmail()));
        return "Person saved successfully..!="+person.getId();
    }
    @Override
    public List<Person> getAllPerson(){
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Get All Person Data"+" "+Person.class);
        kafkaProducer.send(record);
        kafkaProducer.close();
       return mapper.getMapper().scan(Person.class);

        //EmailService.sendEmail(new EmailDetails("Person Information Alert !!!", "Congratulations, Person Info updated "+Person.class.getName()+", Your Person Id is "+Person.class.getField(getAllPerson().), person.getEmail()));
    }
@Override
    public Person findById(int id) {
        return mapper.getMapper().read(Person.class, id);
    }
    @Override
    public String updatePerson(Person person, int id) {
        Person p = mapper.getMapper().read(Person.class,id);
           // p.setId(person.getId());
            p.setName(person.getName());
            p.setEmail(person.getEmail());
            p.setSal(person.getSal());


            mapper.getMapper().save(p);
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Person Data Updated"+" "+person);
        kafkaProducer.send(record);
        kafkaProducer.close();
        EmailService.sendEmail(new EmailDetails("Person Information Alert !!!", "Congratulations, Person Info updated "+person.getName()+", Your Person Id is "+person.getId(), person.getEmail()));
            return "Person Updated..!="+p.getId();
    }
    @Override
    public String deleteById(int id) {
        mapper.getMapper().delete(Person.class,id);
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Person Data Deleted"+" "+Person.class);
        kafkaProducer.send(record);
        kafkaProducer.close();
        //EmailService.sendEmail(new EmailDetails("Person Information Alert !!!", "Congratulations, Person Info added "+Person.class.getName()+"Person deleted Id"+Person.class,id));
        return "Person Deleted By Id..!="+id;

    }
}
