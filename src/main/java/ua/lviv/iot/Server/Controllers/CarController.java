package ua.lviv.iot.Server.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ua.lviv.iot.Server.Models.Car;
import ua.lviv.iot.Server.Services.CarService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/car")
public final class CarController  extends TaxiController {
    @Autowired
    CarController(final CarService carService) throws IOException {
        service = carService;
    }

    /**
     * Add cars (POST request).
     * @param cars
     * @return ResponseEntity
     * @throws IOException
     */
    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody final List<Car> cars) throws IOException {
        for (var car: cars) {
            service.add(car);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Edit car by id.
     * @param id
     * @param car
     * @return ResponseEntity
     * @throws IOException
     */
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
