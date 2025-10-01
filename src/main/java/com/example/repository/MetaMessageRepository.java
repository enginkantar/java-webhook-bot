package com.example.repository;

import com.example.repository.entity.MetaMessageEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MetaMessageRepository {
    private final Map<String, MetaMessageEntity> store = new ConcurrentHashMap<>();

    public MetaMessageEntity save(MetaMessageEntity e) {
        store.put(e.getId(), e);
        return e;
    }

    public Optional<MetaMessageEntity> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<MetaMessageEntity> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(String id) {
        store.remove(id);
    }
}
