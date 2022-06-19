package ua.lviv.iot.Server.Controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.Server.Models.Car;
import ua.lviv.iot.Server.Models.Client;
import ua.lviv.iot.Server.Services.ClientService;
import ua.lviv.iot.Server.Services.DriverService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController extends TaxiController {
   @Autowired
    ClientController(ClientService clientService) throws IOException {
        service = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody final List<Client> clients) throws IOException {
        for (var client: clients) {
            service.add(client);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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
