package parking;

import java.time.LocalDateTime;

public class ParkingSlot {

    private final int slotNumber;
    private boolean occupied;
    private User owner;
    private LocalDateTime parkedAt;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    } // Allows calling from other classes

    public int getSlotNumber() {
        return slotNumber;
    } // checks for the slot number in the different slots

    public boolean isOccupied() {
        return occupied;
    } // Checks whether the slot is actually occupied
    
    public User getOwner() {
        return owner;
    } // Checks which user parked
    
    void occupy(User user) {
        occupied = true;
        owner = user;
        parkedAt = LocalDateTime.now(); // saves the time when the vehicle was parked/freed
    } // Assigns that the slot is occupied
    
    public LocalDateTime getParkedAt() {
        return parkedAt;
    } // Checks when and where the user is parked

    void free() {
        occupied = false;
        owner = null;
        parkedAt = null; 
    } // Assigns that the slot is free for taking
}
