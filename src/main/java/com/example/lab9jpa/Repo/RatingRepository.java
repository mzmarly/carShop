package com.example.lab9jpa.Repo;

import com.example.lab9jpa.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating,Long> {
}
