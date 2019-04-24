# directorySprinBootMicroservices


1.	Predefined data in DbSeeder
2.	To GET all the users and their contacts 
	http://localhost:8081/directory/users
3.	To GET particular user’s contact
http://localhost:8081/directory/users/{userName}
4.	To POST a new user 
http://localhost:8081/directory/users
Body: {
"userName": "name",
    "phoneNumber": "123456789",
    "emailAddress": "email@gmail.com",
    "userContacts": [
        {
            "name": "contactname",
            "phoneNumber": "123456789",
            "emailAddress": "email@gmail.com"
        } ]	
}
5.	To POST a new contact in particular user
http://localhost:8081/directory/users/{ userName }
Body: 
        {
            "name": "contactname",
            "phoneNumber": "123456789",
            "emailAddress": "email@gmail.com"
        }

6.	To DELETE a user
http://localhost:8081/directory/users/{ userName }
7.	To PUT (update) a user
http://localhost:8081/directory/users/{ userName }
Body: {
“id”: “objectId”,
"userName": "name",
    "phoneNumber": "123456789",
    "emailAddress": "email@gmail.com",
    "userContacts": [
        {
            "name": "contactname",
            "phoneNumber": "123456789",
            "emailAddress": "email@gmail.com"
        } ]
}


8.	To GET a contact for a user
http://localhost:8081/directory/users/{userName}/contacts/{contactName}
9.	To DELETE a contact for a user
http://localhost:8081/directory/users/{userName}/contacts/{contactName}
10.	To PUT (update) a contact for a user
http://localhost:8081/directory/users/{userName}/contacts/{contactName}

Body: {
            "name": "contactname",
            "phoneNumber": "123456789",
            "emailAddress": "email@gmail.com"
        }
