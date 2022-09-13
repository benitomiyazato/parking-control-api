package com.api.parkingcontrol.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {

    @Id
    @SequenceGenerator(name = "car_sequence", sequenceName = "car_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    private Long carId;

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
