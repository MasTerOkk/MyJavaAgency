package com.example.agency.service;

import com.example.agency.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agency.repositories.TourRepository;

import java.util.List;

@Service
public class TourService {

    private final TourRepository repository;

    @Autowired
    public TourService(TourRepository repository) {
        this.repository = repository;
    }

    public List<Tour> getAll() {
        return repository.findAll();
    }

    public Tour getById(Long id) {
        return repository.getById(id);
    }

    public void save(Tour tour) {
        repository.save(tour);
    }

    public void delete(Long tourId) {
        repository.deleteById(tourId);
    }
}
