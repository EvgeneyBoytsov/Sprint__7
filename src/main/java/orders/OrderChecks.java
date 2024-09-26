package orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import static java.net.HttpURLConnection.*;
import static org.junit.Assert.*;

public class OrderChecks {

    @Step("Проверка создания заказа")
    public void checkCreateOrder(ValidatableResponse createOrderResponse) {
        int track = createOrderResponse
                .assertThat()
                .statusCode(HTTP_CREATED)
                .extract()
                .path("track")
                ;

        assertNotEquals(0, track);
    }

    @Step("Проверка получения списка заказов")
    public void checkGetOrder(ValidatableResponse createOrderResponse) {
        List<Object> body = createOrderResponse
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("orders");

        assertFalse(body.isEmpty());
    }
}