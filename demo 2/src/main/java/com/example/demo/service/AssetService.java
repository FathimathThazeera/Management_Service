package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.exceptions.AssetNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.repository.table.AssetRepository;
import com.example.demo.repository.table.AssetTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getMap() {
        return assetRepository.findAll().stream().map(AssetTable::toAsset).collect(Collectors.toList());
    }

    public void insert(Asset asset) {
        if (assetRepository.existsById(asset.getId())) {
            log.warn("Duplicate message is trying to be inserted");
            throw new DuplicateKeyException();
        }
        assetRepository.save(asset.toAssetTable());
    }

    public Asset getById(long id) {
        Optional<AssetTable> optionalAssetTable = assetRepository.findById(id);
        if (!optionalAssetTable.isPresent()) {
            log.warn("Asset not found");
            throw new AssetNotFoundException();
        }
        return optionalAssetTable.get().toAsset();
    }

    public void update(Asset asset) {
        Optional<AssetTable> oldAssetOptional = assetRepository.findById(asset.getId());
        if (!oldAssetOptional.isPresent()) {
            log.warn("Asset not found while updating");
            throw new AssetNotFoundException();
        }
        AssetTable newAsset = asset.toAssetTable();
        newAsset.setCreatedAt(oldAssetOptional.get().getCreatedAt());
        assetRepository.save(newAsset);
    }

    public void delete(long id) {
        if (!assetRepository.existsById(id)) {
            log.warn("Invalid ID");
            throw new AssetNotFoundException();
        }
        assetRepository.deleteById(id);

    }
}
