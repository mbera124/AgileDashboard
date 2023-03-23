package com.react.reactspring.client.api;

import com.react.reactspring.client.domain.Client;
import com.react.reactspring.client.domain.ClientRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

        private final ClientRepository clientRepository;

        @GetMapping("/get-all")
        public List<Client> getClients() {
            return clientRepository.findAll();
        }

        @GetMapping("/{id}")
        public Client getClient(@PathVariable Long id) {
            return clientRepository.findById(id).orElseThrow(RuntimeException::new);
        }

        @PostMapping
        public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
            Client savedClient = clientRepository.save(client);
            return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
        }

        @PutMapping("/{id}")
        public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client) {
            Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
                currentClient.setName(client.getName());
            currentClient.setEmail(client.getEmail());
            currentClient = clientRepository.save(client);

            return ResponseEntity.ok(currentClient);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity deleteClient(@PathVariable Long id) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
}
