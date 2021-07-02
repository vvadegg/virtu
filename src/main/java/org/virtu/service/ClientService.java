package org.virtu.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.virtu.domain.Client;
import org.virtu.dto.ClientDTO;
import org.virtu.dto.CreateClientDTO;
import org.virtu.dto.UpdateClientDTO;
import org.virtu.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ClientRepository clientRepository;

    public Client create(CreateClientDTO clientDTO) {

        Client client = new Client();

        client.setSurName(clientDTO.getSurName());
        client.setFirstName (clientDTO.getFirstName());
        client.setMiddleName(clientDTO.getMiddleName());
        client.setBirthday(clientDTO.getBirthday());

        client.setPassSeries (clientDTO.getPassSeries());
        client.setPassNumber (clientDTO.getPassNumber());

        Client save = clientRepository.save(client);

        return save;

    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client update(Integer id, UpdateClientDTO clientDTO) throws EntityNotFoundException {
        Optional<Client> optClient = clientRepository.findById(id);
        if (!optClient.isPresent()) {
            throw new EntityNotFoundException();
        }

        Client client = optClient.get();

        client.setSurName(clientDTO.getSurName());
        client.setFirstName(clientDTO.getFirstName());
        client.setMiddleName(clientDTO.getMiddleName());

        client.setBirthday(clientDTO.getBirthday());

        client.setPassSeries(clientDTO.getPassSeries());
        client.setPassNumber(clientDTO.getPassNumber());

        Client saveClient = clientRepository.save(client);

        return saveClient;
    }

    public void delete(Integer id) throws EntityNotFoundException {
        Optional<Client> optClient = clientRepository.findById(id);
        if (!optClient.isPresent()) {
            throw new EntityNotFoundException();
        }

        Client client = optClient.get();
        clientRepository.delete(client);
    }


    public List<ClientDTO> search(String surName, String firstName, String middleName) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);

        Map<String, Object> parameters = new HashMap<>();

        List<Predicate> predicates = new ArrayList<>();

        if (surName != null) {
            ParameterExpression<String> surNameParam = criteriaBuilder.parameter(String.class, "surName");
            parameters.put("surName", "%" + surName.toLowerCase() + "%");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("surName")), surNameParam));
        }

        if (firstName != null) {
            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class, "firstName");
            parameters.put("firstName", "%" + firstName.toLowerCase() + "%");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), firstNameParam));
        }

        if (middleName != null) {
            ParameterExpression<String> middleNameParam = criteriaBuilder.parameter(String.class, "middleName");
            parameters.put("middleName", "%" + middleName.toLowerCase() + "%");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("middleName")), middleNameParam));
        }

        query.select(root).where(predicates.toArray(new Predicate[]{}));

        Query<Client> clientQuery = session.createQuery(query).setProperties(parameters);

        final List<Client> list = clientQuery.getResultList();

        return list.stream().map(ClientDTO::new).collect(Collectors.toList());

    }


}
