package com.acciojob.bookmyshowapplications.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    @Column(unique = true)
    private String emailId;

    private String password;

    private String roles;

    @Column(unique = true)
    private String mobNo;

    @OneToMany(mappedBy = "userInfo",cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", roles='" + roles + '\'' +
                ", mobNo='" + mobNo + '\'' +
                '}';
    }
}
