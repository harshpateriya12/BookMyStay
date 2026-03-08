package com.service;

import java.util.HashMap;

public class InventoryService {
    //use of hashmap
    HashMap<String, Integer> roomCount = new HashMap<>();
    HashMap<String, Double> roomPrice = new HashMap<>();

    public void addRoomType(String type, int count, double price) {
        roomCount.put(type, count);
        roomPrice.put(type, price);
    }

    public void updateRoomCount(String type, int count) {
        if (roomCount.containsKey(type)) {
            roomCount.put(type, count);
        } else {
            System.out.println("Room type not found");
        }
    }

    public void updateRoomPrice(String type, double price) {
        if (roomPrice.containsKey(type)) {
            roomPrice.put(type, price);
        } else {
            System.out.println("Room type not found");
        }
    }

    public void showInventory() {
        System.out.println("Room Type\tAvailable\tPrice");

        for (String type : roomCount.keySet()) {
            System.out.println(type + "\t\t" + roomCount.get(type) + "\t\t" + roomPrice.get(type));
        }
    }
}