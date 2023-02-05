import java.util.ArrayList;

public class Hall {

    public static Hall currentHall;

    private String name;
    private int pricePerSeat;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private int row;
    private int column;
    private ArrayList<Seat> seats;

    public Hall(String hallName, int pricePerSeat, int row, int column){
        this.name = hallName;
        this.pricePerSeat = pricePerSeat;
        this.row = row;
        this.column = column;
        seats = new ArrayList<Seat>();
    }

    public String getName() {
        return name;
    }

    public int getPricePerSeat() {
        return pricePerSeat;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Seat seat) {
        seats.add(seat);
    }
}
