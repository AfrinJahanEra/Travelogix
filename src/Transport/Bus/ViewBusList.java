package Transport.Bus;
import File.FileHandler;

public class ViewBusList {
    private FileHandler fileHandler;

    public ViewBusList(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    public void list(int n) {
        try {
            String content = fileHandler.readFromFile();
            String[] lines = content.split("\n");


            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];

                String[] parts = line.split(",");


                String busName = parts[0].trim();
                String additional_info = parts[n].trim();
                String contact = parts[5].trim();

                String busInfo = busName + "-"+additional_info + " contact no. "+contact;
                System.out.println((i + 1) + ". " + busInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
