package com.api.parkingcontrol.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_car")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {

    @Id
    @SequenceGenerator(name = "car_sequence", sequenceName = "car_sequence", allocationSize = 1)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarModel carModel = (CarModel) o;
        return carId != null && Objects.equals(carId, carModel.carId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
