package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State create(State state) {
        if (state == null) {
            throw new NullEntityReferenceException("state can not be null");
        }
        return stateRepository.save(state);
    }

    @Override
    public State readById(long id) {
        Optional<State> optional = stateRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("state - " + id + " not found");
        }
    }

    @Override
    public State update(State state) {
        if (state == null) {
            throw new NullEntityReferenceException("state can not be null");
        }
        State oldState = readById(state.getId());
        if (oldState == null) {
            throw new EntityNotFoundException("state -  " + state.getId() + " not found");
        }
        return stateRepository.save(state);
    }

    @Override
    public void delete(long id) {
        State state = readById(id);
        if (state == null) {
            throw new EntityNotFoundException("state - " + id + " not found");
        }
        stateRepository.delete(state);
    }

    @Override
    public State getByName(String name) {
        Optional<State> optional = Optional.ofNullable(stateRepository.getByName(name));
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("state - " + name + " not found");
        }
    }

    @Override
    public List<State> getAll() {
        List<State> states = stateRepository.getAll();
        return states.isEmpty() ? new ArrayList<>() : states;
    }
}
