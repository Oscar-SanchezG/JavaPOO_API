package com.esanchez;
import com.esanchez.getRepository.GetImageRepository;
import com.esanchez.getRepository.GetImageRepositoryImpl;
import com.esanchez.model.Image;
import com.esanchez.repository.ImageRepository;
import com.esanchez.repository.ImageRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GetImageRepository<Image> getRepository = new GetImageRepositoryImpl();
        ImageRepository<Image> repositoryImage = new ImageRepositoryImpl();
        System.out.println("========== List of images API =========");
        getRepository.readJson().forEach(System.out::println);
        System.out.println("========== List of images on DB =========");
        getRepository.readJson().forEach(repositoryImage::saveImg);
    }
}