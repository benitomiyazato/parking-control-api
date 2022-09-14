package com.api.parkingcontrol.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "TB_PARKING_SPOT")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "parking_spot_sequence", sequenceName = "parking_spot_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_spot_sequence")
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 30)
    private String apartment;

    @Column(nullable = false, length = 30)
    private String block;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "car_id", referencedColumnName = "carId")
    private CarModel car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParkingSpotModel that = (ParkingSpotModel) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
