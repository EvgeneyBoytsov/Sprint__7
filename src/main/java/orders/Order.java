package orders;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Order {

    private  String firstName;
    private  String lastName;
    private  String address;
    private  String metroStation;
    private  String phone;
    private  int rentTime;
    private  String deliveryDate;
    private  String comment;
    private  List<String> color;

    public static Order createOrderWithColor(List<String> colorScooter){
        String firstName = "Первый";
        String lastName = "заказ";
        String address = "Москва";
        String metroStation = "70";
        String phone = "123456789";
        int rentTime = 1;
        String deliveryDate = "2024-10-10";
        String comment = "Хочу самый красивый";

        return new Order (firstName,lastName,address,metroStation,phone,rentTime,deliveryDate,comment, colorScooter);
    }
}