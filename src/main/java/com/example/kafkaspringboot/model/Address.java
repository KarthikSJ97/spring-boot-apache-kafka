package com.example.kafkaspringboot.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String country;
    private String state;
    private String city;
    private String area;
    private long pinCode;
}
