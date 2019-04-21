package org.mansi.directoryservice.controller;


import org.apache.coyote.Response;
import org.mansi.directoryservice.models.Contact;
import org.mansi.directoryservice.models.UserDirectory;
import org.mansi.directoryservice.service.UserDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/directory/users")
public class UserDirectoryController {

    @Autowired
    private UserDirectoryService userDirectoryService;



    // get particular user details and contacts
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public Optional<UserDirectory> getContacts(@PathVariable("userId") String userId){

       return userDirectoryService.getAllContacts(userId);


    }

    //add a user in database
    @RequestMapping(value = "add", method = RequestMethod.PUT)
    public UserDirectory addUser(@RequestBody UserDirectory userDirectory){

         return userDirectoryService.addUser(userDirectory);

    }



    //add a contact to a user
    @RequestMapping(value = "{userId}/addContact", method = RequestMethod.PUT)
    public void addContact(HttpServletResponse response, @PathVariable("userId") String userId, @RequestBody Contact contact){

        HttpStatus status = userDirectoryService.addContact(userId,contact);
         if (status == HttpStatus.ACCEPTED){
             response.setStatus(HttpServletResponse.SC_ACCEPTED);
         }
         else  if (userDirectoryService.addContact(userId,contact) == HttpStatus.BAD_REQUEST)
         response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }

    //delete a user
    @RequestMapping(value = "{userId}/delete", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("userId") String userId){

         userDirectoryService.removeUser(userId);

    }

    //update a user profile
    @RequestMapping(value = "{userId}/update", method = RequestMethod.POST)
    public void updateUser(HttpServletResponse response, @PathVariable("userId") String userId, @RequestBody UserDirectory userDirectory){


        HttpStatus status = userDirectoryService.updateUser(userId, userDirectory);
        if (status == HttpStatus.ACCEPTED){
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else  if (status == HttpStatus.BAD_REQUEST)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }


    // get all users and there data
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<UserDirectory> getAll(){

        return userDirectoryService.getAll();


    }


}
