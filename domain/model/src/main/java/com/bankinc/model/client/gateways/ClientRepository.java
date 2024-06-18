package com.bankinc.model.client.gateways;

import com.bankinc.model.client.Client;

public interface ClientRepository {
    Client getClientById(Integer id);
    Client saveClient(Client client);
}
