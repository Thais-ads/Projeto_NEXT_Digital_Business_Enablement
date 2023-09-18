package br.com.artcher.interfaces;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@Transactional
public interface IMyServices<T, Id> {

    @Transactional
    List<T> getAll();

    @Transactional
    @NotNull
    T save(T object);

    @Transactional
    void deleteById(Id id);

    @Transactional
    T findById(Id id);

}
