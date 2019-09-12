package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    private CustomerService customersService;

    @GetMapping(value = "/order", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customers> myCustomers = customersService.findAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{custname}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerByName(@PathVariable String custname)
    {
        Customers myCustomer = customersService.findByCustomerName(custname);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }

    @PostMapping(value = "/new",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody
                                                    Customers newCustomer) throws URISyntaxException
    {
        newCustomer = customersService.save(newCustomer);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custid}").buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{custid}")
    public ResponseEntity<?> deleteCustomerById(
            @PathVariable
                    long custid)
    {
        customersService.delete(custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{custid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> updateCustomer(
            @RequestBody
                    Customers updateCustomer,
            @PathVariable
                    long custid)
    {
        customersService.update(updateCustomer, custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
