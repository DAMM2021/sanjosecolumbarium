package com.example.reservationsanjose.ReservationSanJose.Controller;

import com.example.reservationsanjose.ReservationSanJose.Model.Reservation;
import com.example.reservationsanjose.ReservationSanJose.Model.User;
import com.example.reservationsanjose.ReservationSanJose.Service.PaymentService;
import com.example.reservationsanjose.ReservationSanJose.Service.ReservationService;
import com.example.reservationsanjose.ReservationSanJose.UserDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/new-reservation")
    public String showAddReserveForm(Model model){
        Reservation reservation = new Reservation();
        List<String> slotList = reservationService.listAll().stream().map(reservationObj -> reservationObj.getSlotNo()).collect(Collectors.toList());
        model.addAttribute("slotList", slotList);
        model.addAttribute("reservation", reservation);
        return "reserve-form";
    }

    @RequestMapping(value = "/get-slots", method = RequestMethod.GET)
    public @ResponseBody List<String> optionExerciseView(@RequestParam("dateReserve") String dateReserve) throws Exception {
        Date dateReserveObj = new SimpleDateFormat("yyyy-MM-dd").parse(dateReserve);
        List<String> result = reservationService.findSlots(dateReserveObj);
        return result != null && !result.isEmpty() ? result : new ArrayList<>();
    }

    @RequestMapping(value = "/new-reservation/save", method = RequestMethod.POST)
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation,
                                  @RequestParam("imageFile") MultipartFile file ) throws Exception {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        reservation.setDeathCertificate(fileName);

        reservationService.save(reservation);

        String uploadDir = "C:/xampp_NEW/htdocs/San-Jose-Parish-Columbarium/"; //death-certifcates/"  + reservation.getReservationId();
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

        return "redirect:/new-reservation/payments?reservationId="+reservation.getReservationId();
    }


    /*@RequestMapping("/reservation-lists")
    public String viewReservationList(Model model){

        List<Reservation> listReserves = reservationService.listAll();
        model.addAttribute("listReserves", listReserves);


        return "reserve-lists";

    }*/

    /*@GetMapping("/reservation-lists/{id}")
    public String viewReserveListById(@PathVariable Long id, Model model){

        model.addAttribute("reservationListById", reservationService.getReservesById(id));

        return "reservation-list-by-id";
    }*/

    //search

    @RequestMapping("/reservation-lists")
    public String viewReserve(Model model, @Param("keyword") String keyword){

        List<Reservation> listReservesAll = reservationService.listAllReserves(keyword);
        model.addAttribute("listReservesAll", listReservesAll);
        model.addAttribute("keyword", keyword);


        /*Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 6);

        java.util.Date expirationDate = cal.getTime();*/

        //Reservation reservation = new Reservation();
        //reservation.setExpiry(expirationDate);


        return "reservation-list-by-id";

    }







}
