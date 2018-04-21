package com.carmanager.carmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class MessageResponse extends  Response{
    private String message;
}