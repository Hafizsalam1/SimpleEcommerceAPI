package org.example.Service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IService<T> {
    public T save(T params) throws Exception;

    public Iterable<T> findAll() throws Exception;

    public Optional<T> findById(Long id) throws Exception;

    public T update(T params, Long id) throws Exception;

    public void deleteById(Long id) throws Exception;

}
