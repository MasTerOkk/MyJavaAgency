package com.example.agency.controllers;

import com.example.agency.entity.Tour;
import com.example.agency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class tourController {

    private final TourService tourService;

    @Autowired
    public tourController(TourService tourService) {
        this.tourService = tourService;
    }


    @GetMapping
    public String hello(Model model) {
        model.addAttribute("AllTour",tourService.getAll());
        model.addAttribute("tour", new Tour());
        System.out.println("hello");
        return "Hello";
    }

    @PostMapping
    public String addTour(@ModelAttribute(name = "tour") Tour tour) {
        tourService.save(tour);
        return "redirect:/";
    }

    @GetMapping("/deleteTour/{tour_id}")
    public String deleteTour(@PathVariable(name = "tour_id") Long tour_id) {
        tourService.delete(tour_id);
        System.out.println("deleteTour");
        return "redirect:/";
    }

}
