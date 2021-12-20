package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> insert(@RequestBody Asset asset) {
        boolean res = assetService.insert(asset);
        if (res) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Asset asset) {
        boolean res = assetService.update(asset);
        if (!res) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
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