public class Player implements Comparable<Player> {
    /*
    it holds player's information
     */

    public static int tourScore; //how much point does it gain from one tour

    private int totalScore; //how much point player gains total in every round

    private String username; //player's username

    public Player(){// In the beginning player's name is unknown and Player's score is 0.
        totalScore = 0;
    }

    public Player(String username){  //If we know player's username in the beginning use this constructor
        this.username = username;
        totalScore = 0;
    }

    public void setTotalScore(int totalScore) { // it appends player's score
        this.totalScore += totalScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    //It compare player's total score by descending order.
    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.getTotalScore(), totalScore);
    }
}
