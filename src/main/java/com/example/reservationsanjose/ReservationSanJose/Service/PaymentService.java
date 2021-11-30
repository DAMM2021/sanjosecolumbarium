package com.example.reservationsanjose.ReservationSanJose.Service;

import com.example.reservationsanjose.ReservationSanJose.Model.Payment;
import com.example.reservationsanjose.ReservationSanJose.Model.Reservation;
import com.example.reservationsanjose.ReservationSanJose.Model.User;
import com.example.reservationsanjose.ReservationSanJose.Repository.PaymentRepository;
import com.example.reservationsanjose.ReservationSanJose.Repository.ReservationRepository;
import com.example.reservationsanjose.ReservationSanJose.UserDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public void save(Payment payment){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        payment.setUserId(user.getId());
        payment.setPaymentFor("Reservation");

        paymentRepository.save(payment);
    }

    public List<Payment> listAll(String keyword)
    {
        if (keyword != null) {
            return paymentRepository.searchAll(keyword);
        }
        return paymentRepository.findAll();
    }

    public List<Payment> listAllPayment(String keyword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getFullName();

        Payment payment = new Payment();
        payment.setUserId(user.getId());
        if(keyword != null){
            return paymentRepository.searchAll(keyword);
        }
        return paymentRepository.findAll();
        /* if (user.getRoles().stream().filter(user -> user.getName().equals("Admin")).findFirst().isPresent())*/
//        if(payment.getUserName().equals(username)){
//            if(keyword != null){
//                return paymentRepository.searchAll(keyword);
//            }
//            return paymentRepository.findAll();
//        } else {
//            if(keyword != null){
//                return paymentRepository.search(keyword, username);
//            }
//            return paymentRepository.findByUserName(username);
//        }
    }

    public Payment get(Long id){
        return paymentRepository.findById(id).get();
    }

}
