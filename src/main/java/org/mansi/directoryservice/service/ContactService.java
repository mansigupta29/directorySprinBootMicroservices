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



    public List<Contact> findContactInfo(String userName, String  contact){

        List<Contact> contact2 = new ArrayList<>();

        UserDirectory userDirectory = userDirectoryRepository.findByUserName(userName);



            List<Contact> contactList= userDirectory.getUserContacts();
            for (Contact contact1: contactList){
                if (contact1.getName().equals(contact)) {

                    contact2.add(contact1);
                }

            }

        return contact2;
        }




    public UserDirectory updateContact(String userName, String contactName, Contact contact){


       UserDirectory userDirectory = userDirectoryRepository.findByUserName(userName);



            List<Contact> contactList= userDirectory.getUserContacts();


            for (Contact contact1: contactList){
                if (contact1.getName().equals(contactName)) {

                    contactList.remove(contact1);
                    contactList.add(contact);

                    break;
                }

            }

            userDirectory.setUserContacts(contactList);
            return userDirectoryRepository.save(userDirectory);





    }

    public void removeContact(String userName, String  contact){


       UserDirectory userDirectory = userDirectoryRepository.findByUserName(userName);



            List<Contact> contactList= userDirectory.getUserContacts();

            for (Contact contact1: contactList){
                if (contact1.getName().equals(contact)) {

                    contactList.remove(contact1);

                }

            }

            userDirectory.setUserContacts(contactList);

             userDirectoryRepository.save(userDirectory);


    }
}
