package com.example.lab9jpa.Service;

import com.example.lab9jpa.Model.Car;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CarService {

    Car create(Car car);

    Car showCarById(Long id);

    List<Car> getListCar();

    List<Car> getListCarFromShowroom(Long id);

    void removeCar(Long id);

    void exportCSV(HttpServletResponse response) throws Exception;
}
