// BusDetails.java
package Bus;

public class BusDetails {
    public String busName;
    public String startingLocation;
    public String endingLocation;
    public String numberPlate;
    public String seatMatrix;

    public BusDetails(String busName, String startingLocation, String endingLocation, String numberPlate, String seatMatrix) {
        this.busName = busName;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.numberPlate = numberPlate;
        this.seatMatrix = seatMatrix;
    }

    // Optional: you can add getters and setters here
}
