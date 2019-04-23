package org.mansi.directoryservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "UsersDirectory")
public class UserDirectory {

    @Id
    private String id;
    private String userName;
    private String phoneNumber;
    private String emailAddress;

    private List<Contact> userContacts;

    public UserDirectory(String userName, String phoneNumber, String emailAddress, List<Contact> userContacts) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userContacts = userContacts;
    }


    public List<Contact> getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(List<Contact> userContacts) {
        this.userContacts = userContacts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
