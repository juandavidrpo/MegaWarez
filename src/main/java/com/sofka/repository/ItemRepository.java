package com.sofka.repository;

import com.sofka.domain.Item;
import com.sofka.domain.Subcategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Query(value = "SELECT item" +
            "FROM Item item" +
            "WHERE (item.name LIKE :data%)" +
            "ORDER BY item.name ASC")
    public List<Item> findByName(@Param("data") String data);

    @Query(value = "SELECT item" +
            "FROM Item item" +
            "WHERE item.subcategory = :subcategory")
    public List<Item> findAllBySubcategory(@Param(value = "subcategory")Subcategory subcategory);

    @Modifying
    @Query(value = "UPDATE Item item" +
            "SET item.name = :name" +
            "WHERE item.id = :id")
    public void updateName(@Param(value = "id") Integer id,
                           @Param(value = "name") String name);

}
