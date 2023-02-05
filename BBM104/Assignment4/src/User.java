import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class User {

    public static User currentUser;

    private String name;
    private String password;
    private boolean isClubMember;
    private boolean isAdmin;


    public User(String name, String password, boolean isClubMember, boolean isAdmin){
        this.name = name;
        this.password = password;
        this.isClubMember = isClubMember;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isClubMember() {
        return isClubMember;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setClubMember(boolean clubMember) {
        isClubMember = clubMember;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }



    /**
     * Returns Base64 encoded version of MD5 hashed version of the given password.
     *
     * @param password Password to be hashed.
     * @return Base64 encoded version of MD5 hashed version of password
     */
    public static String passwordEncoder(String password) {
        byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
        byte[] md5Digest = new byte[0];
        try {
            md5Digest = MessageDigest.getInstance("MD5").digest(bytesOfPassword);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return Base64.getEncoder().encodeToString(md5Digest);
    }

}
