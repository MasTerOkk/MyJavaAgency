package com.example.agency.controllers;

import com.example.agency.configs.MyUserDetails;
import com.example.agency.entity.Tour;
import com.example.agency.entity.User;
import com.example.agency.service.TourService;
import com.example.agency.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class TourController {

    private final TourService tourService;
    private final UserService userService;



    @GetMapping
    public String hello(Model model) {
        model.addAttribute("AllTour",tourService.getAll());
        model.addAttribute("tour", new Tour());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) principal;
            Long userId = userDetails.getUserId();
            model.addAttribute("userid", userId);
        }
        return "Hello";
    }

    @PostMapping
    public String addTour(@ModelAttribute(name = "tour") Tour tour) {
        tourService.save(tour);
        return "redirect:/";
    }

    @PostMapping("/findTour")
    public String findTour(
            @RequestParam(name = "tourName") String tourName,
            Model model
        ) {
        List<Tour> tourList = tourService.getToursByName(tourName);
        model.addAttribute("AllTour", tourList);
        model.addAttribute("tour", new Tour());
        return "Hello";
    }

    @GetMapping("/deleteTour/{tour_id}")
    public String deleteTour(@PathVariable(name = "tour_id") Long tour_id) {
        tourService.delete(tour_id);
        return "redirect:/";
    }

    @GetMapping("/deleteFavTour/{tour_id}")
    public String deleteFavTour(
            @PathVariable(name = "tour_id") Long tour_id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) principal;
            User user = userDetails.getUser();

            List<Tour> tourList = user.getFavTourList();
            tourList.removeIf(tour1 -> tour1.getId() == tour_id);

            user.setFavTourList(tourList);
            userService.updateUser(user);

            Tour tour = tourService.getById(tour_id);
            List<User> userList = tour.getUsersFavTourList();
            userList.removeIf(user1 -> user1.getId() == user.getId());

        }

        return "redirect:/fav";
    }

    @GetMapping("/addFavTour/{tour_id}")
    public String addFavTour(@PathVariable(name = "tour_id") Long tour_id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) principal;
            User user = userDetails.getUser();

            Tour tour = tourService.getById(tour_id);

            userService.putTourToList(user,tour);

            tourService.putUserToList(user, tour);

        }
        return "redirect:/fav";
    }

    @GetMapping("/fav")
    public String favList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) principal;
            User user = userDetails.getUser();
            model.addAttribute("favList", user.getFavTourList());
            model.addAttribute("favListFlag", true);
        }
        return "fav";
    }
    @GetMapping("/favTourUsers/{tour_id}")
    public String tourFavUserList(@PathVariable(name = "tour_id") Long tourid, Model model) {

        Tour tour = tourService.getById(tourid);
        model.addAttribute("tourFavUsersList", tour.getUsersFavTourList());
        model.addAttribute("tourFavUsersListFlag", true);

        return "fav";
    }
}
