package com.belch.TravelAgency.entities;

import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders", schema = "public")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @Column(nullable = false)
    private boolean confirmed;

    @Column(name="time_key",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timeKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public Tour getTours() {
        return tour;
    }

    public void setTours(Tour tour) {
        this.tour = tour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWho_take() {
        return user;
    }

    public void setWho_take(User who_take) {
        this.user = who_take;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Date getTimeKey() {
        return timeKey;
    }

    public void setTimeKey(Date timeKey) {
        this.timeKey = timeKey;
    }

}
