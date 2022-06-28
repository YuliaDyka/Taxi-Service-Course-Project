package ua.lviv.iot.Server.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ua.lviv.iot.Server.Models.Client;
import ua.lviv.iot.Server.Services.ClientService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client")
public final class ClientController extends TaxiController {
   @Autowired
    ClientController(final ClientService clientService) throws IOException {
        service = clientService;
    }

    /**
     * Add Clients (POST request).
     * @param clients
     * @return ResponseEntity
     * @throws IOException
     */
    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody final List<Client> clients) throws IOException {
        for (var client: clients) {
            service.add(client);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Edit client by id.
     * @param id
     * @param client
     * @return ResponseEntity
     * @throws IOException
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") final String id,
                                 @RequestBody final Client client) throws IOException {
        var parseId = Integer.parseInt(id);
        if (service.getById(Integer.parseInt(id)) != null) {
            if (service.edit(parseId, client)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
