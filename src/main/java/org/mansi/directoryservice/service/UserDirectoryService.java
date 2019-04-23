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

        List<UserDirectory> userDirectories = userDirectoryRepository.findAll();
        for (UserDirectory userInfo : userDirectories){

            if (userInfo.getUserName().equals(userDirectory.getUserName())){
                return "UserName Already Exists";

            }
        }

        userDirectoryRepository.save(new UserDirectory(userDirectory.getUserName(), userDirectory.getPhoneNumber(), userDirectory.getEmailAddress(), userDirectory.getUserContacts()));
        return "User Added";

    }

    public UserDirectory getUserInfo(String userName){

       return userDirectoryRepository.findByUserName(userName);
    }


    public UserDirectory addContact(String userName, Contact contact) {

        UserDirectory userDirectory = userDirectoryRepository.findByUserName(userName);



            List<Contact> contacts = userDirectory.getUserContacts();

            contacts.add(contact);

            return userDirectoryRepository.save(userDirectory);

    }

    public List<UserDirectory> getAll(){

        return userDirectoryRepository.findAll();
    }

    //update user data except userName and contacts (as contacts can be update in other url)
    public UserDirectory updateUserInfo(String userName, UserDirectory userDirectory){


       UserDirectory findUserDirectory =  userDirectoryRepository.findByUserName(userName);

           findUserDirectory.setEmailAddress(userDirectory.getEmailAddress());
           findUserDirectory.setPhoneNumber(userDirectory.getPhoneNumber());

          return userDirectoryRepository.save(findUserDirectory);

    }

    public void removeUser(String userName){

        UserDirectory userDirectory =  userDirectoryRepository.findByUserName(userName);
        userDirectoryRepository.delete(userDirectory);

    }

}
