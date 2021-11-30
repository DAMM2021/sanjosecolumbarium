package com.example.reservationsanjose.ReservationSanJose.Controller;

import com.example.reservationsanjose.ReservationSanJose.Model.Payment;
import com.example.reservationsanjose.ReservationSanJose.Model.PaymentStatusInfo;
import com.example.reservationsanjose.ReservationSanJose.Model.Reservation;
import com.example.reservationsanjose.ReservationSanJose.Service.PaymentService;
import com.example.reservationsanjose.ReservationSanJose.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PaymentService paymentService;



    @RequestMapping("/payment-list")
    public String viewPaymentList(Model model, @Param("keyword") String keyword){
        List<Payment> listPayments = paymentService.listAllPayment(keyword);
        model.addAttribute("listPayments", listPayments);
        model.addAttribute("keyword", keyword);

        return "paymentList";
    }

    @RequestMapping("/new-reservation/payments")
    public String showPaymentForm(Model model){
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "payment-form";
    }


    @RequestMapping(value = "/new-reservation/payments/save", method = RequestMethod.POST)
    public String savePayments(@ModelAttribute("payment") Payment payment,
                               @RequestParam("paymentImageFile")MultipartFile file) throws Exception{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        payment.setPayment_receipt_img(fileName);

        paymentService.save(payment);

        String uploadDir = "C:/xampp_NEW/htdocs/San-Jose-Parish-Columbarium/";
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e){
            throw new IOException("Could not save uploaded file: " + fileName);

        }
        return "reserve-success";
    }

    @GetMapping("/reserve-success/reserveInfo?reservationId={id}")
    public String reserveSuccess(@PathVariable("id")Long id, Model model){
        Payment payment = paymentService.get(id);


        model.addAttribute("payment" , payment);

        return "reserve-success";
    }




    @GetMapping("/payments/edit/{reservationId}")
    public String editReservation(@PathVariable("reservationId")Long reservationId, Model model){
        Reservation reservation = reservationService.get(reservationId);


        model.addAttribute("reservation" , reservation);

        return "reserve-edit-form";
    }

    /*@GetMapping("/payments/delete/{reservationId}")
    public String deleteReservation(@PathVariable("reservationId")Long reservationId){


        this.reservationService.delete(reservationId);



        return "redirect:/reservation-lists";
    }
*/


    @PostMapping("/reservation/save")
    public String saveEditedReservation(Reservation reservation){

        reservationService.saveEditedReservation(reservation);

        return "redirect:/reservation-lists";
    }


    //rentals


    @RequestMapping("/rental-list")
    public String viewRentalList(Model model, @Param("keyword") String keyword){
        List<Reservation> listRental = reservationService.listAllReserves(keyword);
        List<Reservation> listRentals = reservationService.listRentals(keyword);
        model.addAttribute("listRental", listRental);
        model.addAttribute("listRentals", listRentals);

        return "rentals";
    }

    @GetMapping("/rental/payment")
    public String rentalPayment(Model model){
        Payment payment = new Payment();
        model.addAttribute("payment", payment);

        return "rental-paynent-form";
    }

    @GetMapping("/rental/payment/save")
    public String rentalSuccess(Model model){
        Payment payment = new Payment();
        model.addAttribute("payment", payment);

        return "rental-payment-success";
    }
}
