package com.cruisecompany.dao.db;

public class Columns {
    //CRUISE
    public static final String CRUISE_ID = "cruise_id";

    public static final String CRUISE_DESCRIPTION = "description";
    public static final String TIME_DEPARTURE = "time_departure";
    public static final String DATE_DEPARTURE = "date_departure";
    public static final String DAYS_TOTAL = "days_total";
    public static final String DATE_ARRIVAL = "date_arrival";
    public static final String PRICE = "price";
    public static final String TICKETS_PURCHASED = "tickets_purchased";

    //SHIP
    public static final String SHIP_ID = "ship_id";
    public static final String SHIP_NAME = "name";
    public static final String PASSENGER_CAPACITY = "passenger_capacity";
    public static final String PHOTO_PATH = "photo_path";

    //ROUTE
    public static final String ORDER_NUMBER = "order_number";

    //STATION
    public static final String STATION_ID = "station_id";
    public static final String STATION_CITY = "city";
    public static final String STATION_COUNTRY = "country";

    //TICKET
    public static final String TICKET_ID = "ticket_id";
    public static final String PAID = "paid";
    public static final String BANNED = "banned";
    public static final String CONFIRMED = "confirmed";

    //PASSENGER
    public static final String PASSENGER_ID = "passenger_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PHONE = "phone";
    public static final String MONEY = "money";
    public static final String DOCUMENT_PATH = "document_path";

    //USER_ACCOUNT
    public static final String USER_ACCOUNT_ID = "user_account_id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String PASSWORD_SALT = "password_salt";

    //STAFF
    public static final String STAFF_ID = "staff_id";
    public static final String SPECIALITY = "speciality";

}
