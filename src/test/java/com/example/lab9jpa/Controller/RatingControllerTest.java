package com.example.lab9jpa.Controller;


import com.example.lab9jpa.JsonUtil;
import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Model.Rating;
import com.example.lab9jpa.Service.RatingService;
import org.assertj.core.api.Assertions;
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
@WebMvcTest(value = RatingController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RatingService ratingServicervice;

    @Test
    public void whenPostRating_thenCreateRating() throws Exception {
        Rating rating= new Rating(1L, 1L, 3.0,  "nice");
        given(ratingServicervice.create(Mockito.any())).willReturn(rating);

        mvc
                .perform(post("/api/Rating")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(rating)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.salon_id", is(1)))
                .andExpect(jsonPath("$.ocena", is(3.0)))
                .andExpect(jsonPath("$.opis", is("nice")));

        verify(ratingServicervice, VerificationModeFactory.times(1)).create(Mockito.any());
        reset(ratingServicervice);
    }
    @Test
    public void givenRating_whenGetRating_thenReturnJsonArray() throws Exception {
        Rating rating1= new Rating(1L, 1L, 4.0,  "nice");
        Rating rating2= new Rating(2L, 1L, 2.0,  "bad");
        Rating rating3= new Rating(3L, 1L, 3.0,  "ok");


        List<Rating> allRatings = Arrays.asList(rating1, rating2,rating3);

        given(ratingServicervice.getListRating()).willReturn(allRatings);

        mvc
                .perform(get("/rating").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].opis", is(rating1.getOpis())))
                .andExpect(jsonPath("$[1].opis", is(rating2.getOpis())))
                .andExpect(jsonPath("$[2].opis", is(rating3.getOpis())));

        verify(ratingServicervice, VerificationModeFactory.times(1)).getListRating();
        reset(ratingServicervice);
    }
    @Test
    public void should_delete_Rating() throws Exception {
        mvc.perform(delete("/removeRating/{id}",1L))
                .andExpect(status().isAccepted());
    }

    @Test
    public void givenAverage_then_showAverage() throws Exception{
double srednia =3.0;

given(ratingServicervice.getAverage(1L)).willReturn(srednia);

//        mvc
//                .perform(get("/average/{id}",1L).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .equals(3.0);

      assertThat(ratingServicervice.getAverage(1l)).isEqualTo(srednia);
    }
}
