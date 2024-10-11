package Bus;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Buslist {

    public void list()
    {
        try{
            File f = new File("bus.txt");
            Scanner sc = new Scanner(f);
            int i=1;
            while (sc.hasNextLine())
            {
                String data = sc.nextLine();
                String[] parts = data.split(",");
                String s0 = parts[0].trim();
                String s1 =parts[1].trim();
                String s2 = parts[2].trim();
                String businfo= s0+" from "+s1+" to "+s2;

                System.out.println(i+". "+businfo);
                i++;
            }
            sc.close();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
}