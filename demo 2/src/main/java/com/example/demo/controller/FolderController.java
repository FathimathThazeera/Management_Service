package com.example.demo.controller;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Folder;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.FolderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/folder")
@Slf4j
public class FolderController {
    @Autowired
    private FolderService folderService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Folder> getAll() {
        log.info("Received a request to get all folder ");
        return folderService.getMap();
    }

    @GetMapping("/{folder}")
    @ResponseStatus(HttpStatus.OK)
    public Folder getByFolder(@PathVariable String folder) {
        log.info("Received a request to get folder : {}", folder);
        return folderService.getByFolder(folder);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> insert(@RequestBody @Valid Folder folder) {
        log.info("Received a request to insert folder : {} ", folder);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, folderService.insert(folder));
    }

    @DeleteMapping("/delete/{folder}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> delete(@PathVariable String folder) {
        log.info("Received a request to delete folder : {}", folder);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, folderService.delete(folder));
    }
}
