package com.example.reservationsanjose.ReservationSanJose.Repository;

import com.example.reservationsanjose.ReservationSanJose.Model.Payment;
import com.example.reservationsanjose.ReservationSanJose.Model.PaymentStatusInfo;
import com.example.reservationsanjose.ReservationSanJose.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.accountName = ?2 AND (p.reference_no LIKE %?1%"
            + " AND p.userId = ?2"
            + " OR p.payment_receipt_img LIKE %?1%)")
    public List<Payment> search(String keyword, Long userId);

    @Query("SELECT p FROM Payment p WHERE p.accountName  LIKE %?1%"
            + " OR p.reference_no LIKE %?1%"
            + " OR p.payment_receipt_img LIKE %?1%"
            + " OR CONCAT(p.mobile_number, '')LIKE %?1%")
    public List<Payment> searchAll(String keyword);


}
