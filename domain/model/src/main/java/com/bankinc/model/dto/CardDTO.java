package com.bankinc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CardDTO {
    private Integer id;
    private String number;
    private String clientName;
    private Long balance;
    private String currency;
}
