package com.nylgsc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private long id;
    private BigDecimal account;
}
