package Traveler;

import java.io.*;
import java.util.*;

public class TransportBrowser {

    public List<String[]> searchTransports(String startLocation, String destination, String busFile) {
        List<String[]> matchingTransports = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(busFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] busDetails = line.split(",");
                if (busDetails.length >= 8) {
                    String busStartLocation = busDetails[1].trim();
                    String busDestination = busDetails[2].trim();

                    if (busStartLocation.toLowerCase().contains(startLocation.toLowerCase()) &&
                        busDestination.toLowerCase().contains(destination.toLowerCase())) {
                        matchingTransports.add(busDetails);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading bus file: " + e.getMessage(), e);
        }
        return matchingTransports;
    }

    public void displayTransports(List<String[]> transports) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-20s %-20s %-20s %-20s %-20s ║%n",
                "Bus Name", "Starting Location", "Destination", "Starting Time", "Contact Number");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        if (transports.isEmpty()) {
            System.out.println("║ No buses found for the given route.                                               ║");
        } else {
            for (String[] busDetails : transports) {
                System.out.printf("║ %-20s %-20s %-20s %-20s %-20s ║%n",
                        busDetails[0].trim(),
                        busDetails[1].trim(),
                        busDetails[2].trim(),
                        busDetails[3].trim(),
                        busDetails[5].trim());
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

}
