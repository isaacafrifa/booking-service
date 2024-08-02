package iam.bookme.dto.validation;

import iam.bookme.dto.BookingDto;
import iam.bookme.dto.BookingRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class BookingValidationService {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String EMAIL_ADDRESS_CANNOT_BE_NULL = "Email address cannot be null";
    public static final String INVALID_EMAIL_ADDRESS_FORMAT = "Invalid email address format";

    public void validateBookingRequestDto(BookingRequestDto bookingRequestDto) {
        validateEmail(bookingRequestDto.getUserEmail());
        // Add other validation rules as needed
    }

    public void validateBookingDto(BookingDto bookingDto) {
        validateEmail(bookingDto.getUserEmail());
    }

    private void validateEmail(String email) {
        Assert.notNull(email, EMAIL_ADDRESS_CANNOT_BE_NULL);
        Assert.isTrue(email.matches(EMAIL_PATTERN), INVALID_EMAIL_ADDRESS_FORMAT);
    }
}
