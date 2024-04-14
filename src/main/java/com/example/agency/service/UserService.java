package com.example.agency.service;

import com.example.agency.entity.Log;
import com.example.agency.entity.Tour;
import com.example.agency.entity.User;
import com.example.agency.entity.Role;
import com.example.agency.repositories.RoleRepository;
import com.example.agency.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LogService logService;

    public void saveUser(User user) throws RoleNotFoundException {
        Role role = roleRepository.getByRoleName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("role not found"));

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void putTourToList(User user, Tour tour) {
        List<Tour> tourList = user.getFavTourList();
        boolean tourFlag = false;
        for (Tour t: tourList) {
            if (Objects.equals(t.getId(), tour.getId())) {
                tourFlag = true;
                break;
            }
        }
        if (!tourFlag) {
            tourList.add(tour);
            user.setFavTourList(tourList);
            updateUser(user);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void sendMoney(Long senderId, Long recipientId, Integer money) {
        Log log = new Log();

        try {
            minusMoney(senderId, money);
            plusMoney(recipientId, money);
            log.setLog("Money sent");
            log.setStatus(true);
        } catch (Exception e) {
            log.setLog("Money didnt send");
            log.setStatus(false);
        }
        logService.saveLog(log);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void sendMoneyWithException(Long senderId, Long recipientId, Integer money) {
        Log log = new Log();
        try {
            minusMoney(senderId, money);
            err();
            plusMoney(recipientId, money);
            log.setLog("Money sent");
            log.setStatus(true);
        } catch (Exception e) {
            log.setLog("Money didnt send");
            log.setStatus(false);
            logService.saveLog(log);
            throw new RuntimeException("Exception");
        }
    }

    public void minusMoney(Long userId, Integer money) {
        User user = findById(userId);
        if (user.getAmount() >= money) {
            user.setAmount(user.getAmount() - money);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Not enough money");
        }
    }

    public void plusMoney(Long userId, Integer money) {
        User user = findById(userId);
        user.setAmount(user.getAmount() + money);
        userRepository.save(user);
    }

    private void err() {
        throw new RuntimeException("exception");
    }
}
