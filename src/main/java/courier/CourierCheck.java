package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import java.util.Set;
import static java.net.HttpURLConnection.*;
import static org.junit.Assert.*;

public class CourierCheck {

    @Step("Проверка создания курьера")
    public void checkCreated(ValidatableResponse response) {
        boolean created = response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .extract()
                .path("ok");
        assertTrue(created);
    }

    @Step("Проверка создания курьера без передачи обязательных данных")
    public void checkBadRequest(ValidatableResponse response) {
        var body = response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .body().as(Map.class);
        assertEquals("Недостаточно данных для создания учетной записи", body.get("message"));
        assertEquals(Set.of("message"), body.keySet());
    }

    @Step("Проверка создания дубликата курьера")
    public void checkBadRequestDuplicate(ValidatableResponse response) {
        var body = response
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .body().as(Map.class);
        assertEquals("Этот логин уже используется", body.get("message"));
        assertEquals(Set.of("message"), body.keySet());
    }

    @Step("Проверка логирования курьера")
    public int checkLoggedIn(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("id");
        assertNotEquals(0, id);
        return id;
    }

    @Step("Проверка логирования курьера при передачи неверных данных")
    public void checkInvalidedDataLoggedIn(ValidatableResponse loginResponse) {
        var body = loginResponse
                .assertThat()
                .statusCode(HTTP_NOT_FOUND)
                .extract()
                .body().as(Map.class);
        assertEquals("Учетная запись не найдена", body.get("message"));
        assertEquals(Set.of("message"), body.keySet());
    }

    @Step("Проверка логирования курьера без передачи обязательных данных")
    public void checkWithoutDataLoggedIn(ValidatableResponse loginResponse) {
        var body = loginResponse
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .body().as(Map.class);
        assertEquals("Недостаточно данных для входа", body.get("message"));
        assertEquals(Set.of("message"), body.keySet());
    }
}