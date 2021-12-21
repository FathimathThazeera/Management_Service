package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/all")
    public ResponseEntity<Collection<Asset>> getAll() {
        return ResponseEntity.ok(assetService.getMap().values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable long id) {
        Asset asset = assetService.getById(id);
        if (asset == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asset);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Asset asset) {
        assetService.insert(asset);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Asset asset) {
        assetService.update(asset);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean res = assetService.delete(id);
        if (!res) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }

    }
}