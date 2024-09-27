package orders;

import base.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends Client {

    public static final String ORDER_PATH = "orders";

    @Step("Запрос на создание заказа")
    public ValidatableResponse createOrder (Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    @Step("Запрос на получения списка заказов")
    public ValidatableResponse checkOrder() {
        return specGet()
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }
}