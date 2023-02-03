package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;
    private String password;

//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//
//            orphanRemoval = true
//    )
//    @JsonIgnore
//    @ToString.Exclude
//    private Log log;


}

