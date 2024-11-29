package Transport.Seat;

import java.util.ArrayList;

public class ShowSeat {
    public String displaySeatMatrix(int row, int col, ArrayList<String> bookedSeats) {

        System.err.println(" SEATS ");
        System.err.println(" ");
        char[] columns = new char[col];
        for (int i = 0; i < col; i++) {
            columns[i] = (char) ('A' + i);
        }
        StringBuilder seatLine = new StringBuilder();
        System.out.println("Seat matrix:");
        
        for (int i = 1; i <= row; i++) {
            StringBuilder seatRow = new StringBuilder();
            for (int j = 0; j < col; j++) {
                String seat = i + "" + columns[j];
                if (bookedSeats.contains(seat)) {
                    seatRow.append("[ X ] ");
                } else {
                    seatRow.append(String.format("[%3s] ", seat));
                }
            }
            seatLine.append(seatRow).append("\n");
            System.out.println(seatRow);
        }

        return seatLine.toString();
    }
}
