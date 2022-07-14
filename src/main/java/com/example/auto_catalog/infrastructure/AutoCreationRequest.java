package com.example.auto_catalog.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AutoCreationRequest {
    @NotNull
    private String plateNumber;

    @NotNull
    private String brand;

    @NotNull
    private String color;

    @NotNull
    private Integer manufacturingYear;
}
