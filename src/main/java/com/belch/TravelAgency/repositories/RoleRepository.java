package com.belch.TravelAgency.repositories;

import com.belch.TravelAgency.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByRole(String role);
}
