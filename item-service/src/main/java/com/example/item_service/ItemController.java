package com.example.item_service;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private List<Map<String,Object>> items = new ArrayList<>();
    private int idCounter = 1;

    @GetMapping
    public List<Map<String,Object>> getItems() {
        return items;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> addItem(@RequestBody Map<String,Object> item) {
        item.put("id", idCounter++);
        items.add(item);
        return ResponseEntity.status(201).body(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable int id) {
        return items.stream()
                .filter(i -> i.get("id").equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}