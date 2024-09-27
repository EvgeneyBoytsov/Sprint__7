import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import orders.OrderChecks;
import orders.OrderClient;
import org.junit.Test;

public class ListOfOrderTest {
    private final OrderClient orderClient = new OrderClient();
    private final OrderChecks orderChecks = new OrderChecks();

    @Test
    @DisplayName("Получения списка всех заказов")
    public void checkGetOrders() {
        ValidatableResponse getOrders = orderClient.checkOrder();
        orderChecks.checkGetOrder(getOrders);
    }
}