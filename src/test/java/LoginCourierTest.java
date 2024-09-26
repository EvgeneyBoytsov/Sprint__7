import courier.Courier;
import courier.CourierCheck;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class LoginCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierCheck check = new CourierCheck();
    int courierId;
    Courier defaultCourier = new Courier("MakeVaz", "123456", "Vazowski");
    CourierCredentials defaultCredentials = new CourierCredentials("MakeVaz", "123456");

    @Test
    @DisplayName("Логирование курьера в системе")
    public void loginCourier() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var credentials = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(credentials);
        courierId = check.checkLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Логирование несуществующего курьера в системе")
    public void nonExistentCourier() {
        CourierCredentials credentials = defaultCredentials;
        ValidatableResponse loginResponse = client.logIn(credentials);
        check.checkInvalidedDataLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Логирование курьера в системе с пустым полем login")
    public void CourierWithoutLogin() {
        CourierCredentials credentials = defaultCredentials.clone();
        credentials.setLogin("");

        ValidatableResponse loginResponse = client.logIn(credentials);
        check.checkWithoutDataLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Логирование курьера в системе без поля login")
    public void CourierNullLogin() {
        CourierCredentials credentials = defaultCredentials.clone();
        credentials.setLogin(null);

        ValidatableResponse loginResponse = client.logIn(credentials);
        check.checkWithoutDataLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Логирование курьера в системе с пустым полем password")
    public void CourierWithoutPassword() {
        CourierCredentials credentials = defaultCredentials.clone();
        credentials.setPassword("");

        ValidatableResponse loginResponse = client.logIn(credentials);
        check.checkWithoutDataLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Логирование курьера в системе без поля password")
    public void CourierNullPassword() {
        CourierCredentials credentials = defaultCredentials.clone();
        credentials.setPassword(null);

        ValidatableResponse loginResponse = client.logIn(credentials);
        check.checkWithoutDataLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Логирование курьера в системе с неверным паролем")
    public void loginWithInvalidedPasswordDataCourier() {
        Courier courier = defaultCourier;
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var cred = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponses = client.logIn(cred);
        courierId = check.checkLoggedIn(loginResponses);

        CourierCredentials credentials = defaultCredentials.clone();
        credentials.setLogin("MakeVaz");
        credentials.setPassword("123");

        ValidatableResponse loginResponse = client.logIn(credentials);
        check.checkInvalidedDataLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Логирование курьера в системе с неверным логином")
    public void loginWithInvalidedLoginCourier() {
        Courier courier = defaultCourier;
        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkCreated(createResponse);

        var cred = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponses = client.logIn(cred);
        courierId = check.checkLoggedIn(loginResponses);

        CourierCredentials credentials = defaultCredentials.clone();
        credentials.setLogin("M");
        credentials.setPassword("123456");

        ValidatableResponse loginResponse = client.logIn(credentials);
        check.checkInvalidedDataLoggedIn(loginResponse);
    }

    @After
    @DisplayName("Удаление курьера")
    public void deleteCourier() {
        if (courierId != 0)
            client.delete(courierId);
    }
}