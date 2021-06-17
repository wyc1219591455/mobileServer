package com.justech.mobile.mobileserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "justech_pos")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class JustechPos {
    @Id
    private String id;

    private String name;
    private String version;



}
