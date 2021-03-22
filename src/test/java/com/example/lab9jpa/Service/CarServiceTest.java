package com.example.lab9jpa.Service;

import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Repo.CarRepository;
import com.example.lab9jpa.ServiceImpl.CarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CarServiceTest {

    @TestConfiguration
    static class CarServiceTestC {
        @Bean
        public CarService carService() {
            return new CarServiceImpl();
        }
    }

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Before
    public void setUp() {
        Car car1 = new Car (1L, "opel", "astra",1999, 20000, 2, 1,2L);
        Car car2= new Car (2L, "opel", "bas",1999, 20000, 2, 1,2L);
        Car car3 = new Car (3L, "opel", "cas",1999, 20000, 2, 1,2L);

        List<Car> allCars = Arrays.asList(car1, car2, car3);
        Mockito.when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());
    }
    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Car fromDb = carService.showCarById(1L);
        assertThat(fromDb.getMarka()).isEqualTo("opel");

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(carRepository);
    }

    @Test
    public void whenInValidId_thenCarShouldNotBeFound() {
        Car fromDb = carService.showCarById(-99L);
        assertThat(fromDb).isNull();

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(carRepository);
    }

    @Test
    public void given3Cars_whengetAll_thenReturn3Records() {
        Car car1 = new Car (1L, "opel", "astra",1999, 20000, 2, 1,2L);
        Car car2= new Car (2L, "opel", "bas",1999, 20000, 2, 1,2L);
        Car car3 = new Car (3L, "opel", "cas",1999, 20000, 2, 1,2L);

        List<Car> allEmployees = carService.getListCar();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(carRepository);

        assertThat(allEmployees).hasSize(3).extracting(Car::getMarka).contains(car1.getMarka(), car1.getMarka(), car3.getMarka());
    }

    }


