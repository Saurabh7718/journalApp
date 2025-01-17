package net.engineeringDigest.journalApp.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

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
@Data
@NoArgsConstructor
@Document(collection="journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    @NonNull
    private String content;
    private LocalDateTime date;




}
