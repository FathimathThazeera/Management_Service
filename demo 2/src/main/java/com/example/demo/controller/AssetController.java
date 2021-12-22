package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/asset")
@Slf4j
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/all")
    public ResponseEntity<Collection<Asset>> getAll() {
        log.info("Received a request to get all asset info ");
        return ResponseEntity.ok(assetService.getMap().values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable long id) {
        log.info("Received a request to get asset by its ID : {}", id);
        Asset asset = assetService.getById(id);
        if (asset == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asset);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Asset asset) {
        log.info("Received a request to insert asset : {}", asset);
        assetService.insert(asset);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Asset asset) {
        log.info("Received a request to update asset : {}", asset);
        assetService.update(asset);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        log.info("Received a request to delete asset whose ID is : {}", id);
        assetService.delete(id);
    }
}