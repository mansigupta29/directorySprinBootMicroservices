package org.mansi.directoryservice.repository;

import org.mansi.directoryservice.models.Contact;
import org.mansi.directoryservice.models.UserDirectory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDirectoryRepository extends MongoRepository<UserDirectory, String> {

    public UserDirectory findByUserName(String userName);

}
