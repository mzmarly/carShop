package com.example.lab9jpa.ServiceImpl;

import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Model.CarShowroom;
import com.example.lab9jpa.Repo.CarRepository;
import com.example.lab9jpa.Repo.CarShowroomRepository;
import com.example.lab9jpa.Service.CarShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CarShowroomServiceImpl implements CarShowroomService {

    @Autowired
    private CarShowroomRepository carShowroomRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public CarShowroom create(CarShowroom carShowroom) {
        return carShowroomRepository.save(carShowroom);
    }

    @Override
    public List<CarShowroom> getListOfShowroom() {
       return carShowroomRepository.findAll();
    }

    @Override
    public CarShowroom showCarShowroomById(Long id) {
        return carShowroomRepository.findById(id).orElse(null);
    }

    @Override
    public void removeShowroom(Long id) {
        carShowroomRepository.deleteById(id);
    }

    @Override
    public String capacity(Long id) {
        List<CarShowroom> all=carShowroomRepository.findAll();
        double maxSize=0;
        double i=0;
        for(CarShowroom c:all){
            if(c.getId()==id){
                maxSize=c.getMaxSize();
            }
        }
        System.out.println(maxSize);
        List<Car> car=carRepository.findAll();
        for(Car d:car){
            if(d.getFulfillment_id()==id){
                i++;
            }
        }
        return (i/maxSize)*100+"%";

    }
}
