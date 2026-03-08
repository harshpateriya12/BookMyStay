package com.service;

import java.util.Map;

public class searchService {

    private InventoryService inventoryService;

    public searchService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Search available rooms
    public void searchAvailableRooms() {

        Map<String, Integer> inventory = inventoryService.getRoomInventory();
        Map<String, Double> prices = inventoryService.getRoomPrices();

        System.out.println("\nAvailable Rooms for Guests:");

        for (String type : inventory.keySet()) {

            int count = inventory.get(type);

            if (count > 0) {

                double price = prices.get(type);

                System.out.println(type + " | Available: " + count + " | Price per night: " + price);
            }
        }
    }

    // Check if room available
    public boolean isRoomAvailable(String type) {

        Map<String, Integer> inventory = inventoryService.getRoomInventory();

        if (!inventory.containsKey(type)) {
            System.out.println("Room type does not exist.");
            return false;
        }

        if (inventory.get(type) <= 0) {
            System.out.println("Room not available.");
            return false;
        }

        return true;
    }
}