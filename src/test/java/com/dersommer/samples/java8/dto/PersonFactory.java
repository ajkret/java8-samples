package com.dersommer.samples.java8.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonFactory {
    private static String name[] = { "John", "Bruce", "William", "Peter", "Harry", "Paul", "George" };
    private static String surname[] = { "Doe", "Smith", "Carter", "Griffin", "Simpson", "Turner", "Stewart", "Nisson" };

    public static List<Person> generateList(int elements) {
        Random rnd = new Random();
        // A fancy way to new Person()
        Supplier<Person> factory = Person::new;

        // MapToObj will generate a stream with the elements you passed
        return IntStream.range(0, elements)
            .mapToObj(i -> {

                // A fancy way to new Person()
                Person p = factory.get();

                // Random data
                p.setName(name[rnd.nextInt(name.length)] + " " + surname[rnd.nextInt(surname.length)]);
                p.setRank(rnd.nextInt(100));
                p.setBirth(LocalDate.now()
                    .withYear(1950 + rnd.nextInt(60))
                    .withMonth(1 + rnd.nextInt(12))
                    .withDayOfMonth(1 + rnd.nextInt(27)));

                return p;
            })
            .collect(Collectors.toList());
    }
}
