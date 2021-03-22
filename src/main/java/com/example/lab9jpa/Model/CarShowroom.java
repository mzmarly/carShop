package com.example.lab9jpa.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Showrooms")
public class CarShowroom {

    @Id
    @Column(name="carShowroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nazwaSalonu;

    public CarShowroom(Long id, String nazwaSalonu, int maxSize) {
        this.id = id;
        this.nazwaSalonu = nazwaSalonu;
        this.maxSize = maxSize;
    }


    @Column
    private int maxSize;
    public CarShowroom(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwaSalonu() {
        return nazwaSalonu;
    }

    public void setNazwaSalonu(String nazwaSalonu) {
        this.nazwaSalonu = nazwaSalonu;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
