package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private Note note;
    @Column(name = "completed_on")
    private Date completedOn;

    public Log(int id, Date date) {
        this.id=id;
        this.completedOn=date;
    }

//    public void addUser(User user) {
//        this.user.add(new User(user.getId(),user.getName(), user.getPassword()));
//    }
}

