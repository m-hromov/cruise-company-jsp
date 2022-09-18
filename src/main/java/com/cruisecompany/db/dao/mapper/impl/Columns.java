package com.cruisecompany.db.dao.mapper.impl;

public class Columns {
    public static final String ID = "id";

    //CRUISE
    public static final String CRUISE_DESCRIPTION = "description";
    public static final String TIME_DEPARTURE = "time_departure";
    public static final String DATE_DEPARTURE = "date_departure";
    public static final String DAYS_TOTAL = "days_total";
    public static final String DATE_ARRIVAL = "date_arrival";
    public static final String PRICE = "price";
    public static final String ID_SHIP = "id_ship";

    //SHIP
    public static final String SHIP_NAME = "name";
    public static final String PASSENGER_CAPACITY = "passenger_capacity";
    public static final String PHOTO_PATH = "photo_path";

    //ROUTE
    public static final String ID_STATION = "id_station";
    public static final String ORDER_NUMBER = "order_number";

    //STATION
    public static final String STATION_NAME = "name";

    //PASSENGER_CRUISE
    public static final String ID_PASSENGER = "id_passenger";
    public static final String ID_CRUISE = "id_cruise";
    public static final String PAID = "paid";

    //PASSENGER
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String MONEY = "money";
    public static final String ID_USER_ACCOUNT = "id_user_account";
    public static final String DOCUMENT_PATH = "document_path";

    //USER_ACCOUNT
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    //STAFF
    public static final String SPECIALITY = "speciality";

}
