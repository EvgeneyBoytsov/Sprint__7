package courier;

import base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends Client {

    public static final String COURIER_PATH = "courier";

    @Step("Создание курьера")
    public ValidatableResponse createCourier (Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Логин курьера в системе")
    public ValidatableResponse logIn(CourierCredentials credentials) {
        return spec()
                .body(credentials)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Удаление курьера")
    public void delete(int id) {
        spec()
                .when()
                .delete(COURIER_PATH + "/" + id)
                .then().log().all();
    }
}