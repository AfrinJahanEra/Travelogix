
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
    

            output.append("┌─────┬────────────────────┬────────────────────┬────────────────────┐\n");
            output.append(String.format("│ %-3s │ %-18s │ %-18s │ %-18s │%n", "No.", "Bus Name", "Additional Info", "Contact Number"));
            output.append("├─────┼────────────────────┼────────────────────┼────────────────────┤\n");
    

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");
    
                if (parts.length > Math.max(n, 5)) {
                    String busName = parts[0].trim();
                    String additionalInfo = parts[n].trim(); 
                    String contact = parts[5].trim(); 

                    output.append(String.format("│ %-3d │ %-18s │ %-18s │ %-18s │%n", i + 1, busName, additionalInfo, contact));
                } else {
                    output.append(String.format("│ %-3d │ %-18s │ %-18s │ %-18s │%n", i + 1, "Invalid Data", "-", "-"));
                }
            }
    
            output.append("└─────┴────────────────────┴────────────────────┴────────────────────┘\n");
        } catch (Exception e) {
            output.append("Error: Unable to read data. Please check the file or input format.\n");
            e.printStackTrace();
        }
    
        return output.toString();
    }
    
}
