package com.example.agency.controllers;

import com.example.agency.entity.User;
import com.example.agency.service.LogService;
import com.example.agency.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/amount")
@AllArgsConstructor
public class AmountController {
    private final UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseEntity<?> addMoney(@RequestParam(name = "money") Integer money,
                                      @RequestParam(name = "userId") Long userId) {
        if (money > 0) {
            try {
                User user = userService.findById(userId);
                user.setAmount(user.getAmount() + money);
                userService.updateUser(user);
                return ResponseEntity.status(HttpStatus.OK).body("Your balance is " + user.getAmount() + "$");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Money must be > 0");
    }

    @RequestMapping(value = "/send", method = RequestMethod.PUT)
    public ResponseEntity<?> sendMoney(@RequestParam(name = "money") Integer money,
                                       @RequestParam(name = "senderId") Long senderId,
                                       @RequestParam(name = "recipientId") Long recipientId) {
        try {
            if (money > 0) {
                userService.sendMoney(senderId, recipientId, money);
                return ResponseEntity.status(HttpStatus.OK).body("Transactional success");
            }
            throw new RuntimeException("Money must be > 0");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/sendException", method = RequestMethod.PUT)
    public ResponseEntity<?> sendMoneyWithException(
                                       @RequestParam(name = "money") Integer money,
                                       @RequestParam(name = "senderId") Long senderId,
                                       @RequestParam(name = "recipientId") Long recipientId) {
        try {
            if (money > 0) {
                userService.sendMoneyWithException(senderId, recipientId, money);
                return ResponseEntity.status(HttpStatus.OK).body("Transactional success");
            }
            throw new RuntimeException("Money must be > 0");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
