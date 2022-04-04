package com.sofka.repository;

import com.sofka.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Modifying
    @Query(value = "UPDATE User user" +
            "SET user.username = :username, user.updateAt = CURRENT_TIMESTAMP" +
            "WHERE user.id = :id")
    public void updateUsername(@Param(value = "id") Integer id,
                               @Param(value = "username") String username);

    @Modifying
    @Query(value = "UPDATE User user" +
            "SET user.password = :password, user.updatedAt = CURRENT_TIMESTAMP" +
            "WHERE user.id = :id")
    public void updatePassword(@Param(value = "id") Integer id,
                               @Param(value = "password") String password);
}
