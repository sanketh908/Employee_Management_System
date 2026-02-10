    package com.Sanketh.demo.Entity;


    import lombok.Data;
    import org.bson.types.ObjectId;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.index.Indexed;
    import org.springframework.data.mongodb.core.mapping.Document;

    import java.lang.annotation.Documented;

    @Data

    @Document(collation = "admin")
    public class Admin {
        @Id

       private ObjectId id;
       @Indexed(unique = true)
       private  String username;

       private  String password;

       private  String email;



    }
