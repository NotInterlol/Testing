package parking;

public class ParkingSlot {

    private final int slotNumber;
    private boolean occupied;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    } // Allows calling from other classes

    public int getSlotNumber() {
        return slotNumber;
    } // checks for the slot number in the different slots

    public boolean isOccupied() {
        return occupied;
    } // Checks whether the slot is actually occupied

    void occupy() {
        occupied = true;
    } // Assigns that the slot is occupied

    void free() {
        occupied = false;
    } // Assigns that the slot is free for taking
}