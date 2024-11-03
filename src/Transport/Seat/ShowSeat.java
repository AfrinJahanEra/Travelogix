package Transport.Seat;


import java.util.ArrayList;

public class ShowSeat {
    public String displaySeatMatrix(int row, int col, ArrayList<String> bookedSeats) {
        char[] columns = new char[col];
        for (int i = 0; i < col; i++) {
            columns[i] = (char) ('A' + i);
        }
        String seatline = "";
        System.out.println("Seat matrix:");
        for (int i = 1; i <= row; i++) {
            String seatshow = "";
            for (int j = 0; j < col; j++) {
                String seat = i + "" + columns[j];
                if (bookedSeats.contains(seat)) {
                    seatshow+="[X] ";
                } else {
                    seatshow+="[" + seat + "] ";
                }
            }
            seatline+=seatshow+"\n";
            System.out.println(seatshow);
        }

        return seatline;
    }
}
