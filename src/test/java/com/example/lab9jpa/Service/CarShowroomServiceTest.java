package com.example.lab9jpa.Service;

import com.example.lab9jpa.Model.CarShowroom;
import com.example.lab9jpa.Repo.CarRepository;
import com.example.lab9jpa.Repo.CarShowroomRepository;
import com.example.lab9jpa.ServiceImpl.CarServiceImpl;
import com.example.lab9jpa.ServiceImpl.CarShowroomServiceImpl;
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
public class CarShowroomServiceTest {


    @TestConfiguration
    static class CarShowroomServiceTestC {
        @Bean
        public CarShowroomService carShowroomService() {
            return new CarShowroomServiceImpl();
        }
    }
//    @TestConfiguration
//    static class CarServiceTestC {
//    @Bean
//    public CarService carService() {
//        return new CarServiceImpl();
//    }
//}


    @Autowired
    private CarShowroomService carShowroomService;
//    @Autowired
//    private CarService carService;
    @MockBean
    private CarShowroomRepository carShowroomRepository;
    @MockBean
    private CarRepository carRepository;


    @Before
    public void setUp() {
        CarShowroom carShowroom1 = new CarShowroom (1L, "Opel", 10);
        CarShowroom carShowroom2 = new CarShowroom (2L, "mazda", 11);
        CarShowroom carShowroom3 = new CarShowroom (2L, "mercesed", 12);

        List<CarShowroom> allCarShowrooms = Arrays.asList(carShowroom1, carShowroom2, carShowroom3);
        Mockito.when(carShowroomRepository.findById(carShowroom1.getId())).thenReturn(Optional.of(carShowroom1));
        Mockito.when(carShowroomRepository.findAll()).thenReturn(allCarShowrooms);
        Mockito.when(carShowroomRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    public void whenValidId_thenCarShowroomShouldBeFound() {
        CarShowroom fromDb = carShowroomService.showCarShowroomById(1L);
        assertThat(fromDb.getNazwaSalonu()).isEqualTo("Opel");

        Mockito.verify(carShowroomRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(carShowroomRepository);
    }
    @Test
    public void whenInValidId_thenCarShowroomShouldNotBeFound() {
        CarShowroom fromDb = carShowroomService.showCarShowroomById(-99L);
        assertThat(fromDb).isNull();

        Mockito.verify(carShowroomRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(carShowroomRepository);
    }
}
