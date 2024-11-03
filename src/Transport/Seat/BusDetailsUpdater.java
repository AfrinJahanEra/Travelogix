package Transport.Seat;

public class BusDetailsUpdater {
    public String updateBusDetails(String[] lines, String[] originalParts, String[] bookedSeats) {
        String updatedLines = "";

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[4].trim().equalsIgnoreCase(originalParts[4].trim())) {
                updatedLines += originalParts[0]+","+originalParts[1]+","+originalParts[2]+","
                        +originalParts[3]+","+originalParts[4]+","+originalParts[5]+","+originalParts[6]+","+originalParts[7];

                for (String seat : bookedSeats) {
                    updatedLines += "," + seat;
                }
                updatedLines += "\n";
            } else {
                updatedLines += line + "\n";
            }
        }
        return updatedLines.trim();
    }
}
