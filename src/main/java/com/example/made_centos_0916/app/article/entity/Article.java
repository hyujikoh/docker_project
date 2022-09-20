package com.example.made_centos_0916.app.article.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String subject;
}