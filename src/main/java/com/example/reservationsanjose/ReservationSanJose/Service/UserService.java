package com.example.reservationsanjose.ReservationSanJose.Service;

import com.example.reservationsanjose.ReservationSanJose.Model.Role;
import com.example.reservationsanjose.ReservationSanJose.Model.User;
import com.example.reservationsanjose.ReservationSanJose.Repository.RoleRepository;
import com.example.reservationsanjose.ReservationSanJose.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void saveUserWithDefaultRole(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);
        userRepository.save(user);

    }

    public void save(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }


    public User get(Long id){
        return userRepository.findById(id).get();
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
