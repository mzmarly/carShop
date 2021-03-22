package com.example.lab9jpa.Service;

import com.example.lab9jpa.Model.CarShowroom;
import com.example.lab9jpa.Model.Rating;
import com.example.lab9jpa.Repo.RatingRepository;
import com.example.lab9jpa.ServiceImpl.RatingServiceImpl;
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
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class RatingServiceTest {
    @TestConfiguration
    static class RatingServiceT {
        @Bean
        public RatingService ratingService() {
            return new RatingServiceImpl();
        }
    }

        @Autowired
        private RatingService ratingService;

        @MockBean
        private RatingRepository ratingRepository;


        @Before
        public void setUp(){
            Rating rating= new Rating(1L, 1L, 3.0,  "nice");
            Mockito.when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
            Mockito.when(ratingRepository.findById(-99L)).thenReturn(Optional.empty());
        }

    @Test
    public void whenValidId_thenCarShowroomShouldBeFound() {
        Rating fromDb = ratingService.showRatingById(1L);
        assertThat(fromDb.getOpis()).isEqualTo("nice");

        Mockito.verify(ratingRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(ratingRepository);
    }
        @Test
        public void whenInValidId_thenRatingShowroomShouldNotBeFound(){
            Rating fromDb=ratingService.showRatingById(-99L);
            assertThat(fromDb).isNull();

            Mockito.verify(ratingRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
            Mockito.reset(ratingRepository);

        }
    }

