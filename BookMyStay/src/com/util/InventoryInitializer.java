package com.util;

import com.service.InventoryService;

public class InventoryInitializer {

    public static void initializeRooms(InventoryService service) {

        service.addRoomType("Single", 10, 2000);
        service.addRoomType("Double", 7, 3500);
        service.addRoomType("Suite", 3, 6000);
    }
}