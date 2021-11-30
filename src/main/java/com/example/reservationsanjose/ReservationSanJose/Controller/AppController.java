package com.example.reservationsanjose.ReservationSanJose.Controller;


import com.example.reservationsanjose.ReservationSanJose.Model.*;
import com.example.reservationsanjose.ReservationSanJose.Repository.UserRepository;
import com.example.reservationsanjose.ReservationSanJose.Service.PaymentService;
import com.example.reservationsanjose.ReservationSanJose.Service.ReservationService;
import com.example.reservationsanjose.ReservationSanJose.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    /*@Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;


    @GetMapping("")
    public String viewHomePage(){

        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());

        return "signup-form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){

        userService.saveUserWithDefaultRole(user);

        return "register-success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/users")
    public String users(){
        return "users";
    }

    @GetMapping("/admin")
    public String adminMenu(){ return "admin";}

    @GetMapping("/user-lists")
    public String viewUsersList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        *//*return "user-lists";*//*
        return "users";
    }

    //edit user
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id")Long id, Model model){
        User user = userService.get(id);

        List<Role> listRoles = userService.getRoles();

        model.addAttribute("user" , user);
        model.addAttribute("listRoles" , listRoles);
        return "user_form";
    }

    //delete user
    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id")Long id, Model model){
        User user = userService.get(id);
        userRepository.delete(user);

        return "redirect:/user-lists";
    }


    // adding user
    @PostMapping("/users/save")
    public String saveUser(User user){
        userService.save(user);
        return "redirect:/user-lists";
    }


    // adding reservation

    @RequestMapping("/new-reservation")
    public String showAddReserveForm(Model model){
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        return "reserve-form";
    }



    @RequestMapping(value = "/new-reservation/save", method = RequestMethod.POST)
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation){
        reservationService.save(reservation);
        return "redirect:/new-reservation/payments?reservationId="+reservation.getReservationId();
    }


    @RequestMapping("/reservation-lists")
    public String viewReservationList(Model model){
        List<Reservation> listReserves = reservationService.listAll();
        model.addAttribute("listReserves", listReserves);

        return "reserve-lists";

    }

    //payments

    @GetMapping("/payment-list")
    public String viewPaymentList(Model model){
        List<Payment> listPayments = paymentService.listAll();
        model.addAttribute("listPayments", listPayments);

        return "paymentList";
    }

    @RequestMapping("/new-reservation/payments")
    public String showPaymentForm(Model model){
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "payment-form";
    }


    @RequestMapping(value = "/new-reservation/payments/save", method = RequestMethod.POST)
    public String savePayments(@ModelAttribute("payment") Payment payment){
        paymentService.save(payment);
        return "redirect:/admin";
    }

    @GetMapping("/payments/edit/{reservationId}")
    public String editReservation(@PathVariable("reservationId")Long reservationId, Model model){
        Reservation reservation = reservationService.get(reservationId);

        List<PaymentStatusInfo> paymentStatusInfoList = reservationService.getPaymentStatus();

        model.addAttribute("reservation" , reservation);
        model.addAttribute("paymentStatusInfoList" , paymentStatusInfoList);
        return "reserve-edit-form";
    }

    @PostMapping("/reservation/save")
    public String saveEditedReservation(Reservation reservation){

        reservationService.saveEditedReservation(reservation);

        return "redirect:/reservation-lists";
    }


    //rentals
    @GetMapping("/rental-list")
    public String viewRentalList(Model model){
        List<Reservation> listRental = reservationService.listAll();
        model.addAttribute("listRental", listRental);

        return "rentals";
    }

*/




}
