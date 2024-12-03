package com.watchlist.backend.controllers;

import com.watchlist.backend.models.Watchlist;
import com.watchlist.backend.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping
    public ResponseEntity<String> saveWatchlistItem(@RequestBody Watchlist watchlist) {
        try {
            String response = watchlistService.saveWatchlistItem(watchlist);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Watchlist>> getAllWatchlistItems() {
        try {
            List<Watchlist> watchlist = watchlistService.getAllWatchlistItems();
            return ResponseEntity.ok(watchlist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Watchlist> getWatchlistItemById(@PathVariable String id) {
        try {
            Watchlist watchlist = watchlistService.getWatchlistItemById(id);
            return ResponseEntity.ok(watchlist);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWatchlistItem(@PathVariable String id, @RequestBody Watchlist watchlist) {
        try {
            Watchlist existingItem = watchlistService.getWatchlistItemById(id);

            // Préserver les champs existants si non spécifiés dans la requête
            if (watchlist.getType() == null || watchlist.getType().isEmpty()) {
                watchlist.setType(existingItem.getType());
            }
            if (watchlist.getComment() == null || watchlist.getComment().isEmpty()) {
                watchlist.setComment(existingItem.getComment());
            }

            String response = watchlistService.updateWatchlistItem(id, watchlist);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWatchlistItem(@PathVariable String id) {
        try {
            String response = watchlistService.deleteWatchlistItem(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
