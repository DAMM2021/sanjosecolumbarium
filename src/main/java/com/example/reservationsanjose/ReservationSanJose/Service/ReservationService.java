package com.example.reservationsanjose.ReservationSanJose.Service;

import com.example.reservationsanjose.ReservationSanJose.Model.PaymentStatusInfo;
import com.example.reservationsanjose.ReservationSanJose.Model.Reservation;
import com.example.reservationsanjose.ReservationSanJose.Model.Role;
import com.example.reservationsanjose.ReservationSanJose.Model.User;
import com.example.reservationsanjose.ReservationSanJose.Repository.PaymentStatusInfoRepository;
import com.example.reservationsanjose.ReservationSanJose.Repository.ReservationRepository;
import com.example.reservationsanjose.ReservationSanJose.UserDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PaymentStatusInfoRepository paymentStatusInfoRepository;


    public void save(Reservation reservation) throws Exception {

        //GETTING THE USER LOGIN INFORMATION
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        reservation.setUserId(user.getId());


        reservation.setPaymentStatus("Not Paid");


        reservationRepository.save(reservation);
    }

    public void saveEditedReservation(Reservation reservation){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        reservation.setUserId(user.getId());

        reservationRepository.save(reservation);

    }

    //search
    public List<Reservation> listAllReserves(String keyword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (user.getRoles().stream().filter(role -> role.getName().equals("Admin")).findFirst().isPresent()) {
            if(keyword != null){
                return reservationRepository.searchAll(keyword);
            }
            return reservationRepository.findAll();
        } else {
            if(keyword != null){
                return reservationRepository.search(keyword, user.getId());
            }
            return reservationRepository.findByUserId(user.getId());
        }
    }

    public List<Reservation> listRentals(String keyword){



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (user.getRoles().stream().filter(role -> role.getName().equals("Admin")).findFirst().isPresent()) {
            if(keyword != null){
                return reservationRepository.findRental(keyword);
            }
            return reservationRepository.findRental(keyword);
        } else {
            if(keyword != null){
                return reservationRepository.findRental(keyword);
            }
            return reservationRepository.findByUserId(user.getId());
        }


    }


    public List<Reservation> listAll(){
        return reservationRepository.findAll();
    }


    public Reservation get(Long id){

        return reservationRepository.findById(id).get();
    }


    public void delete(long id){
        reservationRepository.deleteById(id);
    }

    public List<String> findSlots(Date date) {
        return reservationRepository.findSlots(date);
    }

}
