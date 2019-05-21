package com.example.snowboard.controller;

import com.example.snowboard.entities.*;
import com.example.snowboard.repository.OrderedRepisitory;
import com.example.snowboard.repository.OrdersRepository;
import com.example.snowboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class OrderedController {
    @Autowired
    private OrderedRepisitory orderedRepisitory;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/ordered")
    public String ordered(Map<String, Object> modell){
        Iterable<Orders> orders = ordersRepository.findAll();
        modell.put("orders", orders);

        Iterable<Ordered> ordered = orderedRepisitory.findAll();
        modell.put("ordered", ordered);

        Iterable<Product> products = productRepository.findAll();
        modell.put("products", products);
        return "ordered";
    }

    @PostMapping("/ordered")
    public String addOrdered(@RequestParam Integer price,
                            @RequestParam Integer count,
                            @RequestParam Product products,
                            @RequestParam Orders orders,
                            Map<String, Object> modell){
        Ordered order = new Ordered(price, count, products, orders);
        orderedRepisitory.save(order);

        Iterable<Ordered> ordered = orderedRepisitory.findAll();
        modell.put("ordered", ordered);
        return "redirect:/ordered";
    }

    @RequestMapping(value="ordered/delete/{id}", method= RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id,Map<String, Object> modell) {
        orderedRepisitory.deleteById(id);
        return "redirect:/ordered";
    }

    @RequestMapping(value="orderedUpdates/{id}", method= RequestMethod.POST)
    public String clientsUpdates(@PathVariable Integer id,
                                 @RequestParam Integer price,
                                 @RequestParam Integer count,
                                 @RequestParam Product products,
                                 @RequestParam Orders orders,
                                 Map<String, Object> modell){

        Ordered ordered = orderedRepisitory.findById(id).get();
        ordered.setPrice(price);
        ordered.setCount(count);
        ordered.setProduct(products);
        ordered.setOrders(orders);

        return "redirect:/ordered";
    }

    @GetMapping("/orderedUpdates/{id}")
    public String Updates(@PathVariable Integer id,Map<String, Object> modell){
        Iterable<Orders> orders = ordersRepository.findAll();
        modell.put("orders", orders);

        Iterable<Product> products = productRepository.findAll();
        modell.put("products", products);

        return "orderedUpdates";
    }
}
