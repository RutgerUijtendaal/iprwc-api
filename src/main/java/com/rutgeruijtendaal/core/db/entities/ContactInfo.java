package com.rutgeruijtendaal.core.db.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="contact_info")
@SecondaryTables({
        @SecondaryTable(name = "phone"),
        @SecondaryTable(name = "name")
})
public class ContactInfo {

    @Id
    @Column(name="user_id")
    private int userId;

    @Column(table="phone", name="phone_number")
    private String phoneNumber;

    @Column(table="name", name="first_name")
    private String firstName;

    @Column(table="name", name="last_name")
    private String lastName;

    public ContactInfo() {

    }

    public ContactInfo(int userId, String phoneNumber, String firstName, String lastName) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return userId == that.userId &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, phoneNumber, firstName, lastName);
    }
}
