package net.engineeringDigest.journalApp.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//document means is this class we want to map with database
//we want primary key for this class so that used id and @Id primary key
//if collection name is not given then it will take by default JournalEntry name of collection to find in db

//lombok is library , which is used to reduce boiler plate code like getter, setter, constructor etc
//lombok automatically generates code during compilation time
//@Data , @Getter. @Setter all these annotations of lombok
//by using @Data annotation we can use all annotations present in lombok


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString

@Builder
@Data
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;

    //by using indexing searching of user is faster
    //NonNull is annotation that comes under lombok
    //lombok insures that the username and password should not be null it will check while setter method at compile time
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String passWord;

    private String email;

    private boolean sentiementAnalysis;


    @NonNull
    private List<String> roles;

    //DBRef means we are creating reference JournalEntry under this collection users
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();


    /* user class
[
  {
    _id: ObjectId('67712a220f08150645dadb76'),
    userName: 'ram123',
    passWord: 'aaa',
    journalEntries: [],
    _class: 'net.engineeringDigest.journalApp.entity.User'
  },
  {
    _id: ObjectId('67712a3e0f08150645dadb77'),
    userName: 'vipul',
    passWord: 'test123',
    journalEntries: [
      DBRef('journal_entries', ObjectId('67713f895f9b6a36c0b7d548')),
      DBRef('journal_entries', ObjectId('67713f8e5f9b6a36c0b7d549'))
    ],
    _class: 'net.engineeringDigest.journalApp.entity.User'
  }
]
 */

    /* entry class
    [
    {
    _id: ObjectId('67713f8e5f9b6a36c0b7d549'),
    title: 'roaming',
    content: 'food good night',
    date: ISODate('2024-12-29T12:28:50.099Z'),
    _class: 'net.engineeringDigest.journalApp.entity.JournalEntry'
  }
]
     */



}
