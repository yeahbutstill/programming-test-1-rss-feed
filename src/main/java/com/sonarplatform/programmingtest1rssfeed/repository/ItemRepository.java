package com.sonarplatform.programmingtest1rssfeed.repository;

import com.sonarplatform.programmingtest1rssfeed.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
