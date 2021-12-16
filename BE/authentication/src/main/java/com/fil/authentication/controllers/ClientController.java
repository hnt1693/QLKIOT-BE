package com.fil.authentication.controllers;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.ClientDetails;
import com.fil.authentication.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth/client")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Object getAll() throws Exception {
        return clientService.getAllClient();
    }

    @PostMapping
    public ResponseAPI create(@Valid @RequestBody ClientDetails clientDetails) throws Exception {
        return clientService.create(clientDetails);
    }

    @PutMapping
    public ResponseAPI update(@Valid @RequestBody ClientDetails clientDetails) throws Exception {
        return clientService.put(clientDetails);
    }

    @GetMapping("/{id}")
    public ResponseAPI update(@PathVariable String id) throws Exception {
        return clientService.findById(id);
    }

    @DeleteMapping
    public ResponseAPI update(@RequestBody List<String> ids) throws Exception {
        return clientService.deleteByStringIds(ids);
    }
}
