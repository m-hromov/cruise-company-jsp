package com.cruisecompany.util.validator;

import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ValidationException;

import javax.servlet.http.Part;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Validators {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ,.'-]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-z\\d\\.-]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\+?\\(?\\d{2,4}\\)?[\\d\\s-]{3,}");
    private static final Pattern MONEY_PATTERN = Pattern.compile("^([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$");
    private static final HashSet<String> PHOTO_CONTENT_TYPE;

    static {
        PHOTO_CONTENT_TYPE = new HashSet<>();
        PHOTO_CONTENT_TYPE.add("image/jpeg");
        PHOTO_CONTENT_TYPE.add("image/png");
    }

    public static void validatePassengerProfile(PassengerDTO passenger) throws ValidationException {
        validateName(passenger.getFirstName());
        validateName(passenger.getLastName());
        validateEmail(passenger.getEmail());
        validatePhone(passenger.getPhone());
    }

    public static void validatePassenger(Passenger passenger) throws ValidationException {
        validateName(passenger.getFirstName());
        validateName(passenger.getLastName());
        validateEmail(passenger.getEmail());
        validatePhone(passenger.getPhone());
        validatePassword(passenger.getUserAccount().getPassword());
    }

    public static void validatePhoto(Part part) throws ValidationException {
        notNull(part);
        boolean matches = PHOTO_CONTENT_TYPE.stream().anyMatch(pattern ->
                pattern.matches(part.getContentType()));
        if (!matches) throw new ValidationException("Not valid!");
    }

    public static void validatePassword(String password) throws ValidationException {
        try {
            validate(password, PASSWORD_PATTERN);
        } catch (ValidationException e) {
            throw new ValidationException("Password is not valid!", e);
        }
    }

    public static void validateEmail(String email) throws ValidationException {
        try {
            validate(email, EMAIL_PATTERN);
        } catch (ValidationException e) {
            throw new ValidationException("Email is not valid!", e);
        }
    }

    public static void validatePhone(String phone) throws ValidationException {
        try {
            validate(phone, PHONE_PATTERN);
        } catch (ValidationException e) {
            throw new ValidationException("Phone is not valid!", e);
        }
    }

    public static void validateName(String name) throws ValidationException {
        try {
            validate(name, NAME_PATTERN);
        } catch (ValidationException e) {
            throw new ValidationException("Name is not valid!", e);
        }
    }

    public static void validateMoney(String money) throws ValidationException {
        try {
            validate(money, MONEY_PATTERN);
        } catch (ValidationException e) {
            throw new ValidationException("Money format is not valid!", e);
        }
    }

    private static void validate(String target, Pattern pattern) throws ValidationException {
        notNull(target);
        boolean matches = pattern.matcher(target).matches();
        if (!matches) throw new ValidationException("Not valid!");
    }

    private static void notNull(Object target) throws ValidationException {
        if (target == null) throw new ValidationException("Field is null!");
    }
}
