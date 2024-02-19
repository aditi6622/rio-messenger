package com.rio.messenger.controller;

import com.rio.messenger.exceptions.IncorrectPasswordException;
import com.rio.messenger.exceptions.UserAlreadyExistsException;
import com.rio.messenger.exceptions.UserDoesNotExistsException;
import com.rio.messenger.service.UserService;
import javax.validation.constraints.NotBlank;
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
  public ResponseEntity createUser(
      @RequestParam @NotBlank String userName, @RequestParam @NotBlank String passcode) {
    try {
      userService.addUser(userName, passcode);
    } catch (UserAlreadyExistsException userAlreadyExistsException) {
      return new ResponseEntity<>(userAlreadyExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception exception) {
      return new ResponseEntity<>(
          "Some error occured while creating user please try again",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>("User created successfully", HttpStatus.ACCEPTED);
  }

  @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity login(
      @RequestParam @NotBlank String userName, @RequestParam @NotBlank String passcode) {
    try {
      userService.validateLoginRequest(userName, passcode);
    } catch (UserDoesNotExistsException | IncorrectPasswordException userDoesNotExistsException) {
      return new ResponseEntity<>(userDoesNotExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception exception) {
      return new ResponseEntity<>(
          "Some error occured while processing login request, please try again",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>( HttpStatus.ACCEPTED);

  }
}
