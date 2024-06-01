package com.sonarplatform.programmingtest1rssfeed.service.impl;

import com.sonarplatform.programmingtest1rssfeed.model.Item;
import com.sonarplatform.programmingtest1rssfeed.repository.ItemRepository;
import com.sonarplatform.programmingtest1rssfeed.service.ItemService;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
best practice di Spring adalah, saat kita membuat Service Layer, kita akan buat dalam bentuk Interface.
Lalu kita akan buat class implementasinya yang di registrasikan sebagai Spring Bean.
Sedangkan class yang membutuhkan Service Layer tersebut, akan menggunakan Interfacenya, bukan class implementasinya.
Salah satu keuntungan mengekspos Interface dibanding Class adalah, kita bisa mengubah atau mengganti isi dari class
implementasi, tanpa berdampak pada class lain yang menggunakan interfacenya
 */
@Service // secara otomatis class ini akan di registrasikan sebagai spring bean
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * @return
     */
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * @return
     */
    @SneakyThrows
    @Override
    public List<String> getStrings() {
        List<String> detailArticles = new ArrayList<>();
        for (Item item : itemRepository.findAll()) {
            org.jsoup.nodes.Document doc = Jsoup.connect(item.getUrl()).get();
            Elements articles = doc.selectXpath("//div[@class='post-content clearfix']");
            for (org.jsoup.nodes.Element article : articles) {
                detailArticles.add(article.text());
            }
        }
        return detailArticles;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Item getItemById(Integer id) {
        return itemRepository.findById(id).orElse(null);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * @param id
     */
    @Override
    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }

    /**
     * @param rssUrl
     */
    @Override
    public void fetchRssFeed(String rssUrl) {
        try {
            URL url = new URL(rssUrl);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String title = element.getElementsByTagName("title").item(0).getTextContent();
                String link = element.getElementsByTagName("link").item(0).getTextContent();
                String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent();
                String description = element.getElementsByTagName("description").item(0).getTextContent();
                org.jsoup.nodes.Document document = Jsoup.connect(link).get();
                Elements elements = document.selectXpath("//div[@class='post-content clearfix']");
                String content = elements.text();


                Item item = new Item();
                item.setTitle(title);
                item.setUrl(link);
                item.setContent(content);
                item.setSummary(description);
                item.setArticleTs(1L);
                item.setPublishedDate(LocalDate.parse(pubDate));
                item.setInserted(LocalDateTime.now());
                item.setUpdated(LocalDateTime.now());

                itemRepository.save(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}