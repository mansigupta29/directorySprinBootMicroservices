package org.mansi.directoryservice.service;

import org.mansi.directoryservice.models.Contact;
import org.mansi.directoryservice.models.UserDirectory;
import org.mansi.directoryservice.repository.UserDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDirectoryService {

    @Autowired
    private UserDirectoryRepository userDirectoryRepository;


    public String addUser(UserDirectory userDirectory){

        String userName = userDirectory.getUserName();
        String phoneNum = userDirectory.getPhoneNumber();
        String email = userDirectory.getEmailAddress();
        List<Contact> contacts = userDirectory.getUserContacts();

        if (userName!=null) {
            List<UserDirectory> userDirectories = userDirectoryRepository.findAll();
            for (UserDirectory userInfo : userDirectories) {

                if (userInfo.getUserName().equals(userName)) {
                    return "UserName Already Exists";

                }
            }

            userDirectoryRepository.save(new UserDirectory(userName, phoneNum, email, contacts));
            return "User Added";
        }
        return "must have username";

    }

    public UserDirectory getUserInfo(String userName){

       return userDirectoryRepository.findByUserName(userName);
    }


    public UserDirectory addContact(String userName, Contact contact) {

        String phoneNum = contact.getPhoneNumber();
        String name = contact.getName();

        UserDirectory userDirectory = userDirectoryRepository.findByUserName(userName);

        Contact contactObj = new Contact();

            List<Contact> contacts = userDirectory.getUserContacts();

            if (phoneNum!=null && name!=null) {

                    contacts.add(contact);
            }
                return userDirectoryRepository.save(userDirectory);


    }

    public List<UserDirectory> getAll(){

        return userDirectoryRepository.findAll();
    }

    //update user data except userName and contacts (as contacts can be update in other url)
    public UserDirectory updateUserInfo(String userName, UserDirectory userDirectory){

        String email = userDirectory.getEmailAddress();
        String phone = userDirectory.getPhoneNumber();

       UserDirectory findUserDirectory =  userDirectoryRepository.findByUserName(userName);

           if (email !=null) findUserDirectory.setEmailAddress(email);
        if (phone !=null) findUserDirectory.setPhoneNumber(phone);

          return userDirectoryRepository.save(findUserDirectory);

    }

    public void removeUser(String userName){

        UserDirectory userDirectory =  userDirectoryRepository.findByUserName(userName);
        userDirectoryRepository.delete(userDirectory);

    }

}
