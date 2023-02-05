import javafx.scene.image.Image;

import java.io.*;
import java.util.*;


public class DataReader {

    public static Properties prop = new Properties();

    public static int maxTry;
    public static Map<String, User> users = new HashMap<String, User>();
    public static Map<String, Film> films = new HashMap<String, Film>();

    public static File imageFile = new File("assets\\icons\\logo.png");
    public static Image logoImage = new Image(imageFile.toURI().toString());

    /**
     * reading a file and put them on right hashMap with the help of the data's first word
     *
     */
    public static void readBackup() throws FileNotFoundException {
        File backupFile = new File("assets\\data\\backup.dat");
        Scanner sc = new Scanner(backupFile);

        while(sc.hasNext()){
            String[] dataArray = sc.nextLine().split("\t");

            switch (dataArray[0]) {
                case "user":
                    userReader(dataArray);
                    break;
                case "film":
                    filmReader(dataArray);
                    break;
                case "hall":
                    hallReader(dataArray);
                    break;
                case "seat":
                    seatReader(dataArray);
                    break;
            }
        }
    }

    /**
     * reads users information and creates a user class, and put this users on a hashmap
     * @param inputArr all input line that splitted with " "
     */
    private static void userReader(String[] inputArr) {
        String name = inputArr[1];
        String password = inputArr[2];
        boolean isClubMember = Boolean.parseBoolean(inputArr[3]);
        boolean isAdmin = Boolean.parseBoolean(inputArr[4]);

        User currentUser = new User(name, password, isClubMember, isAdmin);
        users.put(name, currentUser);
    }

    /**
     * reads movies information and creates a film class and put this on films hashmap
     * @param inputArr all input line that splitted with " "
     */
    private static void filmReader(String[] inputArr) {
        String name = inputArr[1];
        String trailerPath = inputArr[2];
        int duration = Integer.parseInt(inputArr[3]);

        Film currentFilm = new Film(name, trailerPath, duration);
        films.put(name, currentFilm);
    }

    /**
     * reads halls information and creates hall class and put them on films
     * @param inputArr all input line that splitted with " "
     */
    private static void hallReader(String[] inputArr) {
        String filmName = inputArr[1];
        String hallName = inputArr[2];
        int pricePerSeat = Integer.parseInt(inputArr[3]);
        int row = Integer.parseInt(inputArr[4]);
        int column = Integer.parseInt(inputArr[5]);

        Hall currentHall = new Hall(hallName, pricePerSeat, row, column);
        films.get(filmName).setHalls(currentHall);
    }

    /**
     * read seats and creates a seats class
     * @param inputArr all input line that splitted with " "
     */
    private static void seatReader(String[] inputArr) {
        String filmName = inputArr[1];
        String hallName = inputArr[2];
        int rowOfSeat = Integer.parseInt(inputArr[3]);
        int columnOfSeat = Integer.parseInt(inputArr[4]);
        User owner = Objects.equals(inputArr[5], "null") ? null : users.get(inputArr[5]); // bu nullsa patlariz he ona gore
        int price = Integer.parseInt(inputArr[6]);

        Seat currentSeat = new Seat(rowOfSeat, columnOfSeat, owner, price);
        films.get(filmName).getHalls().get(hallName).setSeats(currentSeat);
    }

    /**
     * reads properties.dat file and load them into a Properties class that called prop
     */
    public static void propertiesReader() {
        try {
            FileInputStream fis = new FileInputStream("assets\\data\\properties.dat");
            prop.load(fis);
            maxTry = Integer.parseInt(DataReader.prop.getProperty("maximum-error-without-getting-blocked"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * try to read all file if readBackup is empty it will creates an user with username= admin password= password
     * then reads properties reader
     */
    public static void readAllFile() {
        try {
            readBackup();
        } catch (FileNotFoundException e) {
            User admin = new User("admin", User.passwordEncoder("password"), true, true);
            users.put("admin", admin);
        }
        propertiesReader();
    }
}
