package com.example.demo.service;

import com.example.demo.entity.Asset;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;


import java.util.HashMap;
import java.util.Map;

@Service
public class AssetService {

    private Map<Long, Asset> map = new HashMap<>();

    public Map<Long, Asset> getMap() {
        return map;
    }

    public boolean insert(Asset asset){
        if (map.containsKey(asset.getId())) {
            return false;
        }
        map.put(asset.getId(), asset);
        return true;
    }

    public Asset getById(long id){
        Asset asset = map.get(id);
        return asset;
    }

    public boolean update(Asset asset){
        if(!map.containsKey(asset.getId()))
        {
            return false;
        }
        map.put(asset.getId(), asset);
        return true;
    }

    public boolean delete(long id){
        if (!map.containsKey(id)) {
            return false;
        }
        map.remove(id);
        return true;
    }
}
