package com.rio.messenger.repository;

import com.rio.messenger.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  User findByUserName(String userName);
  @Query(value = "select * from rio_user", nativeQuery = true)
  List<User> fetchAllUsers();
}
