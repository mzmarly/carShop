package com.example.lab9jpa.Repo;

import com.example.lab9jpa.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
}
