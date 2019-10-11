package by.itacademy.restaurant.service;

import by.itacademy.restaurant.service.impl.RestaurantEmployeeService;
import by.itacademy.restaurant.service.impl.RestaurantVacationService;

public class ServiceProvider {

    private final static ServiceProvider serviceProvider = new ServiceProvider();

    private by.itacademy.restaurant.service.VacationService vacationService = new RestaurantVacationService();
    private by.itacademy.restaurant.service.EmployeeService employeeService = new RestaurantEmployeeService();

    private ServiceProvider(){}

    public static ServiceProvider getInstance() {
        return serviceProvider;
    }

    public by.itacademy.restaurant.service.VacationService getVacationService() {
        return vacationService;
    }

    public by.itacademy.restaurant.service.EmployeeService getEmployeeService(){
        return employeeService;
    }
}
