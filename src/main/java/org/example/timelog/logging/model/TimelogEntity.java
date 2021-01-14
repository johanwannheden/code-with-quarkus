package org.example.timelog.logging.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "TIMELOG")
public class TimelogEntity {

    @Schema(hidden = true)
    @Id
    @SequenceGenerator(name = "timelogSeq", sequenceName = "timelog_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "timelogSeq")
    private Long id;

    @Schema(hidden = true)
    @Version
    private short version;

    @Schema(name = "date", example = "2020-10-23")
    @NotNull
    @PastOrPresent
    private LocalDate date;

    @Schema(name = "dateAdded", example = "2020-10-23T20:01:10")
    @NotNull
    @PastOrPresent
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Schema(name = "dateUpdated", example = "2020-10-23T20:01:10")
    @NotNull
    @PastOrPresent
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @Schema(name = "startTime", pattern = "\\d{2}:\\d{2}", example = "08:30")
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{2}:\\d{2}")
    @Column(name = "start_time")
    private LocalTime startTime;

    @Schema(name = "endTime", pattern = "\\d{2}:\\d{2}", example = "17:15")
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{2}:\\d{2}")
    @Column(name = "end_time")
    private LocalTime endTime;

    @Schema(name = "comment", example = "A looooong day")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
