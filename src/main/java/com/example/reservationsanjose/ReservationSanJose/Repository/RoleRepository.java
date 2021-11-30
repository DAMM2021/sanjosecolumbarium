package com.example.reservationsanjose.ReservationSanJose.Repository;

import com.example.reservationsanjose.ReservationSanJose.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role findByName(String name);

}
