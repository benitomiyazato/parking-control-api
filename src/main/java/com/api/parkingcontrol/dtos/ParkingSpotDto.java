package com.api.parkingcontrol.dtos;

import com.api.parkingcontrol.models.CarModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotDto {

    @NotBlank
    private String parkingSpotNumber;

    @NotBlank
    private String apartment;

    @NotBlank
    private String block;

    @NotNull
    private CarModel car;
}