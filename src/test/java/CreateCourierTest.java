import courier.Courier;
import courier.CourierCheck;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {

    private final CourierClient client = new CourierClient();
    private final CourierCheck check = new CourierCheck();
    int courierId;
    Courier defaultCourier = Courier.random();

    @Test
    @DisplayName("Создание курьера")
    public void createCourier() {
        ValidatableResponse createResponse = client.createCourier(defaultCourier);
        check.checkCreated(createResponse);

        var credentials = CourierCredentials.fromCourier(defaultCourier);
        ValidatableResponse loginResponse = client.logIn(credentials);
        courierId = check.checkLoggedIn(loginResponse);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем login")
    public void createCourierWithoutLogin() {
        Courier courier = defaultCourier.clone();
        courier.setLogin("");

        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkBadRequest(createResponse);
    }

    @Test
    @DisplayName("Создание курьера без поля login")
    public void createCourierNullLogin() {
        Courier courier = defaultCourier.clone();
        courier.setLogin(null);

        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkBadRequest(createResponse);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем password")
    public void createCourierWithoutPassword() {
        Courier courier = defaultCourier.clone();
        courier.setPassword("");

        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkBadRequest(createResponse);
    }

    @Test
    @DisplayName("Создание курьера без поля password")
    public void createCourierNullPassword() {
        Courier courier = defaultCourier.clone();
        courier.setPassword(null);

        ValidatableResponse createResponse = client.createCourier(courier);
        check.checkBadRequest(createResponse);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем firstname")
    public void createCourierWithoutFirstName() {
        Courier courier = defaultCourier.clone();
        courier.setFirstname("");
        ValidatableResponse createResponse = client.createCourier(courier);

        var credentials = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(credentials);
        courierId = check.checkLoggedIn(loginResponse);

        check.checkBadRequest(createResponse);
    }

    @Test
    @DisplayName("Создание курьера без поля firstname")
    public void createCourierNullFirstName() {
        Courier courier = defaultCourier.clone();
        courier.setFirstname(null);
        ValidatableResponse createResponse = client.createCourier(courier);

        var credentials = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(credentials);
        courierId = check.checkLoggedIn(loginResponse);

        check.checkBadRequest(createResponse);
    }

    @Test
    @DisplayName("Создание дубликата курьера")
    @Description("Создается курьер и после проверки создания, создаем курьера с такими же данными")
    public void createDuplicateCourier() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        ValidatableResponse createResponses = client.createCourier(courier);

        var credentials = CourierCredentials.fromCourier(courier);
        ValidatableResponse loginResponse = client.logIn(credentials);
        courierId = check.checkLoggedIn(loginResponse);

        check.checkBadRequestDuplicate(createResponses);
    }

    @After
    @DisplayName("Удаление курьера")
    public void deleteCourier() {
        if (courierId != 0)
            client.delete(courierId);
    }
}