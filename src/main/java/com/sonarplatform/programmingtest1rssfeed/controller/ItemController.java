package com.sonarplatform.programmingtest1rssfeed.controller;

import com.sonarplatform.programmingtest1rssfeed.model.Item;
import com.sonarplatform.programmingtest1rssfeed.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemServiceImpl itemServiceImpl;

    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemServiceImpl.getAllItems());
        return "items";
    }

    @GetMapping("/{id}")
    public String viewItem(@PathVariable Integer id, Model model) {
        model.addAttribute("item", itemServiceImpl.getItemById(id));
        return "item";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "new-item";
    }

    @PostMapping
    public String createItem(@ModelAttribute Item item) {
        itemServiceImpl.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String editItemForm(@PathVariable Integer id, Model model) {
        model.addAttribute("item", itemServiceImpl.getItemById(id));
        return "edit-item";
    }

    @PostMapping("/{id}")
    public String updateItem(@PathVariable Integer id, @ModelAttribute Item item) {
        item.setId(id);
        itemServiceImpl.saveItem(item);
        return "redirect:/items";
    }

    @PostMapping("/{id}/delete")
    public String deleteItem(@PathVariable Integer id) {
        itemServiceImpl.deleteItem(id);
        return "redirect:/items";
    }

    @PostMapping("/fetch")
    public String fetchRss(@RequestParam String url) {
        itemServiceImpl.fetchRssFeed(url);
        return "redirect:/items";
    }

}
