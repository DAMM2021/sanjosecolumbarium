package com.example.reservationsanjose.ReservationSanJose;

import com.example.reservationsanjose.ReservationSanJose.Model.Role;
import com.example.reservationsanjose.ReservationSanJose.Repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testCreateRoles(){

        Role user = new Role("User");
        Role admin = new Role("Admin");
        Role customer = new Role("Customer");

        roleRepository.saveAll(List.of(user, admin, customer));

        List<Role> listRoles = roleRepository.findAll();

        assertThat(listRoles.size()).isEqualTo(3);

    }

}
