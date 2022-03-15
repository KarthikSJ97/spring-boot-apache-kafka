package com.example.kafkaspringboot.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String firstName;
    private String lastName;
    private String emailId;
    private Address address;
    private String mobileNumber;
}
