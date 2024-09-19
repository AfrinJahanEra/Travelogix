public class TransportAgency {
    public String name;
    private String userName;
    private String departSpot;
    private String arriveSpot;
    private int selectedBusIndex;
    private int selectedSeatIndex;


    public String getDepartSpot() {
        return departSpot;
    }

    public void setDepartSpot(String departSpot) {
        this.departSpot = departSpot;
    }

    public String getArriveSpot() {
        return arriveSpot;
    }

    public void setArriveSpot(String arriveSpot) {
        this.arriveSpot = arriveSpot;
    }

    public int getSelectedBusIndex() {
        return selectedBusIndex;
    }

    public void setSelectedBusIndex(int selectedBusIndex) {
        this.selectedBusIndex = selectedBusIndex;
    }

    public int getSelectedSeatIndex() {
        return selectedSeatIndex;
    }

    public void setSelectedSeatIndex(int selectedSeatIndex) {
        this.selectedSeatIndex = selectedSeatIndex;
    }
}
