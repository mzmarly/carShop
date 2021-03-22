package com.example.lab9jpa.Controller;

import com.example.lab9jpa.JsonUtil;
import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CarController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService service;

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car car=new Car(1L, "opel", "astra",1999, 20000, 2, 1,1L);
    //    when(service.create(Mockito.any())).thenReturn(car);
    given(service.create(Mockito.any())).willReturn(car);

          mvc
                .perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marka", is("opel")))
                .andExpect(jsonPath("$.model", is("astra")))
                .andExpect(jsonPath("$.rokProdukcji", is(1999)))
                .andExpect(jsonPath("$.przebieg", is(20000)));

        verify(service, VerificationModeFactory.times(1)).create(Mockito.any());
        reset(service);
    }

    @Test
    public void givenCar_whenGetCar_thenReturnJsonArray() throws Exception {
        Car car1=new Car(1L, "opel", "astra",1999, 20000, 2, 1,1L);
        Car car2 = new Car(1L, "opel", "astra",1999, 20000, 2, 1,1L);
        Car car3 = new Car (1L, "opel", "astra",1999, 20000, 2, 1,1L);

        List<Car> allEmployees = Arrays.asList(car1, car2, car3);

        given(service.getListCar()).willReturn(allEmployees);

        mvc
                .perform(get("/car").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].marka", is(car1.getMarka())))
                .andExpect(jsonPath("$[1].marka", is(car2.getMarka())))
                .andExpect(jsonPath("$[2].marka", is(car3.getMarka())));

        verify(service, VerificationModeFactory.times(1)).getListCar();
        reset(service);
    }

    @Test
    public void givenSelectedCar_whenGetCar_thenReturnJsonArray() throws Exception {
        Car car1=new Car(1L, "opel", "astra",1999, 20000, 2, 1, 1L);
        Car car2 = new Car(2L, "opel", "astra",1999, 20000, 2, 1,1L);
        Car car3 = new Car (3L, "opel", "astra",1999, 20000, 2, 1,1L);

        List<Car> allEmployees = Arrays.asList(car1, car2, car3);

        given(service.getListCar()).willReturn(allEmployees);
       // given(service.getListCarFromShowroom(1L)).willReturn(allEmployees);

        assertThat(service.getListCar().size()).isEqualTo(3);
        assertThat(service.getListCar().get(0).getMarka()).isEqualTo(car1.getMarka());
        assertThat(service.getListCar().get(1).getMarka()).isEqualTo(car2.getMarka());
        assertThat(service.getListCar().get(2).getMarka()).isEqualTo(car3.getMarka());

        mvc
                .perform(get("/selectedCars/{id}",1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
   public void should_export_CSV() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/product/csv")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


//    @Test
//    public void should_del_Car() throws Exception {
//        mvc.perform(delete("/removeCar/{id}",1L))
//                .andExpect(status().isOk());
//    }
}
