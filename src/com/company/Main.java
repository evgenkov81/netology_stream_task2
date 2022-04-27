package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> underage = persons.stream().filter(element -> element.getAge() < 18);
        System.out.println("Underage: " + underage.count());


        List<String> conscripts = persons.stream()
                .filter(element -> element.getAge() > 17 && element.getAge() < 28)
                .map(element -> element.getFamily())
                .collect(Collectors.toList());
        conscripts.forEach(System.out::println);
        System.out.println();

       List<Person> workable= persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
               .filter(person -> (person.getSex().equals(Sex.WOMAN)&& person.getAge()>17&& person.getAge()<61)||
                       (person.getSex().equals(Sex.MAN)&& person.getAge()>17&& person.getAge()<66))
               .sorted(Comparator.comparing(Person::getFamily))
               .collect(Collectors.toList());
       workable.forEach(System.out::println);
    }
}

