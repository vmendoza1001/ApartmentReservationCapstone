package com.vanessa.ApartmentReservationCapstone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String reservationNumber;
    @Column(nullable = false)
    private int numberOfGuests;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private String startDate;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private String endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Reservation() {
    }

    public Reservation(String reservationNumber, int numberOfGuests, String startDate, String endDate, User user) {
        this.reservationNumber = reservationNumber;
        this.numberOfGuests = numberOfGuests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber='" + reservationNumber + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                '}';
    }
}
