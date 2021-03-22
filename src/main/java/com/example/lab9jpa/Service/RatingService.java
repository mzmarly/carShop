package com.example.lab9jpa.Service;

import com.example.lab9jpa.Model.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);

    Rating showRatingById(Long id);

    List<Rating> getListRating();

    void removeRating(Long id);

    double getAverage(Long id);
}
