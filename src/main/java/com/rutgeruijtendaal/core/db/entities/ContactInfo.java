package com.rutgeruijtendaal.core.db.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="contact_info")
@SecondaryTables({
        @SecondaryTable(name = "name"),
        @SecondaryTable(name = "address"),
        @SecondaryTable(name = "zip_code")
})
public class ContactInfo {

    @Id
    @Column(name="user_id")
    private int userId;

    @Column(table="name", name="first_name")
    private String firstName;

    @Column(table="name", name="last_name")
    private String lastName;

    @Column(table="address", name="country")
    private String country;

    @Column(table="address", name="street_name")
    private String streetName;

    @Column(table="address", name="house_number")
    private int houseNumber;

    @Column(table="zip_code", name="zip_letters")
    private String zipLetters;

    @Column(table="zip_code", name="zip_numbers")
    private String zipNumbers;

    public ContactInfo() {

    }

    public ContactInfo(int userId, String firstName, String lastName, String country, String streetName, int houseNumber, String zipLetters, String zipNumbers) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipLetters = zipLetters;
        this.zipNumbers = zipNumbers;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipLetters() {
        return zipLetters;
    }

    public void setZipLetters(String zipLetters) {
        this.zipLetters = zipLetters;
    }

    public String getZipNumbers() {
        return zipNumbers;
    }

    public void setZipNumbers(String zipNumbers) {
        this.zipNumbers = zipNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return userId == that.userId &&
                houseNumber == that.houseNumber &&
                zipNumbers == that.zipNumbers &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                country.equals(that.country) &&
                streetName.equals(that.streetName) &&
                zipLetters.equals(that.zipLetters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, country, streetName, houseNumber, zipLetters, zipNumbers);
    }
}
