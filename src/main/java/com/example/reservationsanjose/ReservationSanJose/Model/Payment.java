package com.example.reservationsanjose.ReservationSanJose.Model;


import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String accountName;

    @Column(nullable = false, length = 80)
    private String payment_receipt_img;

    @Column(nullable = false, length = 60)
    private String reference_no;

    @Column(nullable = false, length = 60)
    private String mobile_number;

    @Column(nullable = false, length = 60)
    private int amountPaid;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="reservation_id",updatable = false, insertable = false)
    private Reservation reservation;

    @Column(name = "reservation_id")
    private Long reserveId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",updatable = false, insertable = false)
    private User user;

    @Column(name="user_id")
    private Long userId;

    @Column(nullable = false, length = 60)
    private String paymentFor;


    public Payment(){

    }

    public Payment(Long id, String accountName, String payment_receipt_img, String reference_no, String mobile_number, int amountPaid) {
        this.id = id;
        this.accountName = accountName;
        this.payment_receipt_img = payment_receipt_img;
        this.reference_no = reference_no;
        this.mobile_number = mobile_number;
        this.amountPaid = amountPaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPayment_receipt_img() {
        return payment_receipt_img;
    }

    public void setPayment_receipt_img(String payment_receipt_img) {
        this.payment_receipt_img = payment_receipt_img;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public Long getReserveId() { return reserveId;}

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentFor() {
        return paymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
    }
}


