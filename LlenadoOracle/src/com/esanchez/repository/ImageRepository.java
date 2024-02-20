package com.esanchez.repository;

import java.util.List;

public interface ImageRepository<T> {
    List<T> listImg();
    T byId(String id);
    void saveImg(T t);
    void deleteImg(String id);
}
