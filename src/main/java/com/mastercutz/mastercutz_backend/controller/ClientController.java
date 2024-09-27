package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public Client registerClient(@RequestBody Client client) {
        return clientService.registerClient(client);
    }

    @PostMapping("/login")
    public Client loginClient(@RequestBody Client client) {
        return clientService.loginClient(client.getEmail(), client.getPassword());
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
