package com.example.lab9jpa.Controller;

import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Model.Rating;
import com.example.lab9jpa.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/average/{id}")
    @ResponseBody
    public ResponseEntity average(@PathVariable(value = "id") long id) {
        if (ratingService.showRatingById(id) != null) {
            return new ResponseEntity<>(ratingService.getAverage(id), HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.resolve(404));
    }

    @DeleteMapping("/removeRating/{id}")
    public ResponseEntity<HttpStatus> removeRating(@PathVariable(value = "id") long id) {
        ratingService.removeRating(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
    @PostMapping("/api/Rating")
    public ResponseEntity<Rating> addCar(@RequestBody Rating rating) {
        HttpStatus status = HttpStatus.CREATED;
        Rating saved = ratingService.create(rating);
        return new ResponseEntity<>(saved, status);

    }

    @GetMapping("/rating")
    @ResponseBody
    public List<Rating> getAllRating() {
        return ratingService.getListRating();
    }


//    @GetMapping("/average/{id}")
//    @ResponseBody
//    public double average(@PathVariable(value = "id") long id) {
//
//        return ratingService.getAverage(id);
//    }


}
