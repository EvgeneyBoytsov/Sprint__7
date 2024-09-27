package courier;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@AllArgsConstructor
@Data
public class Courier implements Cloneable {

    private String login;
    private  String password;
    private  String firstname;

    public static Courier random() {
        String login = RandomStringUtils.randomAlphanumeric(5,10);
        String password = RandomStringUtils.randomAlphanumeric(5,10);
        String firstname = RandomStringUtils.randomAlphanumeric(5,10);

        return new Courier(login, password, firstname);
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