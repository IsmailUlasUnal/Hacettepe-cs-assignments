import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    /**
     * this function write a data to a src file and call:
     * userWriter for user informations
     * filmWriter for film informations
     */
    public static void writeBackup() throws IOException {
        File writeFile = new File("assets\\data\\backup.dat");
        FileWriter writer = new FileWriter(writeFile);

        userWriter(writer);
        filmWriter(writer);
        writer.close();
    }

    /**
     * write a users information
     * @param writer writes an information here
     */
    private static void userWriter(FileWriter writer) throws IOException {
        for(User user: DataReader.users.values()){
            writer.write("user\t"
                    + user.getName() + "\t"
                    + user.getPassword() + "\t"
                    + user.isClubMember() + "\t"
                    + user.isAdmin() + "\n");
        }
    }

    /**
     * write a film infomration
     * @param writer writes an information here
     * @throws IOException
     */
    private static void filmWriter(FileWriter writer) throws IOException {
        for(Film film: DataReader.films.values()){
            writer.write("film\t"
                    + film.getName() + "\t"
                    + film.getTrailerPath() + "\t"
                    + film.getDuration() + "\n");
            hallWriter(writer, film);
        }
    }

    /**
     *
     * @param writer writes an information here
     * @param film for current film, while writing its name
     * @throws IOException
     */
    private static void hallWriter(FileWriter writer, Film film) throws IOException{
        for(Hall hall: film.getHalls().values()){
            writer.write(("hall\t"
                    + film.getName() + "\t"
                    + hall.getName() + "\t"
                    + hall.getPricePerSeat() + "\t"
                    + hall.getRow() + "\t"
                    + hall.getColumn() + "\n"));
            seatWriter(writer, film, hall);
        }
    }

    /**
     *
     * @param writer writes an information here
     * @param film current film
     * @param hall current hall
     */
    private static void seatWriter(FileWriter writer,Film film, Hall hall) throws IOException {
        for(Seat seat: hall.getSeats()){
            String owner = (seat.getOwner() == null) ? "null" : seat.getOwner().getName();

            writer.write("seat\t"
                    + film.getName() + "\t"
                    + hall.getName() + "\t"
                    + seat.getRowOfSeat() + "\t"
                    + seat.getColumnOfSeat() + "\t"
                    + owner + "\t"
                    + seat.getPrice() + "\n");
        }
    }
}
