package com.example.demo.controller;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Folder;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.FolderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/folder")
@Slf4j
public class FolderController {

    private final FolderService folderService;

    private final ObjectMapper objectMapper;

    @GetMapping("/all/{phone}")
    @ResponseStatus(HttpStatus.OK)
    public List<Folder> getAll(@PathVariable @NotNull @Positive Long phone) {
        log.info("Received a request to get all folder ");
        return folderService.getMap(phone);
    }

    @GetMapping("/{phone}/{folder}")
    @ResponseStatus(HttpStatus.OK)
    public List<Folder> getByFolder(@PathVariable String folder, @PathVariable @NotNull @Positive Long phone) {
        log.info("Received a request to get folder : {}", folder);
        return folderService.getByFolder(folder, phone);
    }

    @PostMapping("/insert/{phone}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<String> insert(@RequestBody @Valid Folder folder, @PathVariable @NotNull @Positive Long phone) {
        log.info("Received a request to insert folder : {} ", folder);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, folderService.insert(folder, phone));
    }
}
