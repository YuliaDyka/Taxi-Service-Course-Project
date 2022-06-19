package ua.lviv.iot.Server.Controllers;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.Server.Models.Driver;
import ua.lviv.iot.Server.Models.TaxiEntity;
import ua.lviv.iot.Server.Services.TaxiService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class TaxiController {
    protected TaxiService service;

    @GetMapping
    public String getDefault() {
        return service.toStringAll();
    }

    @GetMapping("/getById{id}")
    public TaxiEntity getById(@RequestParam String id) {
        return service.getById(Integer.parseInt(id));
    }

    @GetMapping("/getAll")
    public List<TaxiEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/getStringById{id}")//  .../getStringById?id=13
    public String getStringById(@RequestParam String id) {
        return service.toStringById(Integer.parseInt(id));
    }

    @GetMapping("/getStringAll")
    public String getStringAll() {
        return service.toStringAll();
    }

    //http://localhost:8080/driver/add?name=Yulia&phone=380665544321&experience=2%20years%205%20monts
    @GetMapping("/add")
    public String add(@RequestParam Map<String, String> params) throws IOException {
        return service.addEntity(params);
    }

    @GetMapping("/remove{id}")
    public String remove(@RequestParam String id) throws IOException {
        return service.removeById(Integer.parseInt(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(name = "id") final String id) throws IOException {
        return service.removeById(Integer.parseInt(id));
    }
}
