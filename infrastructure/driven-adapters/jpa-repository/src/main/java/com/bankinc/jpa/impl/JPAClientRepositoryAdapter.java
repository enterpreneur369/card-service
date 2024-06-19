package com.bankinc.jpa.impl;

import com.bankinc.jpa.helper.AdapterOperations;
import com.bankinc.jpa.model.ClientEntity;
import com.bankinc.jpa.repo.JPAClientRepository;
import com.bankinc.model.client.Client;
import com.bankinc.model.client.gateways.ClientRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPAClientRepositoryAdapter extends AdapterOperations<Client, ClientEntity, Integer, JPAClientRepository>
 implements ClientRepository
{

    public JPAClientRepositoryAdapter(JPAClientRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Client.class));
    }

    @Override
    public Client getClientById(Integer id) {
        return repository.findById(id).map(clientEntity -> mapper.map(clientEntity, Client.class))
                .orElse(Client.builder().build());
    }

    @Override
    public Client saveClient(Client client) {
        client.setId(null);
        ClientEntity clientEntity = mapper.map(client, ClientEntity.class);
        ClientEntity savedEntity = repository.save(clientEntity);
        return mapper.map(savedEntity, Client.class);
    }
}
