package com.service;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private HashMap<String, Integer> roomInventory = new HashMap<>();
    private HashMap<String, Double> roomPrices = new HashMap<>();

    // Add room type
    public void addRoomType(String type, int count, double price) {

        roomInventory.put(type, count);
        roomPrices.put(type, price);

        System.out.println(type + " room added successfully.");
    }

    // Update room count
    public void updateRoomCount(String type, int count) {

        if (roomInventory.containsKey(type)) {
            roomInventory.put(type, count);
            System.out.println("Room count updated.");
        } else {
            System.out.println("Room type not found.");
        }
    }

    // Update room price
    public void updateRoomPrice(String type, double price) {

        if (roomPrices.containsKey(type)) {
            roomPrices.put(type, price);
            System.out.println("Room price updated.");
        } else {
            System.out.println("Room type not found.");
        }
    }

    // Read-only access for search service
    public Map<String, Integer> getRoomInventory() {
        return roomInventory;
    }

    public Map<String, Double> getRoomPrices() {
        return roomPrices;
    }

    // Show inventory (Admin view)
    public void showAvailability() {

        System.out.println("\nHotel Inventory:");

        for (String type : roomInventory.keySet()) {

            int count = roomInventory.get(type);
            double price = roomPrices.get(type);

            System.out.println(type + " | Rooms: " + count + " | Price: " + price);
        }
    }
}