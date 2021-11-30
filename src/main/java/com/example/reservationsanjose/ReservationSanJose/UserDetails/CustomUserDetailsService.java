package com.example.reservationsanjose.ReservationSanJose.UserDetails;

import com.example.reservationsanjose.ReservationSanJose.Model.User;
import com.example.reservationsanjose.ReservationSanJose.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException("User not Found");
        }
        return new CustomUserDetails(user);
    }
}
