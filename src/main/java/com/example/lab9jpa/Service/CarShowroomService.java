package com.example.lab9jpa.Service;

import com.example.lab9jpa.Model.CarShowroom;

import java.util.List;

public interface CarShowroomService {
    CarShowroom create(CarShowroom carShowroom);

    List<CarShowroom> getListOfShowroom();

    CarShowroom showCarShowroomById(Long id);

    void removeShowroom(Long id);

    String capacity(Long id);
}
