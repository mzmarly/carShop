package com.example.lab9jpa.Controller;

import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/api/product")
    public ResponseEntity<Car> addCar(@RequestBody Car car)
    {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carService.create(car);
        return new ResponseEntity<>(saved, status);

    }

    @DeleteMapping("/removeCar/{id}")
    public ResponseEntity<HttpStatus> delFulfillment(@PathVariable(value = "id") long id)
    {
        if(carService.showCarById(id)!=null) {
            carService.removeCar(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
        }
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.resolve(404));
    }

    @GetMapping("/car")
    @ResponseBody
    public List<Car> getAllCars()
    {
        return carService.getListCar();
    }


    @GetMapping("/selectedCars/{id}")
    @ResponseBody
    public List<Car> getListCarFrom(@PathVariable(value = "id") long id)
    {
        return carService.getListCarFromShowroom(id);
    }


    @GetMapping("/product/csv")
    public void exportCSV(HttpServletResponse response) throws Exception {

        carService.exportCSV(response);

    }
}
