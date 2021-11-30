package com.example.reservationsanjose.ReservationSanJose;

import com.example.reservationsanjose.ReservationSanJose.Model.Reservation;
import com.example.reservationsanjose.ReservationSanJose.Model.Role;
import com.example.reservationsanjose.ReservationSanJose.Model.User;
import com.example.reservationsanjose.ReservationSanJose.Repository.ReservationRepository;
import com.example.reservationsanjose.ReservationSanJose.Repository.RoleRepository;
import com.example.reservationsanjose.ReservationSanJose.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("miguel@gmail.com");
        user.setPassword("miguel");
        user.setLastName("apaya");
        user.setFirstName("miguel");
        user.setContact(123);
        user.setAddress("navotas");

        User savedUser = userRepository.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    public void testFindUserByEmail(){
        String email = "miguel@gmail.com";

        User user = userRepository.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testAddRoleToNewUser(){
        User user = new User();
        user.setEmail("giannis@gmail.com");
        user.setPassword("giannis29");
        user.setLastName("Cruz");
        user.setFirstName("Giannis");
        user.setAddress("Malabon City");
        user.setContact(124456789);

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);

    }

    @Test
    public void testAddRolesToExistingUser(){
        User user = userRepository.findById(1L).get();

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);

        Role roleAdmin = new Role(2L);
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }

    @Test
    public void testAddReservesWithUser(){
        Reservation reservation =  new Reservation();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();  ;

        reservation.setLastName("Jiraiya");
        reservation.setLastName("Sensei");
        reservation.setDateReserve(date);
        reservation.setSlotNo("A5");
        reservation.setDeathCertificate("hehe.jpg");


    }
}
