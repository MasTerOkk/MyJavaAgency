package com.example.agency.service;

import com.example.agency.entity.Tour;
import com.example.agency.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agency.repositories.TourRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
        return repository.findById(id).orElseThrow();
    }

    public void save(Tour tour) {
        repository.save(tour);
    }

    public void delete(Long tourId) {
        repository.deleteById(tourId);
    }

    public List<Tour> getToursByName(String name) {
        name = "%" + name + "%";
        return repository.getTourByName(name);
    }

    @Transactional
    public void putUserToList(User user, Tour tour) {
        List<User> userList = tour.getUsersFavTourList();
        boolean userFlag = false;
        for (User u: userList) {
            if (Objects.equals(u.getId(), user.getId())) {
                userFlag = true;
                break;
            }
        }
        if (!userFlag) {
            userList.add(user);
            tour.setUsersFavTourList(userList);
            save(tour);
        }
    }
}
