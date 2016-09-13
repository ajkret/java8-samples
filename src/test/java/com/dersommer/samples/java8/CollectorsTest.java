package com.dersommer.samples.java8;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class CollectorsTest extends TestCase {

    @Test
    // Join strings
    public void test001_Concatenation() {
        List<String> names = Arrays.asList("John", "Ringo", "Paul", "George");

        String joined = names.stream()
            .collect(Collectors.joining(","));
        Assert.assertEquals("John,Ringo,Paul,George", joined);
    }

    @Test
    // Filter by predicate
    public void test002_Filter() {
        List<String> names = Arrays.asList("John", "Ringo", "Paul", "George");

        String joined = names.stream()
            .filter(Pattern.compile("P.*")
                .asPredicate())
            .collect(Collectors.joining());
        Assert.assertEquals("Paul", joined);
    }

    @Test
    public void test003_NullArray() {
        String[] members = null;
        try {
            Stream.of(members).forEach(System.out::println);
            Assert.fail();
        } catch(Exception e) {
        }
    }

}