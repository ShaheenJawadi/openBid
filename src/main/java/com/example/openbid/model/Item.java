package com.example.openbid.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "items")
@Getter
@Setter
@RequiredArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private double startingPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionStart;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.UpdateTimestamp
    private Date updatedAt;

    @Column(nullable = true ,name = "item_images")
    private String itemImage;

    @Column(nullable = true , name = "tags")
    private String tags;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)  // Add category relation
    private Category category;  // New field for category


    @Column(nullable = false, unique = true, length = 150)
    private String slug; // Added slug field

}
