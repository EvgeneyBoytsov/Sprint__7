package courier;

public class CourierCredentials implements Cloneable {
    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials fromCourier(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
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