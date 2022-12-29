package com.cruisecompany.util.validator;

import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.http.Part;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorsTest {

    @Test
    void testValidatePassengerProfile() throws ValidationException {
        try(MockedStatic<Validators> mocked = Mockito.mockStatic(Validators.class)) {
            PassengerDTO passengerDTO = PassengerDTO.builder()
                    .firstName("Maksym")
                    .build();
            mocked.when(()->Validators.validatePassengerProfile(passengerDTO)).thenCallRealMethod();
            Validators.validatePassengerProfile(passengerDTO);
            mocked.verify(()->Validators.validateName(passengerDTO.getFirstName()),Mockito.times(1));
            mocked.verify(()->Validators.validateName(passengerDTO.getLastName()),Mockito.times(1));
            mocked.verify(()->Validators.validateEmail(null),Mockito.times(1));
            mocked.verify(()->Validators.validatePhone(null),Mockito.times(1));
        }
    }

    @Test
    void testValidatePassenger() throws ValidationException {
        try(MockedStatic<Validators> mocked = Mockito.mockStatic(Validators.class)) {
            Passenger passenger = Passenger.builder()
                    .firstName("Maksym")
                    .userAccount(UserAccount.builder().build())
                    .build();
            mocked.when(()->Validators.validatePassenger(passenger)).thenCallRealMethod();
            Validators.validatePassenger(passenger);
            mocked.verify(()->Validators.validateName(passenger.getFirstName()),Mockito.times(1));
            mocked.verify(()->Validators.validateName(passenger.getLastName()),Mockito.times(1));
            mocked.verify(()->Validators.validateEmail(null),Mockito.times(1));
            mocked.verify(()->Validators.validatePhone(null),Mockito.times(1));
            mocked.verify(()->Validators.validatePassword(null),Mockito.times(1));
        }
    }

    @Test
    void testValidatePhoto() {
        Part part = Mockito.mock(Part.class);
        Mockito.when(part.getContentType()).thenReturn("image/jpeg");
        assertDoesNotThrow(()->Validators.validatePhoto(part));
        Mockito.when(part.getContentType()).thenReturn("image/png");
        assertDoesNotThrow(()->Validators.validatePhoto(part));
    }

    @Test
    void testValidatePassword() {
        assertDoesNotThrow(()->Validators.validatePassword("Mgrgrgrg00125"));
        assertDoesNotThrow(()->Validators.validatePassword("fdfdfd102"));
    }

    @Test
    void testValidateEmail() {
        assertDoesNotThrow(()->Validators.validateEmail("test123@gmail.com"));
        assertDoesNotThrow(()->Validators.validateEmail("test-123@gmail.ua"));
        assertDoesNotThrow(()->Validators.validateEmail("te.st-123@gmail.ua"));
    }

    @Test
    void testValidatePhone() {
        assertDoesNotThrow(()->Validators.validatePhone("+380923233232"));
        assertDoesNotThrow(()->Validators.validatePhone("0923233232"));
    }

    @Test
    void testValidateName() {
        assertDoesNotThrow(()->Validators.validateName("Maksym"));
        assertDoesNotThrow(()->Validators.validateName("Leo nar-Do"));
    }

    @Test
    void testValidateMoney() {
        assertDoesNotThrow(()->Validators.validateMoney("100"));
        assertDoesNotThrow(()->Validators.validateMoney("100.0"));
        assertDoesNotThrow(()->Validators.validateMoney("100.25"));
        assertDoesNotThrow(()->Validators.validateMoney("100.25"));
    }

    @Test
    void testValidatePhotoFails() {
        Part part = Mockito.mock(Part.class);
        Mockito.when(part.getContentType()).thenReturn("docx");
        assertThrows(ValidationException.class,()->Validators.validatePhoto(part));
        Mockito.when(part.getContentType()).thenReturn("txt");
        assertThrows(ValidationException.class,()->Validators.validatePhoto(part));
    }

    @Test
    void testValidatePasswordFails() {
        assertThrows(ValidationException.class, ()->Validators.validatePassword("-fkr_f"));
        assertThrows(ValidationException.class, ()->Validators.validatePassword(null));
    }

    @Test
    void testValidateEmailFails() {
        assertThrows(ValidationException.class, ()->Validators.validateEmail("hhhhh@gm"));
        assertThrows(ValidationException.class, ()->Validators.validateEmail("hhhhh@gmai."));
        assertThrows(ValidationException.class, ()->Validators.validateEmail("hhhhh@gmai.u"));
        assertThrows(ValidationException.class, ()->Validators.validateEmail("hhhh$h@gmai.u"));
        assertThrows(ValidationException.class, ()->Validators.validateMoney(null));
    }

    @Test
    void testValidatePhoneFails() {
        assertThrows(ValidationException.class, ()->Validators.validatePhone("-12f"));
        assertThrows(ValidationException.class, ()->Validators.validatePhone("+3892"));
        assertThrows(ValidationException.class, ()->Validators.validatePhone(null));
    }

    @Test
    void testValidateNameFails() {
        assertThrows(ValidationException.class, ()->Validators.validateName("Mak12"));
        assertThrows(ValidationException.class, ()->Validators.validateName(null));
    }

    @Test
    void testValidateMoneyFails() {
        assertThrows(ValidationException.class, ()->Validators.validateMoney("-12"));
        assertThrows(ValidationException.class, ()->Validators.validateMoney(null));
    }
}