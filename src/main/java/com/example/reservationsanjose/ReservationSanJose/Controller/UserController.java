package com.example.reservationsanjose.ReservationSanJose.Controller;

import com.example.reservationsanjose.ReservationSanJose.Model.Role;
import com.example.reservationsanjose.ReservationSanJose.Model.User;
import com.example.reservationsanjose.ReservationSanJose.Repository.UserRepository;
import com.example.reservationsanjose.ReservationSanJose.Service.UserService;
import com.example.reservationsanjose.ReservationSanJose.UserDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/user-login")
    public String userLogin() {

        return "user-login";
    }


    @GetMapping("/admin")
    public String adminMenu(){ return "admin";}

    /*@RequestMapping(value = "/admin", method = RequestMethod.GET)
    public @ResponseBody String adminMenu(@RequestParam("user") User user){

        return "admin/userId=?"+user.getId();
    }*/

    @GetMapping("/user-home")
    public String userMenu(){ return "user-page";}


    @GetMapping("/user-lists")
    public String viewUsersList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        /*return "user-lists";*/
        return "users";
    }

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
}
