package com.example.lab9jpa.Repo;

import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Model.CarShowroom;
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
public class CarShowroomRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarShowroomRepository carShowroomRepository;
    @Test
    public void whenFindById_thenReturnCarShowroom() {
        CarShowroom carShowroom = new CarShowroom (1L, "Opel", 10);

        CarShowroom fromDb = carShowroomRepository.findById(carShowroom.getId()).orElse(null);
        assertThat(fromDb.getNazwaSalonu()).isEqualTo(carShowroom.getNazwaSalonu());
    }
    @Test
    public void whenInvalidId_thenReturnNull() {
        CarShowroom fromDb = carShowroomRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCarShowroom_whenFindAll_thenReturnAllCarShowrooms() {
        CarShowroom carShowroom1 = new CarShowroom (1L, "Opel", 10);
        CarShowroom carShowroom2 = new CarShowroom (2L, "mazda", 11);
        CarShowroom carShowroom3 = new CarShowroom (2L, "mercesed", 12);

        List<CarShowroom> allEmployees = carShowroomRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(CarShowroom::getNazwaSalonu).containsOnly(carShowroom1.getNazwaSalonu(), carShowroom2.getNazwaSalonu(), carShowroom3.getNazwaSalonu());
    }
}


