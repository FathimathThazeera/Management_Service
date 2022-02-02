package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Item;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.repository.table.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    private final AccountRepository accountRepository;

    private final FolderRepository folderRepository;

    public List<Item> getMap(Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (!accountTable.isLogin()) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }
        return itemRepository.findByPhone(phone).stream().map(ItemTable::toItem).collect(Collectors.toList());
    }

    public String insert(Item item, Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (!accountTable.isLogin()) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }
       /*UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
        UrlValidator urlValidator = new URLValidator();
          if (urlValidator.isValid()){
            log.warn("Enter valid url");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_VALID_URL);
        }*/

        Long folderId = item.getFolder();
        FolderTable folderTable = folderRepository.getFolderByIdAndPhone(folderId, phone);
        if (folderTable == null) {
            log.warn("Invalid folder id");
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }

        ItemTable itemTable = item.toItemTable(folderTable.getId());
        itemTable.setPhone(phone);
        return itemRepository.save(itemTable).getSite();
    }

    public List<Item> getBySite(String site, Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (!accountTable.isLogin()) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }

        List<ItemTable> optionalItemTable = itemRepository.findBySite(site, phone);
        if (optionalItemTable == null) {
            log.warn("Item not found");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        return itemRepository.findBySite(site, phone).stream().map(ItemTable::toItem).collect(Collectors.toList());
    }

    public String update(Item item, Long id, Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }

        if (!accountTable.isLogin()) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }

        ItemTable oldItem = itemRepository.getItemByIdAndPhone(id, phone);
        if (oldItem == null) {
            log.warn("Item not found while updating");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }

        Long folderId = item.getFolder();
        FolderTable folderTable = folderRepository.getFolderByIdAndPhone(folderId, phone);
        if (folderTable == null) {
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }

        ItemTable newItem = item.toItemTable(folderTable.getId());
        newItem.setCreatedAt(oldItem.getCreatedAt());
        newItem.setId(oldItem.getId());
        newItem.setPhone(oldItem.getPhone());
        itemRepository.save(newItem);
        return newItem.getSite();
    }

    public String addNewPassword(Item item, Long id, Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (!accountTable.isLogin()) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }

        ItemTable oldItem = itemRepository.getItemByIdAndPhone(id, phone);
        if (oldItem == null) {
            log.warn("Item not found while updating");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        String newPassword = item.getPassword();
        ItemTable newItem = item.toItemTable(oldItem.getFolder());
        newItem.setId(oldItem.getId());
        newItem.setUrl(oldItem.getUrl());
        newItem.setUser(oldItem.getUser());
        newItem.setPassword(newPassword);
        newItem.setNotes(oldItem.getNotes());
        newItem.setCreatedAt(oldItem.getCreatedAt());
        newItem.setSite(oldItem.getSite());
        newItem.setPhone(oldItem.getPhone());
        itemRepository.save(newItem);
        return newItem.getSite();
    }

    public List<Item> getByUrl(String url, Long phone) {
        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        if (!accountTable.isLogin()) {
            log.warn("Need to login");
            throw new AccountNotFoundException(ResultInfoConstants.NEED_TO_LOGIN);
        }

        List<ItemTable> optionalItemTable = itemRepository.findByUrl(url, phone);
        if (optionalItemTable.isEmpty()) {
            log.warn("Item not found");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        return itemRepository.findByUrl(url, phone).stream().map(ItemTable::toItem).collect(Collectors.toList());
    }
}
