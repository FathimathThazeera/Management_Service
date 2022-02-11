package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Folder;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.repository.table.AccountRepository;
import com.example.demo.repository.table.FolderRepository;
import com.example.demo.repository.table.FolderTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class FolderService {
    private final FolderRepository folderRepository;

    private final AccountRepository accountRepository;

    public List<Folder> getMap(Long userId) {
        return folderRepository.findByUserId(userId).stream().map(FolderTable::toFolder).collect(Collectors.toList());
    }

    public String insert(Folder folder, Long userId) {
        FolderTable folderTable = folder.toFolderTable();
        folderTable.setUserId(userId);
        return folderRepository.save(folderTable).getFolder();
    }

    public List<Folder> getByFolder(String folder, Long userId) {

        List<FolderTable> optionalFolderTable = folderRepository.getByFolderList(folder, userId);
        if (optionalFolderTable == null) {
            log.warn("Folder not found");
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }
        return folderRepository.getByFolderList(folder, userId).stream().map(FolderTable::toFolder).collect(Collectors.toList());
    }
}
