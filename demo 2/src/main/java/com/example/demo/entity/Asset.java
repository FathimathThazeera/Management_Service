package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Asset {
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @Min(0)
    @NotNull
    private Integer amount;
    @NotNull
    private Year make;
    @NotNull
    private Type type;
    @NotNull
    private Brand brand;
}
