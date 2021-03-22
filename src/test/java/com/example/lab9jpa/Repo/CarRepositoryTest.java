package com.example.lab9jpa.Repo;

import com.example.lab9jpa.Model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;


    @Test
    public void whenFindById_thenReturnCar() {
        Car car = new Car (1L, "opel", "astra",1999, 20000, 2, 1,2L);


        Car fromDb = carRepository.findById(car.getId()).orElse(null);
        assertThat(fromDb.getMarka()).isEqualTo(car.getMarka());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCar() {
        Car car = new Car (1L, "opel", "astra",1999, 20000, 2, 1,2L);
        Car car1 = new Car (2L, "opel", "astra",1999, 20000, 2, 1,2L);
        Car car2 = new Car (3L, "opel", "astra",1999, 20000, 2, 1,2L);


       entityManager.flush();

        List<Car> allEmployees = carRepository.findAll();

        assertThat(allEmployees).hasSize(4).extracting(Car::getMarka).containsOnly(car.getMarka(), car1.getMarka(), car2.getMarka());
    }
}
