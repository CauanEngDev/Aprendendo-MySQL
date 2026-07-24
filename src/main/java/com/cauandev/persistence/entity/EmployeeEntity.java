package com.cauandev.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class EmployeeEntity {
    private long id;
    private String name;
    private OffsetDateTime birthday;
    private BigDecimal salary;
    private List<ContactEntity> contacts;
}
