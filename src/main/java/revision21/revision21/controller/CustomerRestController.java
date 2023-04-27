package revision21.revision21.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import revision21.revision21.model.Customer;
import revision21.revision21.model.Order;
import revision21.revision21.repository.CustomerRepository;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE) 
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository; 

    @GetMapping(path="/customers/")
    public ResponseEntity<String> getAllCustomers() {
        List<Customer> customers = customerRepository.getAllCustomers(); 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
        for (Customer c : customers) {
            arrayBuilder.add(c.toJson()); 
        }
        JsonArray result = arrayBuilder.build();
        return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(result.toString());
    } 

    @GetMapping(path="/customers")
    public ResponseEntity<String> getAllCustomersLimitOffset(
            @RequestParam(required=false) String limit, 
            @RequestParam(required=false) String offset) {
        
        if(Objects.isNull(limit)) limit = "5";
        if(Objects.isNull(offset)) offset = "0";

        List<Customer> customers = customerRepository.getAllCustomersLimitOffset(Integer.parseInt(limit), Integer.parseInt(offset)); 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
        for (Customer c : customers) {
            arrayBuilder.add(c.toJson()); 
        }
        JsonArray result = arrayBuilder.build();
        return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(result.toString());
    } 

    @GetMapping(path="/customer/{customer_id}")
    public ResponseEntity<String> getCustomerById(@PathVariable String customer_id) {

        List<Customer> customer = customerRepository.getCustomerById(Integer.parseInt(customer_id)); 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
        for (Customer c : customer) {
            arrayBuilder.add(c.toJson()); 
        }
        JsonArray result = arrayBuilder.build();
        
        if(result.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("{error_message: record not found}");
        }
        return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(result.toString());

        // JsonArray result = null; 

        // try {
        //     List<Customer> customer = customerRepository.getCustomerById(Integer.parseInt(customer_id)); 
        //     JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
        //     for (Customer c : customer) {
        //         arrayBuilder.add(c.toJson()); 
        //     }
        //     result = arrayBuilder.build();
        // } catch(Exception e) {
        //     System.out.println("***********************************************");
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND)
        //                     .contentType(MediaType.APPLICATION_JSON)
        //                     .body("{error_message: record not found}");
        // }
        // return ResponseEntity.status(HttpStatus.OK)
        //                     .contentType(MediaType.APPLICATION_JSON)
        //                     .body(result.toString());
    } 

    @GetMapping(path="/customer/{customer_id}/orders")
    public ResponseEntity<String> getOrdersByCustomerId(@PathVariable String customer_id) {

        List<Order> order = customerRepository.getOrdersByCustomerId(Integer.parseInt(customer_id)); 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Order o : order) {
            arrayBuilder.add(o.toJson());
        }
        JsonArray result = arrayBuilder.build(); 

        if(result.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("{error_message: record found}");
        }
        return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(result.toString());
    }
}
