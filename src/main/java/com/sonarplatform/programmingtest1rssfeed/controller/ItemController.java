package com.sonarplatform.programmingtest1rssfeed.controller;

import com.sonarplatform.programmingtest1rssfeed.model.Item;
import com.sonarplatform.programmingtest1rssfeed.repository.ItemRepository;
import com.sonarplatform.programmingtest1rssfeed.service.impl.ItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private ItemRepository itemRepository;

    /***
     * http://localhost:8001/sonar/hello?name=DNL
     * @param httpServletRequest untuk set name dari req
     * @param httpServletResponse untuk get name dari res
     * @throws IOException
     */
    @GetMapping("/hello") // Request method GET
    public void hello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String name = httpServletRequest.getParameter("name");
        // kalau objectnya null si name maka name set jadi "Sonar"
        if (Objects.isNull(name)) name = "Sonar";
        httpServletResponse.getWriter().println("Hello " + name + "!");
    }

    @GetMapping("/hello_mock_bean")
    public void helloGetHelloService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String name = httpServletRequest.getParameter("name");
        String responseBody = itemService.hello(name);
        httpServletResponse.getWriter().println(responseBody);
    }

    /***
     * @RequestParam dengan annotation ini, kita memberitahu bahwa kita membutuhkan request parameter
     * selain itu kita bisa menambahkan apakah query parameter itu wajib atau tidak, dan juga bisa
     * menambahkan default valuenya jika tidak dikirim oleh user.
     * Secara atomatis data request parameter akan dikirim datanya ke parameter yang kita tentukan.
     * @param name, required = false (artinya ini tidak wajib) String name, artinya request parameter ini datanya
     *              akan dimasukan kedalam String name.
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping("/hello_request_param")
    public void helloRequestParam(@RequestParam(name = "name", required = false) String name,
                                  HttpServletResponse httpServletResponse) throws IOException {
        String responseBody = itemService.hello(name);
        httpServletResponse.getWriter().println(responseBody);
    }

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "items";
    }

    @GetMapping("/{id}")
    public String viewItem(@PathVariable Integer id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        return "item";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "new-item";
    }

    @PostMapping
    public String createItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String editItemForm(@PathVariable Integer id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        return "edit-item";
    }

    @PostMapping("/{id}")
    public String updateItem(@PathVariable Integer id, @ModelAttribute Item item) {
        item.setId(id);
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @PostMapping("/{id}/delete")
    public String deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return "redirect:/items";
    }

    @PostMapping("/fetch")
    public String fetchRss(@RequestParam String url) {
        itemService.fetchRssFeed(url);
        return "redirect:/items";
    }

}
