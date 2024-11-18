// package Transport.Bus;

// import Utilities.FileManager.File.*;

// public class ViewBusList {
//     private FileHandler fileHandler;

//     public ViewBusList(String filePath) {
//         this.fileHandler = new FileHandler(filePath);
//     }

//     public String list(int n) {
//         String output = "";
//         try {
//             String content = fileHandler.readFromFile();
//             String[] lines = content.split("\n");

//             for (int i = 0; i < lines.length; i++) {
//                 String line = lines[i];
//                 String[] parts = line.split(",");

//                 String busName = parts[0].trim();
//                 String additional_info = parts[n].trim();
//                 String contact = parts[5].trim();

//                 String busInfo = busName + "-" + additional_info + " contact no. " + contact;
//                 output +=(i + 1) + ". " + busInfo + "\n";
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return output;
//     }
// }
// }
package Transport.Bus;

import Utilities.FileManager.File.*;

public class ViewBusList {
    private FileHandler fileHandler;

    public ViewBusList(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    public String list(int n) {
        StringBuilder output = new StringBuilder();

        try {
            String content = fileHandler.readFromFile();
            String[] lines = content.split("\n");

            // Add a title row for headers
            output.append(String.format("%-5s %-20s %-20s %-20s%n", "No.", "Bus Name", "Additional Info", "Contact Number"));
            output.append("------------------------------------------------------------\n");

            // Format each row with column spacing
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");

                String busName = parts[0].trim();
                String additionalInfo = parts[n].trim();
                String contact = parts[5].trim();

                output.append(String.format("%-5d %-20s %-20s %-20s%n", i + 1, busName, additionalInfo, contact));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
