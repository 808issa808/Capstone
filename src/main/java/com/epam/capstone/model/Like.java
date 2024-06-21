//package com.epam.capstone.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "likes")
//public class Like {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne(fetch =FetchType.LAZY)
//    @JoinColumn(name = "liker_id")
//    User liker;
//    @ManyToOne(fetch =FetchType.LAZY)
//    @JoinColumn(name = "post_id")
//    private Post post;
//}
