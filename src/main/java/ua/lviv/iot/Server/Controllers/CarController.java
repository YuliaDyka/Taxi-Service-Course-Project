package ua.lviv.iot.Server.Controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.Server.Models.Car;
import ua.lviv.iot.Server.Models.Driver;
import ua.lviv.iot.Server.Services.CarService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController  extends TaxiController {
    @Autowired
    CarController(CarService carService) throws IOException {
        service = carService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody final List<Car> cars) throws IOException {
        for (var car: cars) {
            service.add(car);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") final String id,
                                 @RequestBody final Car car) throws IOException {
        var parseId = Integer.parseInt(id);
        if (service.getById(Integer.parseInt(id)) != null) {
            if (service.edit(parseId, car)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
