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


    public UserDirectory addUser(UserDirectory userDirectory){

        return userDirectoryRepository.save(new UserDirectory(userDirectory.getName(), userDirectory.getPhoneNumber(), userDirectory.getEmailAddress(), userDirectory.getUserContacts()));


    }

    public Optional<UserDirectory> getAllContacts(String userId){

       return userDirectoryRepository.findById(userId);
    }


    public HttpStatus addContact(String userId, Contact contact) {

        Optional<UserDirectory> userDirectoryOptional = userDirectoryRepository.findById(userId);

        if (userDirectoryOptional.isPresent()) {

            UserDirectory userDirectory = userDirectoryOptional.get();

            List<Contact> contacts = userDirectory.getUserContacts();

            contacts.add(contact);

             userDirectoryRepository.save(userDirectory);
            return  HttpStatus.ACCEPTED;
        }

        else {



            return HttpStatus.BAD_REQUEST;
        }
    }

    public List<UserDirectory> getAll(){

        return userDirectoryRepository.findAll();
    }

    public HttpStatus updateUser(String userId, UserDirectory userDirectory){

       Optional<UserDirectory> userDirectoryOptional =  userDirectoryRepository.findById(userId);

       if (userDirectoryOptional.isPresent()){

           UserDirectory findUserDirectory = userDirectoryOptional.get();

           findUserDirectory.setUserContacts(userDirectory.getUserContacts());
           findUserDirectory.setEmailAddress(userDirectory.getEmailAddress());
           findUserDirectory.setPhoneNumber(userDirectory.getPhoneNumber());
           findUserDirectory.setName(userDirectory.getName());

            userDirectoryRepository.save(userDirectory);

           return  HttpStatus.ACCEPTED;
       }

       else {



           return HttpStatus.BAD_REQUEST;
       }



    }

    public void removeUser(String userId){

         userDirectoryRepository.deleteById(userId);

    }

}
