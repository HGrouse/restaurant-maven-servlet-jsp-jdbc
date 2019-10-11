package by.itacademy.restaurant.dao;

import by.itacademy.restaurant.bean.Order;
import by.itacademy.restaurant.bean.OrderQueue;

import java.util.List;

public interface OrderDAO {

    //realise
    void addOrder(int table);
    void editOrder(int table);

    void cancelOrder(int id);

    void addOrderQueue(OrderQueue orderQueue);
    void editOrderQueue(int id, OrderQueue orderQueue);

    List<Order> getOrderByTableNumber(int tableNumber);
    Order getOrderById(int id);

    boolean pay(int id);
}
