package com.dartacademy.blogkuapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Integer id;
    private String name;
}
