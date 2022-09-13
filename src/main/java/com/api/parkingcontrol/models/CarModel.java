package com.api.parkingcontrol.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_car")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID carId;

    @Column(nullable = false, unique = true, length = 7)
    private String licensePlate;

    @Column(nullable = false, length = 70)
    private String brand;

    @Column(nullable = false, length = 70)
    private String model;

    @Column(nullable = false, length = 70)
    private String color;

    @Column(nullable = false, length = 130)
    private String responsibleName;

}
