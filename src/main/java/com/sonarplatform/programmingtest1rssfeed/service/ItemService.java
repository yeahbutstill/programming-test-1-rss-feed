package com.sonarplatform.programmingtest1rssfeed.service;

import com.sonarplatform.programmingtest1rssfeed.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    List<String> getStrings();

    Item getItemById(Integer id);

    Item saveItem(Item item);

    void deleteItem(Integer id);

    void fetchRssFeed(String rssUrl);
}
