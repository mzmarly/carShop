//package com.example.lab9jpa.Controller;
//
//import com.example.lab9jpa.JsonUtil;
//import com.example.lab9jpa.Model.CarShowroom;
//import com.example.lab9jpa.Service.CarService;
//import com.example.lab9jpa.Service.CarShowroomService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.internal.verification.VerificationModeFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = CarShowroomControllerTest.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
//public class CarShowroomControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CarShowroomService carShowroomService;
//
//    @Test
//    public void whenPostCarShowroom_thenCreateCar() throws Exception {
//        CarShowroom carShowroom = new CarShowroom(1L,"OpelSalon",10);
//          //when(service.create(Mockito.any())).thenReturn(carShowroom);
//        given(carShowroomService.create(Mockito.any())).willReturn(carShowroom);
//
//        mvc
//                .perform(post("/api/fulfillment")
//                        .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(carShowroom)))
//            //   .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.nazwaSalonu", is("OpelSalon")))
//                .andExpect(jsonPath("$.maxSize", is(10)));
//
//        verify(carShowroomService, VerificationModeFactory.times(1)).create(Mockito.any());
//        reset(carShowroomService);
//    }
//
//    @Test
//    public void should_del_fulfillment() throws Exception {
//        mvc.perform(delete("/fulfillment/{id}",1))
//                .andExpect(status().isAccepted());
//    }
//
//    @Test
//    public void givenAverage_then_showAverage() throws Exception{
//        double srednia =3.0;
//
//        given(carShowroomService.capacity(1L)).willReturn("srednia +%");
//
//        mvc
//                .perform(get("/average/{id}",1L).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//
//        assertThat(carShowroomService.capacity(1l)).isEqualTo(srednia);
//    }
//}
