package ua.lviv.iot.Server.Controllers;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import ua.lviv.iot.Server.Models.TaxiEntity;
import ua.lviv.iot.Server.Services.TaxiService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class TaxiController {
    /**
     * TaxiEntity service.
     */
    protected TaxiService service;

    /**
     * Default request for controllers (http://localhost:8080/{controllerMapping}).
     * @return A string to out in browser
     */
    @GetMapping
    public String getDefault() {
        return service.toStringAll();
    }

    /**
     * Returns entity by id.
     * @param id
     * @return A TaxiEntity object with id={id}
     */
    @GetMapping("/getById{id}")
    public TaxiEntity getById(@RequestParam final String id) {
        return service.getById(Integer.parseInt(id));
    }

    /**
     * Returns all entities.
     * @return List of all TaxiEntities objects
     */
    @GetMapping("/getAll")
    public List<TaxiEntity> getAll() {
        return service.getAll();
    }

    /**
     * Returns a formatted string of entity by id.
     * @param id - id of entity
     * @return String to show in browser
     */
    @GetMapping("/getStringById{id}")//  .../getStringById?id=13
    public String getStringById(@RequestParam final String id) {
        return service.toStringById(Integer.parseInt(id));
    }

    /**
     * Returns a formatted string of all entities.
     * @return String to show in browser
     */
    @GetMapping("/getStringAll")
    public String getStringAll() {
        return service.toStringAll();
    }

    /**
     * Add an entity using GET request.
     * @param params - Map of parameters from address line
     * @return A formatted string with added entity
     * @throws IOException
     */
    @GetMapping("/add")
    public String add(@RequestParam final Map<String, String> params) throws IOException {
        return service.addEntity(params);
    }

    /**
     * Remove an entity using GET request.
     * @param id - entity id to remove
     * @return String with operation result
     * @throws IOException
     */
    @GetMapping("/remove{id}")
    public String remove(@RequestParam final String id) throws IOException {
        return service.removeByIdString(Integer.parseInt(id));
    }

    /**
     * Remove an entity using DELETE request.
     * @param id - entity id to remove
     * @return ResponseEntity
     * @throws IOException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") final String id) throws IOException {
        if (service.removeById(Integer.parseInt(id)) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
