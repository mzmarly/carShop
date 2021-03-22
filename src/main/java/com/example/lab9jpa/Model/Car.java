package com.example.lab9jpa.Model;

import javax.persistence.*;


@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @Column(name="car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String marka;

    @Column
    private String model;

    @Column
    private int rokProdukcji;

    @Column
    private int przebieg;
public Car(String marka){
    this.marka=marka;
}
    public Car(Long id, String marka, String model, int rokProdukcji, int przebieg, int pojemnoscSilinka, int quantity,Long fulfillment_id) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
        this.przebieg = przebieg;
        this.pojemnoscSilinka = pojemnoscSilinka;
        this.quantity = quantity;
        this.fulfillment_id=fulfillment_id;
    }

//        public Car(Long id, String marka, String model, int rokProdukcji, int przebieg, int pojemnoscSilinka, int quantity,CarShowroom carShowroom) {
//        this.id = id;
//        this.marka = marka;
//        this.model = model;
//        this.rokProdukcji = rokProdukcji;
//        this.przebieg = przebieg;
//        this.pojemnoscSilinka = pojemnoscSilinka;
//        this.quantity = quantity;
//        this.carShowroom=carShowroom;
//    }

    @Column
    private int pojemnoscSilinka;

    @Column
    private int quantity;

    public Long getFulfillment_id() {
        return fulfillment_id;
    }

    public void setFulfillment_id(Long fulfillment_id) {
        this.fulfillment_id = fulfillment_id;
    }

    @Column
    private Long fulfillment_id;
public Car(){
}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    public int getPojemnoscSilinka() {
        return pojemnoscSilinka;
    }

    public void setPojemnoscSilinka(int pojemnoscSilinka) {
        this.pojemnoscSilinka = pojemnoscSilinka;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}
