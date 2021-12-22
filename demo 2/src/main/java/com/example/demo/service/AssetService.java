package com.example.demo.service;

import com.example.demo.entity.Asset;
import com.example.demo.exceptions.AssetNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AssetService {

    private Map<Long, Asset> map = new HashMap<>();

    public Map<Long, Asset> getMap() {
        return map;
    }

    public void insert(Asset asset) {
        if (map.containsKey(asset.getId())) {
            log.warn("Duplicate message is trying to be inserted");
            throw new DuplicateKeyException();
        }
        map.put(asset.getId(), asset);
    }

    public Asset getById(long id) {
        if (!map.containsKey(id)) {
            log.warn("Asset not found");
            throw new AssetNotFoundException();
        }
        return map.get(id);
    }

    public void update(Asset asset) {
        if (!map.containsKey(asset.getId())) {
            log.warn("Asset not found while updating");
            throw new AssetNotFoundException();
        }
        map.put(asset.getId(), asset);
    }

    public void delete(long id) {
        if (!map.containsKey(id)) {
            log.warn("ID is invalid");
            throw new AssetNotFoundException();
        }
        map.remove(id);
    }
}
