package org.mansi.directoryservice.service;

import org.mansi.directoryservice.models.Contact;
import org.mansi.directoryservice.models.UserDirectory;
import org.mansi.directoryservice.repository.UserDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ContactService {

    @Autowired
    private UserDirectoryRepository userDirectoryRepository;



    public List<Contact> findContact(String userId, String  contact){

        List<Contact> contact2 = new ArrayList<>();

        Optional<UserDirectory> userDirectoryOptional = userDirectoryRepository.findById(userId);

        if (userDirectoryOptional.isPresent()) {

            UserDirectory userDirectory = userDirectoryOptional.get();
            List<Contact> contactList= userDirectory.getUserContacts();
            for (Contact contact1: contactList){
                if (contact1.getName().equals(contact)) {

                    contact2.add(contact1);
                }

            }


        }
        return contact2;


    }

    public HttpStatus updateContact(String userId, String contactName, Contact contact){


        Optional<UserDirectory> userDirectoryOptional = userDirectoryRepository.findById(userId);

        if (userDirectoryOptional.isPresent()) {

            UserDirectory userDirectory = userDirectoryOptional.get();

            List<Contact> contactList= userDirectory.getUserContacts();


            for (Contact contact1: contactList){
                if (contact1.getName().equals(contactName)) {

                    contact1.setPhoneNumber(contact.getPhoneNumber());
                    contact1.setEmailAddress(contact.getEmailAddress());
                    contact1.setName(contact.getName());



                    break;
                }

            }

            userDirectory.setUserContacts(contactList);
            userDirectoryRepository.save(userDirectory);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.BAD_REQUEST;




    }

    public HttpStatus removeContact(String userId, String  contact){


        Optional<UserDirectory> userDirectoryOptional = userDirectoryRepository.findById(userId);

        if (userDirectoryOptional.isPresent()) {

            UserDirectory userDirectory = userDirectoryOptional.get();

            List<Contact> contactList= userDirectory.getUserContacts();

            for (Contact contact1: contactList){
                if (contact1.getName().equals(contact)) {

                    contactList.remove(contact1);

                }

            }

            userDirectory.setUserContacts(contactList);

             userDirectoryRepository.save(userDirectory);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.BAD_REQUEST;





    }
}
