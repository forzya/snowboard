package com.example.snowboard.controller;

import com.example.snowboard.entities.Client;
import com.example.snowboard.entities.Ordered;
import com.example.snowboard.entities.Product;
import com.example.snowboard.repository.ClientRepository;
import com.example.snowboard.repository.OrderedRepisitory;
import com.example.snowboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderedRepisitory orderedRepisitory;

    @GetMapping("/product")
    public String product(Map<String, Object> modell){
        Iterable<Product> products = productRepository.findAll();
        modell.put("products", products);
        Iterable<Client> clients = clientRepository.findAll();
        modell.put("clients", clients);
        return "product";
    }

    @PostMapping("/product")
    public String addProduct(@RequestParam String model,
                             @RequestParam String type,
                             @RequestParam Integer price,
                             Map<String, Object> modell){
        Product product = new Product(model, type, price);
        productRepository.save(product);
        Iterable<Product> products = productRepository.findAll();
        modell.put("products", products);
        return "product";
    }

    @RequestMapping(value="product/delete/{id}", method= RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id,Map<String, Object> modell) {
        Iterable<Ordered> ordereds = orderedRepisitory.findAll();
        if(ordereds.iterator().hasNext()){
            for(Ordered ord : ordereds){
                if(!(productRepository.findById(id).get().getId() == ord.getProduct().getId())){
                    productRepository.deleteById(id);
                }
            }
        }else{
            productRepository.deleteById(id);
        }
        return "redirect:/product";
    }

    @RequestMapping(value="productUpdates/{id}", method= RequestMethod.POST)
    public String clientsUpdates(@PathVariable Integer id,
                                 @RequestParam String model,
                                 @RequestParam String type,
                                 @RequestParam Integer price,Map<String, Object> modell){

        Product product = productRepository.findById(id).get();
        product.setModel(model);
        product.setType(type);
        product.setPrice(price);
        productRepository.save(product);

        return "redirect:/product";
    }

    @GetMapping("/productUpdates/{id}")
    public String Updates(@PathVariable Integer id,Map<String, Object> modell){
        return "productUpdates";
    }
}
