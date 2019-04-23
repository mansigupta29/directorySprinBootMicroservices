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
    @RequestMapping(value = "{userName}",method = RequestMethod.GET)
    public UserDirectory getContacts(@PathVariable("userName") String userName){

       return userDirectoryService.getUserInfo(userName);


    }

    //add a user in database
    @RequestMapping( method = RequestMethod.POST)
    public String addUser(@RequestBody UserDirectory userDirectory){

         return userDirectoryService.addUser(userDirectory);

    }



    //add a contact to a user
    @RequestMapping(value = "{userName}", method = RequestMethod.POST)
    public UserDirectory addContact(HttpServletResponse response, @PathVariable("userName") String userName, @RequestBody Contact contact){

        return userDirectoryService.addContact(userName,contact);


    }

    //delete a user
    @RequestMapping(value = "{userName}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("userName") String userName){

         userDirectoryService.removeUser(userName);

    }

    //update a user profile
    @RequestMapping(value = "{userName}", method = RequestMethod.PUT)
    public UserDirectory updateUserInfo(HttpServletResponse response, @PathVariable("userName") String userName, @RequestBody UserDirectory userDirectory){

        return userDirectoryService.updateUserInfo(userName, userDirectory);


    }


    // get all users and there data
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDirectory> getAll(){

        return userDirectoryService.getAll();


    }


}
