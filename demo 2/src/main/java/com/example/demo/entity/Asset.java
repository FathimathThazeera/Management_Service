package com.example.demo.entity;

import com.example.demo.repository.table.AssetTable;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Data

public class Asset {
    @NotNull
    private final Long id;
    @NotBlank
    private final String name;
    @Min(0)
    @NotNull
    private final Integer amount;
    @NotNull
    private final Year make;
    @NotNull
    private final Type type;
    @NotNull
    private final Brand brand;

    public AssetTable toAssetTable() {
        return new AssetTable(this.id, this.name, this.amount, this.make, this.type);
    }
}
