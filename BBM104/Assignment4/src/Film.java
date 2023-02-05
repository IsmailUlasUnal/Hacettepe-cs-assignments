import java.util.HashMap;

public class Film {
    private String name;
    private String trailerPath;
    private int duration;
    private HashMap<String, Hall> halls;

    public static Film currentFilm;

    public Film(String name, String trailerPath, int duration){
        this.name = name;
        this.trailerPath = trailerPath;
        this.duration = duration;
        halls = new HashMap<String, Hall>();
    }

    public String getName() {
        return name;
    }

    public String getTrailerPath() {
        return trailerPath;
    }

    public int getDuration() {
        return duration;
    }

    public HashMap<String, Hall> getHalls() {
        return halls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setHalls(Hall hall) { //for adding hall
        halls.put(hall.getName(), hall);
    }

}
