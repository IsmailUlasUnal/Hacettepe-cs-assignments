public class Seat {
    private int rowOfSeat;
    private int columnOfSeat;
    private User owner;
    private int price;

    public Seat(int rowOfSeat, int columnOfSeat, User owner, int price){
        this.rowOfSeat = rowOfSeat;
        this.columnOfSeat = columnOfSeat;
        this.owner = owner;
        this.price = price;
    }

    public int getRowOfSeat() {
        return rowOfSeat;
    }

    public int getColumnOfSeat() {
        return columnOfSeat;
    }

    public User getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public void setRowOfSeat(int rowOfSeat) {
        this.rowOfSeat = rowOfSeat;
    }

    public void setColumnOfSeat(int columnOfSeat) {
        this.columnOfSeat = columnOfSeat;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPrice(int price) {
        this.price = price;
        if(getOwner().isClubMember()){
            this.price = price * (100 - Integer.parseInt(DataReader.prop.getProperty("discount-percentage"))) / 100;
        }
    }
}
