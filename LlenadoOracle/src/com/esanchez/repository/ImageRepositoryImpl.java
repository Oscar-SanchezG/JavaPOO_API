package com.esanchez.repository;

import com.esanchez.model.Image;
import com.esanchez.util.ConectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImageRepositoryImpl implements ImageRepository<Image>{
    private Connection getConnection() throws SQLException {
        try {
            return ConectionDB.getInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Image> listImg() {
        return null;
    }

    @Override
    public Image byId(String id) {
        return null;
    }

    @Override
    public void saveImg(Image image) {
        String sql;

        sql = "INSERT INTO fotos(id, albumid, title, url, thumbnailUrl) VALUES(?,?,?,?,?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, image.getId());
            stmt.setLong(2, image.getAlbumId());
            stmt.setString(3,image.getTitle());
            stmt.setString(4, image.getUrl());
            stmt.setString(5, image.getThumbnailUrl());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("creando imagen en DB = " + image.getTitle());
    }

    @Override
    public void deleteImg(String id) {

    }
}
