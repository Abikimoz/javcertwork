package com.example.javcertwork.controller;

import com.example.javcertwork.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final JdbcTemplate jdbcTemplate;

    public ItemController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Item> readAll() {
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, this::mapRowToItem);
    }

    @GetMapping("/{id}")
    public Item readById(@PathVariable Long id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToItem);
    }

    @PostMapping
    public void save(@RequestBody Item item) {
        String sql = "INSERT INTO items (name) VALUES (?)";
        jdbcTemplate.update(sql, item.getName());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Item item) {
        String sql = "UPDATE items SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, item.getName(), id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        String sql = "DELETE FROM items WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Item mapRowToItem(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));
        return item;
    }
}
