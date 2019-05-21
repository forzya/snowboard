package com.example.snowboard.controller;

import com.example.snowboard.entities.Client;
import com.example.snowboard.entities.Orders;
import com.example.snowboard.repository.ClientRepository;
import com.example.snowboard.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/clients")
    public String clients(Map<String, Object> modell){
        Iterable<Client> clients = clientRepository.findAll();
        modell.put("clients", clients);
        return "clients";
    }

    @PostMapping("/clients")
    public String addClients(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone, Map<String, Object> modell){
        Client client = new Client(firstName, lastName,phone);
        clientRepository.save(client);
        Iterable<Client> clients = clientRepository.findAll();
        modell.put("clients", clients);
        return "clients";
    }

    @RequestMapping(value="clients/delete/{id}", method= RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id,Map<String, Object> modell) {
        Iterable<Orders> orders = ordersRepository.findAll();
        if(orders.iterator().hasNext()){
            for(Orders orders1 : orders)
                if(!(clientRepository.findById(id).get().getId()==orders1.getClient().getId()))
                    clientRepository.deleteById(id);
        }else
            clientRepository.deleteById(id);

        return "redirect:/clients";
    }

    @RequestMapping(value="clientsUpdates/{id}", method= RequestMethod.POST)
    public String clientsUpdates(@PathVariable Integer id,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String phone,Map<String, Object> modell){

        Client client = clientRepository.findById(id).get();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);
        clientRepository.save(client);

        return "redirect:/clients";
    }

    @GetMapping("/clientsUpdates/{id}")
    public String Updates(@PathVariable Integer id,Map<String, Object> modell){
        return "clientsUpdates";
    }
}
