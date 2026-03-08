package com.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AllocationService {

    private Set<String> bookedRoomIds = new HashSet<>();
    private HashMap<String, Set<String>> roomAllocations = new HashMap<>();
    
    private int reservationCounter = 100;
    public String generateReservationId() {
        reservationCounter++;
        return "RES" + reservationCounter;
    }

    // Allocate room
    public String allocateRoom(String roomType) {

        int roomNumber = 1;
        String roomId;

        do {
            roomId = roomType + "-" + roomNumber;
            roomNumber++;
        } 
        while (bookedRoomIds.contains(roomId));

        bookedRoomIds.add(roomId);

        roomAllocations.putIfAbsent(roomType, new HashSet<>());
        roomAllocations.get(roomType).add(roomId);

        return roomId;
    }

    // View allocated rooms
    public void viewAllocations() {

        if (roomAllocations.isEmpty()) {
            System.out.println("No rooms allocated yet.");
            return;
        }

        System.out.println("\nAllocated Rooms:");

        for (String type : roomAllocations.keySet()) {

            System.out.println(type + ":" + roomAllocations.get(type));
        }
    }
}