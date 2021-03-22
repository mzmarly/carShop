package com.example.lab9jpa.ServiceImpl;

import com.example.lab9jpa.Model.Car;
import com.example.lab9jpa.Repo.CarRepository;
import com.example.lab9jpa.Service.CarService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {

        return carRepository.save(car);
    }


    @Override
    public Car showCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> getListCar() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getListCarFromShowroom(Long id) {
        List<Car> selected=new ArrayList();
        List<Car> all=carRepository.findAll();

        for(Car c:all){
            if(c.getFulfillment_id()==id){

                selected.add(c);
            }
        }
        return selected;
    }

    @Override
    public void removeCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void exportCSV(HttpServletResponse response) throws Exception {
        String filename = "products.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<Car> writer = new StatefulBeanToCsvBuilder<Car>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(carRepository.findAll());
    }
}
