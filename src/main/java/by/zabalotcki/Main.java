package by.zabalotcki;

import by.zabalotcki.model.*;
import by.zabalotcki.util.Util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.*;
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

        List<Person> crossBuildingPeople = houses.stream()
                .map(House::getPersonList)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<Person> peopleFromHospital = houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .map(House::getPersonList)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        crossBuildingPeople.removeAll(peopleFromHospital);

        List<Person> youngAndOld = houses.stream()
                .filter(house ->
                        house.getBuildingType().equals("Civil building"))
                .map(House::getPersonList)
                .flatMap(List::stream)
                .filter(person ->
                        person.getGender().equals("Male") &&
                                person.getDateOfBirth().isAfter(LocalDate.now().minus(Period.ofYears(18))) ||
                                person.getDateOfBirth().isBefore(LocalDate.now().minus(Period.ofYears(63))) &&
                                        person.getGender().matches("Female") &&
                                        person.getDateOfBirth().isAfter(LocalDate.now().minus(Period.ofYears(18))) ||
                                person.getDateOfBirth().isBefore(LocalDate.now().minus(Period.ofYears(58)))
                )
                .collect(Collectors.toList());

        crossBuildingPeople.removeAll(youngAndOld);

        List<List<Person>> firstQueue =
                Arrays.asList(peopleFromHospital, youngAndOld, crossBuildingPeople);

        List<Person> toEvacuation = firstQueue.stream()
                .flatMap(List::stream)
                .limit(500)
                .collect(Collectors.toList());

        toEvacuation.stream()
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        List<Car> allCars = new ArrayList<>(cars);

        List<Car> stopTurkmenistan = allCars.stream()
                .filter(car ->
                        car.getCarMake().equals("Jaguar") || car.getColor().equals("White"))
                .collect(Collectors.toList());

        int massForTurkmenistan = stopTurkmenistan.stream()
                .mapToInt(Car::getMass)
                .sum();

        System.out.println("Price for Turkmenistan " + calculateTransportationCosts(massForTurkmenistan,
                BigDecimal.valueOf(7.14)) + " $");

        allCars.removeAll(stopTurkmenistan);

        List<Car> stopUzbekistan = allCars.stream()
                .filter(car ->
                        car.getMass() < 1500 ||
                                car.getCarMake().equals("BMW") ||
                                car.getCarMake().equals("Lexus") ||
                                car.getCarMake().equals("Chrysler") ||
                                car.getCarMake().equals("Toyota")
                )
                .collect(Collectors.toList());

        int massForUzbekistan = stopUzbekistan.stream()
                .mapToInt(Car::getMass)
                .sum();

        System.out.println("Price for Uzbekistan " + calculateTransportationCosts(massForUzbekistan,
                BigDecimal.valueOf(7.14)) + " $");

        allCars.removeAll(stopUzbekistan);

        List<Car> stopKazakhstan = allCars.stream()
                .filter(car ->
                        car.getColor().equals("Dark") ||
                                car.getColor().equals("Black") &&
                                        car.getMass() < 4000 ||
                                car.getCarMake().equals("GMC") ||
                                car.getCarMake().equals("Dodge")

                )
                .collect(Collectors.toList());

        int massForKazakhstan = stopKazakhstan.stream()
                .mapToInt(Car::getMass)
                .sum();

        System.out.println("Price for Kazakhstan " + calculateTransportationCosts(massForKazakhstan,
                BigDecimal.valueOf(7.14)) + " $");

        allCars.removeAll(stopKazakhstan);

        List<Car> stopKyrgyzstan = allCars.stream()
                .filter(car ->
                        car.getReleaseYear() < 1982 ||
                                car.getCarModel().equals("Civic") ||
                                car.getCarModel().equals("Cherokee"))
                .collect(Collectors.toList());

        int massForKyrgyzstan = stopKyrgyzstan.stream()
                .mapToInt(Car::getMass)
                .sum();

        System.out.println("Price for Kyrgyzstan " + calculateTransportationCosts(massForKyrgyzstan,
                BigDecimal.valueOf(7.14)) + " $");

        allCars.removeAll(stopKyrgyzstan);

        List<Car> stopRussia = allCars.stream()
                .filter(car ->
                        !car.getColor().equals("Yellow") &&
                                !car.getColor().equals("Red") &&
                                !car.getColor().equals("Green") &&
                                !car.getColor().equals("Blue") ||
                                car.getPrice() > 40000
                )
                .collect(Collectors.toList());

        int massForRussia = stopRussia.stream()
                .mapToInt(Car::getMass)
                .sum();

        System.out.println("Price for Russia " + calculateTransportationCosts(massForRussia,
                BigDecimal.valueOf(7.14)) + " $");

        allCars.removeAll(stopRussia);

        List<Car> stopMongolia = allCars.stream()
                .filter(car ->
                        car.getVin()
                                .matches(".*59.*"))
                .collect(Collectors.toList());

        int massForMongolia = stopMongolia.stream()
                .mapToInt(Car::getMass)
                .sum();

        System.out.println("Price for Mongolia " + calculateTransportationCosts(massForMongolia,
                BigDecimal.valueOf(7.14)) + " $");

        allCars.removeAll(stopMongolia);

        List<List<Car>> listCarsOnTrain =
                Arrays.asList(stopTurkmenistan, stopUzbekistan, stopKazakhstan, stopKyrgyzstan, stopRussia, stopMongolia);

        List<Car> allCarsOnTrain = listCarsOnTrain.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        int allMass = allCarsOnTrain.stream()
                .mapToInt(Car::getMass)
                .sum();

        System.out.println("Total revenue " + calculateTransportationCosts(allMass, BigDecimal.valueOf(7.14)) + " $");
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();

        BigDecimal sum = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                .sorted(Comparator.comparing(Flower::getPrice))
                .sorted(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(flower ->
                        flower.getCommonName().matches("^[c-sC-S]+"))
                .filter(flower ->
                        flower.isShadePreferred() &&
                                flower.getFlowerVaseMaterial().stream()
                                        .anyMatch(f ->
                                                f.matches("Glass") ||
                                                        f.matches("Aluminum") ||
                                                        f.matches("Steel")))
                .map(flower ->
                        calculateFullFlowerPriceFor5Years(
                                flower.getPrice(),
                                flower.getWaterConsumptionPerDay(),
                                BigDecimal.valueOf(1.39)))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        System.out.println(sum);
    }

    private static BigDecimal calculateTransportationCosts(int mass, BigDecimal priceForTon) {
        return priceForTon.multiply(BigDecimal.valueOf(mass));
    }

    private static BigDecimal calculateFullFlowerPriceFor5Years(int price, double waterConsumptionPerDay, BigDecimal waterCost) {
        BigDecimal flowerPrice = BigDecimal.valueOf(price);

        int daysIn5Years = 365 * 4 + 366;
        double waterFlow = Math.round(waterConsumptionPerDay * daysIn5Years);
        BigDecimal priceOfWaterFor5Years = waterCost.multiply(BigDecimal.valueOf(waterFlow)
                .round(new MathContext(4, RoundingMode.HALF_EVEN)));

        return flowerPrice.add(priceOfWaterFor5Years);
    }
}