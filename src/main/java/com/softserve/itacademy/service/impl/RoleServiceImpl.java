package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        if (role == null) {
            throw new NullEntityReferenceException("role can not be null");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("role - " + id + " not found");
        }
    }

    @Override
    public Role update(Role role) {
        if (role == null) {
            throw new NullEntityReferenceException("role can not be null");
        }
        Role oldRole = readById(role.getId());
        if (oldRole == null) {
            throw new EntityNotFoundException("role - " + role.getId() + " not found");
        }
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) {
        Role role = readById(id);
        if (role == null) {
            throw new EntityNotFoundException("role - " + id + " not found");
        }
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
