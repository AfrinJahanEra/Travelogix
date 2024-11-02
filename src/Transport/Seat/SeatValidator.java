package Transport.Seat;


public class SeatValidator {
    private int row;
    private int col;

    public SeatValidator(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isSeatValid(String seat) {
        if (seat.length() < 2) return false;
        int seatRow = Integer.parseInt(seat.substring(0, seat.length() - 1));
        char seatCol = seat.charAt(seat.length() - 1);
        return seatRow >= 1 && seatRow <= row && seatCol >= 'A' && seatCol < 'A' + col;
    }
}
