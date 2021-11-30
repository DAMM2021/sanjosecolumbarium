package com.example.reservationsanjose.ReservationSanJose.Repository;

import com.example.reservationsanjose.ReservationSanJose.Model.PaymentStatusInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface PaymentStatusInfoRepository extends JpaRepository<PaymentStatusInfo,Long> {
    @Query("SELECT p FROM PaymentStatusInfo p WHERE p.status_name= ?1")
    public PaymentStatusInfo findByName(String name);
}
