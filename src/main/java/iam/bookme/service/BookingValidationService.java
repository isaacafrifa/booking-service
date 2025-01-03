package iam.bookme.service;

import iam.bookme.dto.BookingRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class BookingValidationService {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String EMAIL_ADDRESS_CANNOT_BE_NULL = "Email address cannot be null";
    public static final String INVALID_EMAIL_ADDRESS_FORMAT = "Invalid email address format";

    public void validateBookingRequestDto(BookingRequestDto bookingRequestDto) {
        validateEmail(bookingRequestDto.getUserEmail());
        validateStartTime(bookingRequestDto);
        // Add other validation rules as needed
    }

    private void validateEmail(String email) {
        Assert.notNull(email, EMAIL_ADDRESS_CANNOT_BE_NULL);
        Assert.isTrue(email.matches(EMAIL_PATTERN), INVALID_EMAIL_ADDRESS_FORMAT);
    }

    // validate for startTime must be in the future
    private void validateStartTime(BookingRequestDto bookingRequestDto) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime startTime = bookingRequestDto.getStartTime();

        Assert.notNull(startTime, "Start time must not be null");

        // Ensure both times are in UTC for comparison
        OffsetDateTime startTimeUtc = startTime.withOffsetSameInstant(ZoneOffset.UTC);

        Assert.isTrue(startTimeUtc.isAfter(now),
                String.format("Start time %s must be in the future. Current time is %s",
                        startTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                        now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
    }




}
