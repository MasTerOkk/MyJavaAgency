package com.example.agency.repositories;

import com.example.agency.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("SELECT t FROM Tour t WHERE t.price between ?1 and ?2")
    List<Tour> getByPriceBetween(Integer price1, Integer price2);

    List<Tour> findAllByPriceBetween(Integer price, Integer price2);


//    @Query("SELECT t from Tour t join fetch t.usersFavTourList")
//    List<Tour> getAllEager();
//
//    @Query("SELECT t from Tour t join fetch t.usersFavTourList where t.id = ?1")
//    Tour getByIdEager(Long id);

    @Query("SELECT t FROM Tour t where UPPER(t.name) like UPPER(?1)")
    List<Tour> getTourByName(String name);
}
