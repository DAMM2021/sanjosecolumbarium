package com.example.reservationsanjose.ReservationSanJose.Model;

import javax.persistence.*;

@Entity
@Table(name = "payment_status")
public class PaymentStatusInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    @Column(nullable = false, length = 45)
    private String status_name;

    public PaymentStatusInfo(){

    }

    public PaymentStatusInfo(String status_name){this.status_name=status_name;}

    public PaymentStatusInfo(Long status_id){this.status_id = status_id;}

    public PaymentStatusInfo(Long status_id, String status_name) {
        this.status_id = status_id;
        this.status_name = status_name;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return this.status_name;
    }
}
