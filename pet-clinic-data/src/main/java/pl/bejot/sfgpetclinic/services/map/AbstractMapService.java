package pl.bejot.sfgpetclinic.services.map;

import pl.bejot.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T t) {
        if (t == null) {
            throw new RuntimeException("Object cannot be null!");
        }

        if (t.getId() == null) {
            t.setId(getNextId());
        }

        map.put(t.getId(), t);

        return t;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T t) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(t));
    }

    private Long getNextId() {
        long nextId;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException: Empty map, nextId is 1.");
            nextId = 1L;
        }

        return nextId;
    }
}
