package com.example.repository;

import com.example.repository.entity.GreenMessageEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GreenMessageRepository {
    private final Map<String, GreenMessageEntity> store = new ConcurrentHashMap<>();

    public GreenMessageEntity save(GreenMessageEntity e) {
        store.put(e.getId(), e);
        return e;
    }

    public Optional<GreenMessageEntity> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<GreenMessageEntity> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(String id) {
        store.remove(id);
    }
}
