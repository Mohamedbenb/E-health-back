package com.Ehealth.spring.models;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor

@Builder
@Data
@ToString
public class Message {
    private String senderName;
    private String receiverName;
    private String  message;
    private String date;
    private Status status;
}
