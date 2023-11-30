package com.example.demoreactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Integer id;
    private String name;
    private Integer age;
}
