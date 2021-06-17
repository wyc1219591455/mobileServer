package com.justech.mobile.mobileserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "justech_job")
@Getter
@Setter
@NoArgsConstructor
public class JustechJob {
    @Id
    private String id;
    private String name;




}
