package org.mansi.directoryservice.database;


import org.mansi.directoryservice.models.Contact;
import org.mansi.directoryservice.models.UserDirectory;
import org.mansi.directoryservice.repository.UserDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private UserDirectoryRepository userDirectoryRepository;


// Sample Data exactly before the application startup completes.
    @Override
    public void run(String... args) throws Exception {

        UserDirectory user1 = new UserDirectory(
                "mansi",
                "6475392678",
                "imansigupta29@gmail.com",
                Arrays.asList( new Contact("abhinav","6475394797","iabhisharma12@gmail.com"),
                        new Contact("neha","123456789","neha.thakral@gmail.com"))
        );
        UserDirectory user2 = new UserDirectory(
                "neha",
                "23468765",
                "ineha29@gmail.com",
                Arrays.asList( new Contact("abhi","6475394797","iabhisharma12@gmail.com"),
                        new Contact("mansi","6475392678","imansigupta29@gmail.com"))
        );
        UserDirectory user3 = new UserDirectory(
                "abhinav",
                "6475394797",
                "iabhisharma12@gmail.com",
                Arrays.asList( new Contact("mansi","6475392678","imansigupta29@gmail.com"),
                        new Contact("neha","123456789","neha.thakral@gmail.com"))
        );


        this.userDirectoryRepository.deleteAll();

        List<UserDirectory> userDirectoryList = Arrays.asList(user1,user2,user3);

      this.userDirectoryRepository.saveAll(userDirectoryList);
    }
}
