package com.example.reservationsanjose.ReservationSanJose.Model;


import com.sun.istack.NotNull;
import org.assertj.core.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(name = "date_reserve", columnDefinition = "DATE")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateReserve;


    @Column(nullable = false, unique = true, length = 20)
    private String slotNo;

    @Column(nullable = false, length = 20)
    private String deathCertificate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

/*    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reservation_payment_status",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id")
    )
    private Set<PaymentStatusInfo> paymentStatusInfo = new HashSet<>();*/

    @Column(nullable = false, length = 60)
    private String paymentStatus;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
    private Payment payment;

    public Reservation() {

    }

    public Reservation(Date dateReserve) {
        this.dateReserve = dateReserve;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateReserve() {
        return dateReserve;
    }

    public void setDateReserve(Date dateReserve) {
        this.dateReserve = dateReserve;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    public String getDeathCertificate() {
        return deathCertificate;
    }

    public void setDeathCertificate(String deathCertificate) {
        this.deathCertificate = deathCertificate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final String DATE_FORMAT = "dd/MM/yyyy";
/*
    public Set<PaymentStatusInfo> getPaymentStatusInfo() {
        return paymentStatusInfo;
    }

    public void setPaymentStatusInfo(Set<PaymentStatusInfo> paymentStatusInfo) {
        this.paymentStatusInfo = paymentStatusInfo;
    }

    public void addPaymentStatus(PaymentStatusInfo statusInfo){
        this.paymentStatusInfo.add(statusInfo);
    }*/

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


}
