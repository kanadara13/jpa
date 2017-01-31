package com.lq.demo.entity;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by user81 on 2017-01-17.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private int age;

    @ManyToOne
    //@JoinColumn(name="TEAM_ID")
    @JoinColumn(name="TEAM_NICK_NAME", referencedColumnName = "NICK_NAME")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
