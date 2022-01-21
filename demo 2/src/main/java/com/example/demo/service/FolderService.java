package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Folder;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.repository.table.FolderRepository;
import com.example.demo.repository.table.FolderTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FolderService {


    @Autowired
    private FolderRepository folderRepository;

    public List<Folder> getMap() {
        return folderRepository.findAll().stream().map(FolderTable::toFolder).collect(Collectors.toList());
    }

    public String insert(Folder folder) {
        if (folderRepository.existsById(folder.getFolder())) {
            log.warn("Folder already existing");
            throw new DuplicateKeyException(ResultInfoConstants.FOLDER_ALREADY_EXISTING);
        }
        return folderRepository.save(folder.toFolderTable()).getFolder();
    }

    public Folder getByFolder(String folder) {
        Optional<FolderTable> optionalFolderTable = folderRepository.findById(folder);
        if (!optionalFolderTable.isPresent()) {
            log.warn("Folder not found");
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }
        return optionalFolderTable.get().toFolder();
    }

    public String delete(String folder) {
        if (!folderRepository.existsById(folder)) {
            log.warn("Invalid folder");
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }
        folderRepository.deleteById(folder);
        return folder;
    }
}
