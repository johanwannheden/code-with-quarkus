package org.example.timelog.reporting.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "T_USER")
public class UserEntity {
    @Id
    @Schema(name = "id", hidden = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotNull
    @Schema(name = "kind", example = "EMPLOYEE", defaultValue = "EMPLOYEE")
    @Enumerated(EnumType.STRING)
    @Column(name = "kind")
    private UserKind kind;

    @Schema(name = "employerId")
    @Column(name = "employer_id")
    private String employerId;

    @NotNull
    @Schema(name = "firstName", example = "Donald")
    @Column(name = "first_name", updatable = false)
    private String firstName;

    @NotNull
    @Schema(name = "lastName", example = "Duck")
    @Column(name = "last_name", updatable = false)
    private String lastName;

    @NotNull
    @Schema(name = "street", example = "Webfoot Walk")
    @Column(name = "street")
    private String street;

    @NotNull
    @Schema(name = "streetNumber", example = "1313")
    @Column(name = "street_number")
    private String streetNumber;

    @NotNull
    @Schema(name = "city", example = "Duckburg")
    @Column(name = "city")
    private String city;

    @NotNull
    @Schema(name = "zip", example = "54321")
    @Column(name = "zip")
    private String zip;

    @NotNull
    @Schema(name = "email", example = "donald@duck.net")
    @Column(name = "email")
    @Email
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String employeeId) {
        this.id = employeeId;
    }

    public UserKind getKind() {
        return kind;
    }

    public void setKind(UserKind kind) {
        this.kind = kind;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
