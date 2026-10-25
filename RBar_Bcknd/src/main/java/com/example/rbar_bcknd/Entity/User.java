package com.example.rbar_bcknd.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor //Lombok által kinyált paraméterek.
public class User {
    @Id
    @Column(name = "UserIdOrAnyId")
    private String userIdOrAnyId;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "LastSignInDate")
    private LocalDateTime lastSignInDate;

    @Column(name = "RegistrationDate", updatable = false, insertable = false)
    private LocalDateTime registrationDate;

    @Column(name="BirthdayDate")
    private LocalDate birthdayDate;
}
