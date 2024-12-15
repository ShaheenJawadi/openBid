package com.example.openbid.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "auctions")
@Getter
@Setter
@NoArgsConstructor


public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status = Status.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private User winner;

    @Column(nullable = false)
    private double finalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.UpdateTimestamp
    private Date updatedAt;

    public enum Status {
        PENDING, ONGOING, COMPLETED
    }

}
