package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Item;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.repository.table.FolderRepository;
import com.example.demo.repository.table.FolderTable;
import com.example.demo.repository.table.ItemRepository;
import com.example.demo.repository.table.ItemTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FolderRepository folderRepository;

    public List<Item> getMap() {
        return itemRepository.findAll().stream().map(ItemTable::toItem).collect(Collectors.toList());
    }

    public String insert(Item item) {
        if (itemRepository.existsById(item.getSite())) {
            log.warn("Item already existing");
            throw new DuplicateKeyException(ResultInfoConstants.ITEM_ALREADY_EXISTING);
        }

        String folderName = item.getFolder();
        FolderTable folderTable = folderRepository.getFolder(folderName);
        if (folderTable == null) {
            log.warn("Invalid folder name");
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }

        return itemRepository.save(item.toItemTable(folderTable.getFolder())).getSite();
    }

    public Item getBySite(String site) {
        Optional<ItemTable> optionalItemTable = itemRepository.findById(site);
        if (!optionalItemTable.isPresent()) {
            log.warn("Item not found");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        return optionalItemTable.get().toItem();
    }

    public String update(Item item, String site) {
        Optional<ItemTable> oldItem = itemRepository.findById(site);
        if (!oldItem.isPresent()) {
            log.warn("Item not found while updating");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        String folderName = item.getFolder();
        FolderTable folderTable = folderRepository.getFolder(folderName);
        if (folderTable == null) {
            throw new AccountNotFoundException(ResultInfoConstants.FOLDER_NOT_FOUND);
        }

        ItemTable newItem = item.toItemTable(folderTable.getFolder());

        newItem.setCreatedAt(oldItem.get().getCreatedAt());
        newItem.setSite(oldItem.get().getSite());
        itemRepository.save(newItem);
        return newItem.getSite();
    }

    public String delete(String site) {
        if (!itemRepository.existsById(site)) {
            log.warn("Invalid site");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        itemRepository.deleteById(site);
        return site;
    }

    public String addNewPassword(Item item, String site) {
        Optional<ItemTable> oldItem = itemRepository.findById(site);
        if (!oldItem.isPresent()) {
            log.warn("Item not found while updating");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        String newPassword = item.getPassword();
        ItemTable newItem = item.toItemTable(oldItem.get().getFolder());
        newItem.setUrl(oldItem.get().getUrl());
        newItem.setUser(oldItem.get().getUser());
        newItem.setPassword(newPassword);
        newItem.setNotes(oldItem.get().getNotes());
        newItem.setCreatedAt(oldItem.get().getCreatedAt());
        newItem.setSite(oldItem.get().getSite());
        itemRepository.save(newItem);

        return newItem.getSite();
    }

    public Item getByUrl(String url) {
        ItemTable optionalItemTable = itemRepository.findByUrl(url);
        if (optionalItemTable == null) {
            log.warn("Item not found");
            throw new AccountNotFoundException(ResultInfoConstants.NOT_FOUND);
        }
        return optionalItemTable.toItem();
    }
}
