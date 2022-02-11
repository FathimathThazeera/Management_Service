package com.example.demo.controller;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Item;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    private final ObjectMapper objectMapper;

    @GetMapping("/all/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Item>> getAll(@PathVariable @NotNull @Positive Long userId, Pageable page) {
        log.info("Received a request to get all item info ");
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.getMap(userId, page));
    }

    @GetMapping("/{site}/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Item>> getBySite(@PathVariable String site, @PathVariable @NotNull @Positive Long userId) {
        log.info("Received a request to get info by site name : {}", site);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.getBySite(site, userId));
    }

    @PostMapping("/insert/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> insert(@RequestBody @Valid Item item, @PathVariable @NotNull @Positive Long userId) {
        log.info("Received a request to insert item : {}", item);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.insert(item, userId));
    }

    @PutMapping("/update/{id}/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> update(@RequestBody @Valid Item item, @PathVariable @NotNull @Positive Long id, @PathVariable @NotNull @Positive Long userId) {
        log.info("Received a request to update item : {} ,id : {}", item, id);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.update(item, id, userId));
    }

    @PutMapping("/newpassword/{userid}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> addNewPassword(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Item item, @PathVariable @NotNull @Positive Long userId) {
        log.info("Received a request to  add new password : {} ,site : {}", item, id);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, itemService.addNewPassword(item, id, userId));
    }

    @GetMapping("/url/{userid}/{url}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getByUrl(@PathVariable @Valid String url, @PathVariable @NotNull @Positive Long userId) {
        log.info("Received a request to get info by url : {}", url);
        return itemService.getByUrl(url, userId);
    }

}
