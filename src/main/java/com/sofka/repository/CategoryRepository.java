package com.sofka.repository;

import com.sofka.domain.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query(value = "SELECT cat" +
            "FROM Category cat" +
            "WHERE (cat.name LIKE :data%)" +
            "ORDER BY cat.name ASC")
    public List<Category> findByName(@Param("data") String data);

    @Modifying
    @Query(value = "UPDATE Category cat" +
            "SET cat.name = :name" +
            "WHERE cat.id = :id")
    public void updateName(@Param(value = "id") Integer id,
                           @Param(value = "name") String name);
}
