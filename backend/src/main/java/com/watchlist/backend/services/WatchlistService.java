package com.watchlist.backend.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.watchlist.backend.models.Watchlist;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class WatchlistService {

    private final String COLLECTION_NAME = "watchlist";

    public String saveWatchlistItem(Watchlist watchlist) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collection = firestore.collection(COLLECTION_NAME);

        // Generate unique ID if not provided
        if (watchlist.getIdItem() == null || watchlist.getIdItem().trim().isEmpty()) {
            String generatedId = collection.document().getId();
            watchlist.setIdItem(generatedId);
        }

        // Save to Firestore
        collection.document(watchlist.getIdItem()).set(watchlist);
        return "Watchlist item added successfully. ID: " + watchlist.getIdItem();
    }

    public List<Watchlist> getAllWatchlistItems() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        List<Watchlist> watchlist = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            watchlist.add(document.toObject(Watchlist.class));
        }

        return watchlist;
    }

    public Watchlist getWatchlistItemById(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentSnapshot document = firestore.collection(COLLECTION_NAME).document(id).get().get();

        if (document.exists()) {
            return document.toObject(Watchlist.class);
        } else {
            throw new IllegalArgumentException("Watchlist item not found.");
        }
    }

    public String updateWatchlistItem(String id, Watchlist watchlist) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        // Check if the item exists
        if (firestore.collection(COLLECTION_NAME).document(id).get().get().exists()) {
            watchlist.setIdItem(id); // Ensure the ID matches
            firestore.collection(COLLECTION_NAME).document(id).set(watchlist);
            return "Watchlist item updated successfully.";
        } else {
            throw new IllegalArgumentException("Watchlist item not found.");
        }
    }

    public String deleteWatchlistItem(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        // Check if the item exists
        if (firestore.collection(COLLECTION_NAME).document(id).get().get().exists()) {
            firestore.collection(COLLECTION_NAME).document(id).delete();
            return "Watchlist item deleted successfully.";
        } else {
            throw new IllegalArgumentException("Watchlist item not found.");
        }
    }
}
