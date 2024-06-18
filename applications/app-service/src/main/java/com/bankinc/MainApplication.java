package com.bankinc;

import com.bankinc.jpa.model.CardEntity;
import com.bankinc.jpa.model.ClientEntity;
import com.bankinc.model.card.Card;
import com.bankinc.model.card.CardState;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.client.Client;
import com.bankinc.model.client.gateways.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}