package com.example.snowboard.controller;

import com.example.snowboard.entities.Client;
import com.example.snowboard.entities.Ordered;
import com.example.snowboard.entities.Orders;
import com.example.snowboard.entities.Point;
import com.example.snowboard.repository.ClientRepository;
import com.example.snowboard.repository.OrderedRepisitory;
import com.example.snowboard.repository.OrdersRepository;
import com.example.snowboard.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private OrderedRepisitory orderedRepisitory;

    @GetMapping("/orders")
    public String orders(Map<String, Object> modell){
        Iterable<Orders> orders = ordersRepository.findAll();
        modell.put("orders", orders);

        Iterable<Client> clients = clientRepository.findAll();
        modell.put("clients", clients);

        Iterable<Point> points = pointRepository.findAll();
        modell.put("points", points);
        return "orders";
    }

    @PostMapping("/orders")
    public String addOrders(@RequestParam String orderedDate,
                             @RequestParam String executionDate,
                             @RequestParam Point points,
                            @RequestParam Client clients,
                             Map<String, Object> modell){
        Orders order = new Orders(orderedDate, executionDate,points,clients);
        ordersRepository.save(order);

        Iterable<Orders> orders = ordersRepository.findAll();
        modell.put("orders", orders);
        return "redirect:/orders";
    }

    @RequestMapping(value="orders/delete/{id}", method= RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id,Map<String, Object> modell) {
        Iterable<Ordered> ordereds = orderedRepisitory.findAll();
        if(ordereds.iterator().hasNext()){
            for(Ordered ordered : ordereds)
                if(!(ordersRepository.findById(id).get().getId()==ordered.getOrders().getId()))
                    ordersRepository.deleteById(id);
        }else
            ordersRepository.deleteById(id);

        return "redirect:/orders";
    }

    @RequestMapping(value="ordersUpdates/{id}", method= RequestMethod.POST)
    public String clientsUpdates(@PathVariable Integer id,
                                 @RequestParam String orderedDate,
                                 @RequestParam String executionDate,
                                 @RequestParam Point points,
                                 @RequestParam Client clients,
                                 Map<String, Object> modell){

        Orders orders = ordersRepository.findById(id).get();
        orders.setOrderedDate(orderedDate);
        orders.setExecutionDate(executionDate);
        orders.setPoint(points);
        orders.setClient(clients);
        ordersRepository.save(orders);

        return "redirect:/orders";
    }

    @GetMapping("/ordersUpdates/{id}")
    public String Updates(@PathVariable Integer id,Map<String, Object> modell){
        Iterable<Client> clients = clientRepository.findAll();
        modell.put("clients", clients);

        Iterable<Point> points = pointRepository.findAll();
        modell.put("points", points);
        return "ordersUpdates";
    }
}
