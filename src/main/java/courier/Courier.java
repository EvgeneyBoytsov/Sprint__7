package courier;
import org.apache.commons.lang3.RandomStringUtils;

public class Courier implements Cloneable {

    private String login;
    private  String password;
    private  String firstname;

    public Courier(String login, String password, String firstname) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }

    public static Courier random() {
        String login = RandomStringUtils.randomAlphanumeric(5,10);
        String password = RandomStringUtils.randomAlphanumeric(5,10);
        String firstname = RandomStringUtils.randomAlphanumeric(5,10);

        return new Courier(login, password, firstname);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Courier clone() {
        Courier clone = null;
        try {
            clone = (Courier) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        clone.login = login;
        clone.password = password;
        clone.firstname = firstname;
        return clone;
    }
}