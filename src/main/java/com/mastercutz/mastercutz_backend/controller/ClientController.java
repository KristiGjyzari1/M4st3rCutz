package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.dto.UserProfileUpdateDto;
import com.mastercutz.mastercutz_backend.exception.EmailIsAlradyInUseException;
import com.mastercutz.mastercutz_backend.exception.WrongUsernameOrPassword;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public Client registerClient(@RequestBody Client client) throws EmailIsAlradyInUseException {
        return clientService.registerClient(client);
    }

    @PostMapping("/login")
    public Client loginClient(@RequestBody Client client) throws WrongUsernameOrPassword {
        return clientService.loginClient(client.getEmail(), client.getPassword());
    }

    @GetMapping("/getAll")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<Client> updateProfile(@PathVariable Long id, @RequestBody UserProfileUpdateDto updateDto) {
        Client updatedClient = clientService.updateUserProfile(id, updateDto);
        return ResponseEntity.ok(updatedClient);
    }
}
