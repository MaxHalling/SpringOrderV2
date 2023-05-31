package com.example.springorderv2.RestController;

import com.example.springorderv2.DAO.OrdrarRepository;
import com.example.springorderv2.Entity.Customer;
import com.example.springorderv2.Entity.Items;
import com.example.springorderv2.Entity.Ordrar;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrdrarRestController {
    private final OrdrarRepository ordrarRepo;

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(OrdrarRestController.class);

    OrdrarRestController(OrdrarRepository ordrarRepo) {
        this.ordrarRepo = ordrarRepo;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Value("${item-service.url}")
    private String itemServiceBaseUrl;

    @Value("${customer-service.url}")
    private String customerServiceBaseUrl;

    @RequestMapping("/order/{id}/item")
    public Items getOrderItem(@PathVariable Long id) {
        Ordrar temp = ordrarRepo.findById(id).orElse(null);
        if (temp == null) {
            return null;
        }
        Long tempId = temp.getItem();
        String userResourceUrl = itemServiceBaseUrl + "/springitem/items/" + tempId;
        return restTemplate.getForObject(userResourceUrl, Items.class);
    }

    @RequestMapping("/order/{id}/customer")
    public Customer getOrderCustomer(@PathVariable Long id) {
        Ordrar temp = ordrarRepo.findById(id).orElse(null);
        if (temp == null) {
            return null;
        }
        Long tempId = temp.getCustomer();
        String userResourceUrl = customerServiceBaseUrl + "/springcustomer/" + tempId;
        return restTemplate.getForObject(userResourceUrl, Customer.class);
    }

    @RequestMapping("/ordrar/add/{customerId}/{itemId}")
    public Ordrar addOrder(@PathVariable Long customerId, @PathVariable Long itemId) {
        Ordrar o = new Ordrar();
        o.setCustomer(customerId);
        o.setItem(itemId);
        ordrarRepo.save(o);
        return o;
    }

    @RequestMapping("/ordrar")
    public List<Ordrar> getOrdrar() {
        log.info("Successfully returned all the orders");
        return ordrarRepo.findAll();
    }

    @RequestMapping("/ordrar/{id}")
    public Ordrar getOrdrarById(@PathVariable Long id) {
        log.info("Fetching one order");
        return ordrarRepo.findById(id).orElse(null);
    }

    //TODO Add funktion potentiellt
}
