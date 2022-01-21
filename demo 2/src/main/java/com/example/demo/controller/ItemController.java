package com.example.demo.controller;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Item;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Item>> getAll() {
        log.info("Received a request to get all item info ");
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.getMap());
    }

    @GetMapping("/{site}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Item> getBySite(@PathVariable String site) {
        log.info("Received a request to get info by site name : {}", site);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.getBySite(site));
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> insert(@RequestBody @Valid Item item) {
        log.info("Received a request to insert item : {}", item);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.insert(item));
    }

    @PutMapping("/update/{site}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> update(@PathVariable @NotNull @Positive String site, @RequestBody @Valid Item item) {
        log.info("Received a request to update item : {} ,site : {}", item, site);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.update(item, site));
    }

    @DeleteMapping("/delete/{site}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> delete(@PathVariable String site) {
        log.info("Received a request to delete item whose site is : {}", site);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.delete(site));
    }

    @PutMapping("/newpassword/{site}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> addNewPassword(@PathVariable @NotNull @Positive String site, @RequestBody @Valid Item item) {
        log.info("Received a request to  add new password : {} ,site : {}", item, site);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.addNewPassword(item, site));
    }

    @GetMapping("/url/{url}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Item> getByUrl(@PathVariable @Valid String url) {
        log.info("Received a request to get info by url : {}", url);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.getByUrl(url));
    }
}
