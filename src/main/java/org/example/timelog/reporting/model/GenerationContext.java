package org.example.timelog.reporting.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GenerationContext {
    UserEntity employee;
    UserEntity employer;

    int year;
    int month;
}
