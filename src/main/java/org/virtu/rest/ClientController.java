package org.virtu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.virtu.domain.Client;
import org.virtu.dto.ClientDTO;
import org.virtu.dto.CreateClientDTO;
import org.virtu.dto.UpdateClientDTO;
import org.virtu.repository.ClientRepository;
import org.virtu.service.ClientService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/search")
    public ResponseEntity<List> getList(@RequestParam(value = "surName", required = false) String surName,
                                        @RequestParam(value = "firstName", required = false) String firstName,
                                        @RequestParam(value = "middleName", required = false) String middleName
                                        ) {
        List<ClientDTO> all = clientService.search(surName, firstName, middleName);
        return ResponseEntity.ok(all);
    }


    @GetMapping
    public ResponseEntity<List> getList() {
        List<Client> all = clientService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> get(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            ClientDTO clientDTO = new ClientDTO(client.get());
            return ResponseEntity.ok(clientDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Client> create(@Valid @RequestBody CreateClientDTO dto) {
        Client client = clientService.create(dto);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody UpdateClientDTO dto) {

        Client client = null;
        try {
            client = clientService.update(id, dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        try {
            clientService.delete(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}
