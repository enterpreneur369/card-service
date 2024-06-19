package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomErrorRecordTest {

    @Test
    void testRecordConstructorAndGetters() {
        LocalDateTime dateTime = LocalDateTime.now();
        String message = "Test message";
        String details = "Test details";

        CustomErrorRecord record = new CustomErrorRecord(dateTime, message, details);

        assertThat(record.dateTime()).isEqualTo(dateTime);
        assertThat(record.message()).isEqualTo(message);
        assertThat(record.details()).isEqualTo(details);
    }

    @Test
    void testRecordEqualsAndHashCode() {
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now().plusMinutes(1);
        String message = "Test message";
        String details = "Test details";

        CustomErrorRecord record1 = new CustomErrorRecord(dateTime1, message, details);
        CustomErrorRecord record2 = new CustomErrorRecord(dateTime1, message, details);
        CustomErrorRecord record3 = new CustomErrorRecord(dateTime2, message, details);

        assertThat(record1).isEqualTo(record2);
        assertThat(record1.hashCode()).isEqualTo(record2.hashCode());
        assertThat(record1).isNotEqualTo(record3);
        assertThat(record1.hashCode()).isNotEqualTo(record3.hashCode());
    }

    @Test
    void testRecordToString() {
        LocalDateTime dateTime = LocalDateTime.now();
        String message = "Test message";
        String details = "Test details";

        CustomErrorRecord record = new CustomErrorRecord(dateTime, message, details);

        assertThat(record.toString()).contains("CustomErrorRecord")
                .contains(dateTime.toString())
                .contains(message)
                .contains(details);
    }
}
