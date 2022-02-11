package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Item;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.repository.table.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    public List<Item> getMap(Long userId, Pageable page) {
        return itemRepository.pagination(userId, page).stream().map(ItemTable::toItem).collect(Collectors.toList());
    }

    public String insert(Item item, Long userId) {
        Long folderId = item.getFolder();
        FolderTable folderTable = folderRepository.getFolderByIdAndUserId(folderId, userId);
        if (folderTable == null) {
            log.warn("Invalid folder id");
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }

        ItemTable itemTable = item.toItemTable(folderTable.getId());
        itemTable.setUserId(userId);
        return itemRepository.save(itemTable).getSite();
    }

    public List<Item> getBySite(String site, Long userId) {

        List<ItemTable> optionalItemTable = itemRepository.findBySite(site, userId);
        if (optionalItemTable == null) {
            log.warn("Item not found");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        return itemRepository.findBySite(site, userId).stream().map(ItemTable::toItem).collect(Collectors.toList());
    }

    public String update(Item item, Long id, Long userId) {
        ItemTable oldItem = itemRepository.getItemByIdAndUserId(id, userId);
        if (oldItem == null) {
            log.warn("Item not found while updating");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }

        Long folderId = item.getFolder();
        FolderTable folderTable = folderRepository.getFolderByIdAndUserId(folderId, userId);
        if (folderTable == null) {
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }

        ItemTable newItem = item.toItemTable(folderTable.getId());
        newItem.setCreatedAt(oldItem.getCreatedAt());
        newItem.setId(oldItem.getId());
        newItem.setUserId(oldItem.getUserId());
        itemRepository.save(newItem);
        return newItem.getSite();
    }

    public String addNewPassword(Item item, Long id, Long userId) {
        ItemTable oldItem = itemRepository.getItemByIdAndUserId(id, userId);
        if (oldItem == null) {
            log.warn("Item not found while updating");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        String newPassword = item.getPassword();
        oldItem.setPassword(newPassword);
        /*ItemTable newItem = item.toItemTable(oldItem.getFolder());
        newItem.setId(oldItem.getId());
        newItem.setUrl(oldItem.getUrl());
        newItem.setUser(oldItem.getUser());
        newItem.setPassword(newPassword);
        newItem.setNotes(oldItem.getNotes());
        newItem.setCreatedAt(oldItem.getCreatedAt());
        newItem.setSite(oldItem.getSite());
        newItem.setPhone(oldItem.getPhone());*/
        itemRepository.save(oldItem);
        return oldItem.getSite();
    }

    public List<Item> getByUrl(String url, Long userId) {
        List<ItemTable> optionalItemTable = itemRepository.findByUrl(url, userId);
        if (optionalItemTable.isEmpty()) {
            log.warn("Item not found");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        return itemRepository.findByUrl(url, userId).stream().map(ItemTable::toItem).collect(Collectors.toList());
    }
}
