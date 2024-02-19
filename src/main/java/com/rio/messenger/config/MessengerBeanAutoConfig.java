package com.rio.messenger.config;

import com.rio.messenger.repository.UserRepository;
import com.rio.messenger.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerBeanAutoConfig {
  @Bean
  UserService userService(UserRepository userRepository){
    return new UserService(userRepository);
  }
}
