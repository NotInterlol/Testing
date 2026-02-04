package parking;

import java.util.HashMap;
import java.util.Map;

public class SlotManager {

	/* ------------ MODE TYPE ------------ */
	
	
    public enum Mode {
        PARK,		// Defines whether it is parking or removing a vehicle
        REMOVE
    }

    private static Mode currentMode = Mode.PARK;

    private static final Map<String, ParkingSlot> slots = new HashMap<>();

    /* ------------ MODE CONTROL ------------ */

    public static void setMode(Mode mode) {
        currentMode = mode;
    }

    public static Mode getMode() {
        return currentMode;
    }

    /* ------------ SLOT KEY ------------ */

    private static String key(String type, int slotNumber) {
        return type + "_" + slotNumber;
    }

    /* ------------ SLOT CONTROL ------------ */

    public static void registerSlot(String type, int slotNumber) {
        slots.putIfAbsent(key(type, slotNumber), new ParkingSlot(slotNumber));
    }

    public static boolean isOccupied(String type, int slotNumber) {
        ParkingSlot slot = slots.get(key(type, slotNumber));
        return slot != null && slot.isOccupied();
    }

    public static boolean occupySlot(String type, int slotNumber) {
        ParkingSlot slot = slots.get(key(type, slotNumber));
        if (slot != null && !slot.isOccupied()) {
            slot.occupy();
            return true;
        }
        return false;
    }

    public static boolean freeSlot(String type, int slotNumber) {
        ParkingSlot slot = slots.get(key(type, slotNumber));
        if (slot != null && slot.isOccupied()) {
            slot.free();
            return true;
        }
        return false;
    }
}