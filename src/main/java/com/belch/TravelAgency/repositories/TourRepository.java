package com.belch.TravelAgency.repositories;

import com.belch.TravelAgency.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long>
{
}
