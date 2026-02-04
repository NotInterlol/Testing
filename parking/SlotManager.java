package parking;

import java.util.HashMap;
import java.util.Map;

public class SlotManager {

	/* ------------ MODE TYPE ------------ */
	
	
    public enum Mode {
        PARK,		// Defines whether it is parking or removing a vehicle
        REMOVE
    }
    
    // Default mode becomes parking
    private static Mode currentMode = Mode.PARK;
    
    // Stores slot data
    private static final Map<String, ParkingSlot> slots = new HashMap<>();

    /* ------------ MODE CONTROL ------------ */
    
    // Checks what mode to use
    public static void setMode(Mode mode) {
        currentMode = mode;
    }
    
    // Returns the mode that is being used to enable parking/unparking
    public static Mode getMode() {
        return currentMode;
    }

    /* ------------ SLOT KEY ------------ */
    
    // Selects the type of parking and slot location
    private static String key(String type, int slotNumber) {
        return type + "_" + slotNumber;
    }

    /* ------------ SLOT CONTROL ------------ */
    
    // Creates a slot so that it can be occupied or freed and avoids overwriting between slot status
    public static void registerSlot(String type, int slotNumber) {
        slots.putIfAbsent(key(type, slotNumber), new ParkingSlot(slotNumber));
    }
    
    // Checks if the slot is available or not
    public static boolean isOccupied(String type, int slotNumber) {
        ParkingSlot slot = slots.get(key(type, slotNumber));
        return slot != null && slot.isOccupied();
    }
    
    // Process for occupying the slot if its free
    public static boolean occupySlot(String type, int slotNumber, User user) {
        ParkingSlot slot = slots.get(key(type, slotNumber));
        if (slot != null && !slot.isOccupied()) {
            slot.occupy(user);
            return true;
        }
        return false;
    }
    
    // Makes sure the slot is free to be occupied once more
    public static boolean freeSlot(String type, int slotNumber) {
        ParkingSlot slot = slots.get(key(type, slotNumber));
        if (slot != null && slot.isOccupied()) {
            slot.free();
            return true;
        }
        return false;
    }
    
    public static ParkingSlot getSlot(String type, int slotNumber) {
        return slots.get(key(type, slotNumber));
    }
}