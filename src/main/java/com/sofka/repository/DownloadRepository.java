package com.sofka.repository;

import com.sofka.domain.Download;
import com.sofka.domain.Item;
import com.sofka.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DownloadRepository extends CrudRepository<Download, Integer> {

    @Query(value = "SELECT dwn" +
            "FROM Download dwn" +
            "WHERE dwn.user = :user")
    public List<Download> findAllByUser(@Param(value = "user") User user);

    @Query(value = "SELECT dwn" +
            "FROM Download dwn" +
            "WHERE dwn.item = :item")
    public List<Download> findAllByUser(@Param(value = "item")Item item);

}
