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
@RequestMapping("directory/users/{userId}/contacts/{contactName}")
public class ContactController {

    @Autowired
    ContactService contactService;


    //get particular contact of user
    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getContact(@PathVariable("userId") String userId, @PathVariable("contactName") String contactName){

      return contactService.findContact(userId,contactName);
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updateContact(HttpServletResponse response, @PathVariable("userId") String userId, @PathVariable("contactName") String contactName, @RequestBody Contact contact){
        HttpStatus status = contactService.updateContact(userId, contactName, contact);


        if (status == HttpStatus.ACCEPTED){
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else  if (status == HttpStatus.BAD_REQUEST)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }


    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void removeContact(HttpServletResponse response, @PathVariable("userId") String userId, @PathVariable("contactName") String contactName){

        HttpStatus status = contactService.removeContact(userId, contactName);
        if ( status == HttpStatus.ACCEPTED){
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else  if (status == HttpStatus.BAD_REQUEST)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }
}
