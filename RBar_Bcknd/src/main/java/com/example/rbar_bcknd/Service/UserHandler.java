package com.example.rbar_bcknd.Service;

import com.example.rbar_bcknd.UserRepository.UserRepository;
import com.example.rbar_bcknd.Entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public Boolean UserCheck(String userIdOrSubId){
        if(userRepository.existsByUserDecision(userIdOrSubId))
        { //Módosított sorok száma...
            int answer=userRepository.updateSignInDate(userIdOrSubId);
            return false; //Nincs szükség új felhasználóra.
        }
        else{ return false; /*Szükség van új felhasználóra*/}
    }

    public void UserCreateInDB(String userIdOrSubId, String email, String fullName, LocalDate birthdayDate){
        User newUser=new User();
        newUser.setUserIdOrAnyId(userIdOrSubId);
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setBirthdayDate(birthdayDate);
        newUser.setLastSignInDate(LocalDateTime.now());
        userRepository.save(newUser);
    }
}
