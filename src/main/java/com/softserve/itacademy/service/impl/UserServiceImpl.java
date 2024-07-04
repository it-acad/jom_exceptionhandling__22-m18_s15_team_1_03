package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (user == null) {
            throw new NullEntityReferenceException("user can not be null");
        }
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("user - " + id + " not found");
        }
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new NullEntityReferenceException("user cannot be nul");
        }
        User oldUser = readById(user.getId());
        if (oldUser == null) {
            throw new EntityNotFoundException("user - " + user.getId() + " not found");
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        if (user == null) {
            throw new EntityNotFoundException("user - " + id + " not found");
        }
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
