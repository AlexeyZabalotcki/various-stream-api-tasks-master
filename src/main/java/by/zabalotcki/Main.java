package by.zabalotcki;

import by.zabalotcki.model.*;
import by.zabalotcki.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal ->
                        animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal ->
                        animal.getOrigin().equals("Japanese"))
                .map(animal -> {
                    animal.setBread(animal.getBread().toUpperCase());
                    return animal;
                })
                .filter(animal -> animal.getGender().equals("Female")).map(Animal::getBread)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal ->
                        animal.getAge() > 30 && animal.getOrigin().startsWith("A"))
                .map(Animal::getOrigin)
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(animal ->
                        animal.getGender().equals("Female"))
                .count();
        System.out.println(count);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .filter(animal ->
                        animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal ->
                        animal.getOrigin().equals("Hungarian"));
        System.out.println(result);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .allMatch(animal ->
                        animal.getGender().equals("Male") && animal.getGender().equals("Female"));
        System.out.println(result);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .anyMatch(animal ->
                        animal.getOrigin().equals("Oceania"));
        System.out.println(result);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        Optional<Animal> max = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge));
        System.out.println(max.get().getAge());

    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int size = animals.stream()
                .map(animal ->
                        animal.getBread().chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.toList()))
                .min(Comparator.comparing(List::size)).get().size();
        System.out.println(size);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int sum = animals.stream()
                .map(Animal::getAge)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        Double averageAge = animals.stream()
                .filter(animal ->
                        animal.getOrigin().equals("Indonesian"))
                .map(Animal::getAge)
                .mapToDouble(Integer::doubleValue)
                .average()
                .getAsDouble();
        System.out.println(averageAge);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person ->
                        person.getGender().equals("Male"))
                .filter(person ->
                        person.getDateOfBirth().isBefore(LocalDate.now().minus(Period.ofYears(18))) &&
                                person.getDateOfBirth().isAfter(LocalDate.now().minus(Period.ofYears(27))))
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        //        Продолжить...
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }
}