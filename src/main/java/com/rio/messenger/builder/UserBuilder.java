package com.rio.messenger.builder;

import com.rio.messenger.entity.User;

public final class UserBuilder {
  public static User buildUser(String userName, String passcode){
    return User.builder().userName(userName).password(passcode).build();
  }
}
