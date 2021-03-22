package com.example.lab9jpa.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @Column(name="rating_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long salon_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalon_id() {
        return salon_id;
    }

    public void setSalon_id(Long salon_id) {
        this.salon_id = salon_id;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Column
    private double ocena;

    @Column
    private Date dataWystawienia;

    public Rating(Long id, Long salon_id, double ocena, String opis) {
        this.id = id;
        this.salon_id = salon_id;
        this.ocena = ocena;

        this.opis = opis;
    }
    public Rating(){

    }
    @Column
    private String opis;

}
