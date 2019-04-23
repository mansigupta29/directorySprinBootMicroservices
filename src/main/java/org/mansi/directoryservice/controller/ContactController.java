package org.mansi.directoryservice.controller;


import org.mansi.directoryservice.models.Contact;
import org.mansi.directoryservice.models.UserDirectory;
import org.mansi.directoryservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("directory/users/{userName}/contacts/{contactName}")
public class ContactController {

    @Autowired
    ContactService contactService;


    //get particular contact of user
    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getContactInfo(@PathVariable("userName") String userName, @PathVariable("contactName") String contactName){

      return contactService.findContactInfo(userName,contactName);
    }


    @RequestMapping( method = RequestMethod.PUT)
    public UserDirectory updateContact(HttpServletResponse response, @PathVariable("userName") String userName, @PathVariable("contactName") String contactName, @RequestBody Contact contact){
        return contactService.updateContact(userName, contactName, contact);



    }


    @RequestMapping(method = RequestMethod.DELETE)
    public void removeContact(HttpServletResponse response, @PathVariable("userName") String userName, @PathVariable("contactName") String contactName){

         contactService.removeContact(userName, contactName);

    }
}
