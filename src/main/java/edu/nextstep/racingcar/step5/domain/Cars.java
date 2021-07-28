package edu.nextstep.racingcar.step5.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void play(int numberOfAttempts, MoveStrategy moveStrategy) {
        IntStream.range(0, numberOfAttempts).forEach(idx -> attempt(moveStrategy));
    }

    private void attempt(MoveStrategy moveStrategy) {
        cars.forEach(car -> car.move(moveStrategy));
    }

    public List<String> getWinners() {
        int maxDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(car -> car.getCarName().getName())
                .collect(Collectors.toList());
    }

    public static Cars create(String namesOfCars) {
        List<Car> cars = Arrays.stream(namesOfCars.split(","))
                .map(name -> new Car(new CarName(name)))
                .collect(Collectors.toList());

        return new Cars(cars);
    }

    public List<Car> getCars() {
        return cars;
    }
}