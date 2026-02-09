package com.Sanketh.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name= "admin_table")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
    @Column(nullable = false,unique = true)
   private  String username;
    @Column(nullable = false)
   private  String password;
   @Column(nullable = false,unique = true)
   private  String email;



}
