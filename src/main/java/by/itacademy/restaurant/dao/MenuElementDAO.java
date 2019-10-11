package by.itacademy.restaurant.dao;

import by.itacademy.restaurant.bean.menu.MenuElement;

import java.util.List;

public interface MenuElementDAO {

    //realise
    void addDrink(MenuElement menuElement);
    void editMenuElement(int id, MenuElement menuElement);
    void ChangeStatus(int id, boolean status);

    List<MenuElement> getMenuElementsByType (String type);
    List<MenuElement> getMenuElementsByName (String name);


}
