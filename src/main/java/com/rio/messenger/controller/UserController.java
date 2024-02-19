package com.rio.messenger.controller;

import com.rio.messenger.exceptions.UserAlreadyExistsException;
import com.rio.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
  @Autowired UserService userService;

  @PostMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createUser(@RequestParam String userName, @RequestParam String passcode) {
    try {
      userService.addUser(userName, passcode);
    } catch (UserAlreadyExistsException userAlreadyExistsException) {
      return new ResponseEntity<>(userAlreadyExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception exception) {
      return new ResponseEntity<>(
          "Some error occured while creating user please try again",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>("User created successfully", HttpStatus.BAD_REQUEST);
  }
}
