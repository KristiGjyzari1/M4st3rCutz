package com.mastercutz.mastercutz_backend.services;


import com.mastercutz.mastercutz_backend.exception.EmailIsAlradyInUseException;
import com.mastercutz.mastercutz_backend.exception.ScheduleNotFoundException;
import com.mastercutz.mastercutz_backend.exception.WrongUsernameOrPassword;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client registerClient(Client client) throws EmailIsAlradyInUseException {
        System.out.println("Registering client with email: " + client.getEmail());

        // Kontrollo nëse email-i është unikal
        if (clientRepository.findByEmail(client.getEmail()) == null) {
            Client savedClient = clientRepository.save(client);
            System.out.println("Client saved with ID: " + savedClient.getId());
            return savedClient;
        } else {
            throw new EmailIsAlradyInUseException("Email Is Already In Use");
        }
    }

    public Client loginClient(String email, String password) throws WrongUsernameOrPassword {
        Client client = clientRepository.findByEmail(email);
        if (client != null && client.getPassword().equals(password)) {
            return client;
        }else
        throw new WrongUsernameOrPassword("Wrong Username Or Password"); // ose hedh një përjashtim për kredenciale të gabuara
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}