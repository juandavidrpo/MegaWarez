package com.sofka.repository;

import com.sofka.domain.Category;
import com.sofka.domain.Subcategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Integer> {
    @Query(value = "SELECT scat" +
            "FROM Subcategory scat" +
            "WHERE (scat.name LIKE :data%)" +
            "ORDER BY scat.name ASC")
    public List<Subcategory> findByName(@Param("data") String data);

    @Query(value = "SELECT scat" +
            "FROM Subcategory scat" +
            "WHERE scat.category = :category")
    public List<Subcategory> findAllByCategory(@Param(value = "category")Category category);

    @Modifying
    @Query(value = "UPDATE Subcategory scat" +
            "SET scat.name = :name" +
            "WHERE scat.id = :id")
    public void updateName(@Param(value = "id") Integer id,
                           @Param(value = "name") String name);
}
