package com.example.imagecrud.dao;

import com.example.imagecrud.model.Image;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ImageDao {

    private final JdbcTemplate jdbcTemplate;

    public ImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Image image) {
        String sql = "INSERT INTO images (name, type, data) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, image.getName(), image.getType(), image.getData());
    }

    public Image findById(Long id) {
        String sql = "SELECT * FROM images WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToImage, id);
    }

    public List<Image> findAll() {
        String sql = "SELECT * FROM images";
        return jdbcTemplate.query(sql, this::mapRowToImage);
    }

    public void update(Long id, Image image) {
        String sql = "UPDATE images SET name = ?, type = ?, data = ? WHERE id = ?";
        jdbcTemplate.update(sql, image.getName(), image.getType(), image.getData(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM images WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Image mapRowToImage(ResultSet rs, int rowNum) throws SQLException {
        return new Image(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("type"),
                rs.getBytes("data")
        );
    }
}