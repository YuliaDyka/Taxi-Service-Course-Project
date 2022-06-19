package ua.lviv.iot.Server.Controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.Server.Models.Car;
import ua.lviv.iot.Server.Models.Order;
import ua.lviv.iot.Server.Services.ClientService;
import ua.lviv.iot.Server.Services.OrderService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController extends TaxiController {
    @Autowired
    OrderController(OrderService orderService) throws IOException {
        service = orderService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody final List<Order> orders) throws IOException {
        for (var order: orders) {
            service.add(order);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") final String id,
                                 @RequestBody final Order order) throws IOException {
        var parseId = Integer.parseInt(id);
        if (service.getById(Integer.parseInt(id)) != null) {
            if (service.edit(parseId, order)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
