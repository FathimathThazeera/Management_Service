package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Folder;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.repository.table.AccountRepository;
import com.example.demo.repository.table.AccountTable;
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

    public List<Folder> getMap(Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (accountTable.isLogin() == false) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }
        return folderRepository.findByPhone(phone).stream().map(FolderTable::toFolder).collect(Collectors.toList());
    }

    public String insert(Folder folder, Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (accountTable.isLogin() == false) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }

        FolderTable folderTable = folder.toFolderTable();
        folderTable.setPhone(phone);
        folderRepository.save(folderTable);
        return folderTable.getFolder();
    }

    public List<Folder> getByFolder(String folder, Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (accountTable.isLogin() == false) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }

        List<FolderTable> optionalFolderTable = folderRepository.getByFolderList(folder, phone);
        if (optionalFolderTable == null) {
            log.warn("Folder not found");
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }
        return folderRepository.getByFolderList(folder, phone).stream().map(FolderTable::toFolder).collect(Collectors.toList());
    }
}
