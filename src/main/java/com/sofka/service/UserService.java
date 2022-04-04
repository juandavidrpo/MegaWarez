package com.sofka.service;

import com.sofka.domain.Session;
import com.sofka.domain.User;
import com.sofka.repository.SessionRepository;
import com.sofka.repository.UserRepository;
import com.sofka.service.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class UserService implements IUser {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User createUser(User user) {
        user.setCreatedAt(Instant.now());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Integer id, User user) {
        user.setId(id);
        user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUsername(Integer id, User user) {
        user.setId(id);
        user.setUpdatedAt(Instant.now());
        userRepository.updateUsername(id, user.getUsername());
        return user;
    }

    @Override
    @Transactional
    public User updatePassword(Integer id, User user) {
        user.setId(id);
        user.setUpdatedAt(Instant.now());
        userRepository.updatePassword(id, user.getPassword());
        return user;
    }

    @Override
    @Transactional
    public Session logIn(User user, Session session) {
        session.setCreatedAt(Instant.now());
        session.setUser(user);
        session.setToken(user.getUsername());
        return sessionRepository.save(session);
    }

    @Override
    @Transactional
    public Session signOut() {
        return null;
    }
}
