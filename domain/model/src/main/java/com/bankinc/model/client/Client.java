package com.bankinc.model.client;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Client {
    private Integer id;
    private String name;
    private String surname;
}
