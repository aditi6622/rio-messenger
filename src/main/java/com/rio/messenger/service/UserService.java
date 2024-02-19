package com.rio.messenger.service;

import com.rio.messenger.builder.UserBuilder;
import com.rio.messenger.entity.User;
import com.rio.messenger.exceptions.IncorrectPasswordException;
import com.rio.messenger.exceptions.UserAlreadyExistsException;
import com.rio.messenger.exceptions.UserDoesNotExistsException;
import com.rio.messenger.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void addUser(String username, String passcode) throws Exception {
    User user = userRepository.findByUserName(username);
    if (Objects.isNull(user)) {
      user = UserBuilder.buildUser(username, passcode);
      userRepository.save(user);
      return;
    }
    throw new UserAlreadyExistsException("User already exists");
  }

  public void validateLoginRequest(String username, String passcode) throws Exception {
    User user = userRepository.findByUserName(username);
    if (Objects.isNull(user)) {
      throw new UserDoesNotExistsException("User does not exists, Please sign in");
    }
    if (!passcode.equals(user.getPassword())) {
      throw new IncorrectPasswordException("Password is incorrect.");
    }
  }
}
