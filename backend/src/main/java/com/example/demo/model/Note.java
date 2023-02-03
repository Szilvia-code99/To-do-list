package com.example.demo.model;
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
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String title;
    private String text;
    private String category;
    private String textColor;
    private String color;
    private Boolean pinned;

//    @OneToMany(
//            mappedBy = "note",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Log> logList = new ArrayList<>();


}

