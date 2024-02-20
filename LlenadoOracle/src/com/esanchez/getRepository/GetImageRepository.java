package com.esanchez.getRepository;

import java.util.List;

public interface GetImageRepository<T> {
    List<T> readJson();
}
