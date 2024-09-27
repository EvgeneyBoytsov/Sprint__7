package courier;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CourierCredentials implements Cloneable {
    private String login;
    private String password;

    public static CourierCredentials fromCourier(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public CourierCredentials clone() {
        CourierCredentials cloneLogin = null;
        try {
            cloneLogin = (CourierCredentials) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        cloneLogin.login = login;
        cloneLogin.password = password;
        return cloneLogin;
    }
}