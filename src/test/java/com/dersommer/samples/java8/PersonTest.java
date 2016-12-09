package com.dersommer.samples.java8;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.dersommer.samples.java8.dto.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonTest {

    @Test
    public void test001_PersonSerialization() {
        Person person = new Person();

        person.setBirth(LocalDate.now());
        person.setName("John Doe");
        person.setRank(1);

        ObjectMapper mapper = new ObjectMapper();

        try {
            String result = mapper.writeValueAsString(person);

            Assert.assertNotNull(result);

        } catch (JsonProcessingException e) {
            Assert.fail();
        }
    }

    @Test
    public void test002_ValidDeSerialization() {

        String obj = "{\"name\":\"John Doe\",\"birth\":[2016,12,9],\"rank\":1}";

        ObjectMapper mapper = new ObjectMapper();

        try {
            Person p = mapper.readValue(obj, Person.class);

            Assert.assertNotNull(p);

        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Test
    public void test003_InvalidDeSerialization() {

        String obj = "{\"name\":\"John Doe\",\"birth\":[2016,12,9],\"rank\":1, \"invalid\": 0}";

        ObjectMapper mapper = new ObjectMapper();

        try {
            Person p = mapper.readValue(obj, Person.class);

            Assert.assertNotNull(p);

        } catch (IOException e) {
            Assert.fail();
        }
    }

}
