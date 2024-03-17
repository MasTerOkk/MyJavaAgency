package com.example.agency.repositories;

import com.example.agency.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("SELECT t FROM Tour t WHERE t.price between ?1 and ?2")
    List<Tour> findByPriceBetween(Integer price1, Integer price2);

}
