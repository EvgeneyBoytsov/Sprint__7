import io.qameta.allure.junit4.DisplayName;
import orders.OrderChecks;
import orders.OrderClient;
import io.restassured.response.ValidatableResponse;
import orders.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

@RunWith(Parameterized.class)
public class OrderTest {
    private final OrderClient orderClient = new OrderClient();
    private final OrderChecks orderChecks = new OrderChecks();

    private final List<String> colorScooter;

    public OrderTest(List<String> colorScooter) {
        this.colorScooter = colorScooter;
    }

    @Parameterized.Parameters
    public static Object[] getOrderCreation() {
        return new Object[][]{
                {List.of()},
                {List.of("BLACK", "GREY")},
                {List.of("GREY")},
                {List.of("BLACK")},
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void createdOrder() {
        Order order = Order.createOrderWithColor(colorScooter);
        ValidatableResponse createOrderResponse = orderClient.createOrder(order);
        orderChecks.checkCreateOrder(createOrderResponse);
    }

}