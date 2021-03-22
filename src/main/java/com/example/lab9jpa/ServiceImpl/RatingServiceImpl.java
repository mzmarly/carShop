package com.example.lab9jpa.ServiceImpl;

import com.example.lab9jpa.Model.Rating;
import com.example.lab9jpa.Repo.RatingRepository;
import com.example.lab9jpa.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }


    @Override
    public Rating showRatingById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rating> getListRating() {
        return ratingRepository.findAll();
    }

    @Override
    public void removeRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public double getAverage(Long id) {
        List<Rating> all=ratingRepository.findAll();
        double result=0;
        int i=0;
        for(Rating u:all){
            if(u.getSalon_id()==id){
                result+=u.getOcena();
                i++;
            }
        }
        return result/i;
    }
}
