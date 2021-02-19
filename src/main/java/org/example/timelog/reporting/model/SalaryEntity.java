package org.example.timelog.reporting.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "T_SALARY")
public class SalaryEntity {

    @Id
    @Schema(name = "id", hidden = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Schema(name = "validFrom", example = "2020-10-23")
    @NotNull
    @Column(name = "valid_from")
    private LocalDate validFrom;

    @Schema(name = "validUntil")
    @NotNull
    @Column(name = "valid_until")
    private LocalDate validUntil;

    @NotNull
    @Positive
    @Column(name = "hourly_wage")
    private double hourlyWage;

    public String getUserId() {
        return userId;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

}
