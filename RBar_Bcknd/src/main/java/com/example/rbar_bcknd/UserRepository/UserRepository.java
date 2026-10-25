package com.example.rbar_bcknd.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.rbar_bcknd.Entity.User;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT CASE WHEN COUNT(u.userIdOrAnyId)>0 THEN true ELSE false END "+
            "FROM User u WHERE u.userIdOrAnyId= :userIdOrAnyId")
    boolean existsByUserDecision(@Param("userIdOrAnyId") String userIdOrAnyId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.lastSignInDate = CURRENT_TIMESTAMP WHERE u.userIdOrAnyId=:userIdOrAnyId")
    int updateSignInDate(@Param("userIdOrAnyId") String userIdOrANyId);
}
