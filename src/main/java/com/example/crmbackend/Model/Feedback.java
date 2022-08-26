package com.example.crmbackend.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="feedbackId")
    private Integer Id;

    @Column(name="feedbackDescription")
    private String feedbackDesciption;

    @Column(name = "rating")
    private int rating;

    @OneToOne
    private Request request;

    @OneToOne
    private Customer customer;
}
