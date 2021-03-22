package com.example.lab9jpa.Controller;


import com.example.lab9jpa.Model.CarShowroom;
import com.example.lab9jpa.Service.CarShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class CarShowroomController {
    @Autowired
    private CarShowroomService carShowroomService;

    @DeleteMapping("/fulfillment/{id}")
    public ResponseEntity<HttpStatus> removeCar(@PathVariable(value = "id") long id) {

        if(carShowroomService.showCarShowroomById(id)!=null) {
            carShowroomService.removeShowroom(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
        }
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.resolve(404));
    }

    @GetMapping("/capacity/{id}")
    @ResponseBody
    public ResponseEntity getCapacity(@PathVariable(value = "id") long id)
    {
        if (carShowroomService.showCarShowroomById(id) != null) {
            return new ResponseEntity<>(carShowroomService.capacity(id), HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.resolve(404));

        //return carShowroomService.capacity(id);
    }
    @PostMapping("/api/fulfillment")
    public ResponseEntity<CarShowroom> addCarShowroom(@RequestBody CarShowroom carShowroom)
    {
        HttpStatus status = HttpStatus.CREATED;
        CarShowroom saved =carShowroomService.create(carShowroom);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/fulfillment")
    @ResponseBody
    public List<CarShowroom> getAllCarShowrooms()
    {
        return carShowroomService.getListOfShowroom();
    }


}
