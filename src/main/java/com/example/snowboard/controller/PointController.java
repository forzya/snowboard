package com.example.snowboard.controller;

import com.example.snowboard.entities.Orders;
import com.example.snowboard.entities.Point;
import com.example.snowboard.repository.OrdersRepository;
import com.example.snowboard.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class PointController {
    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/points")
    public String points(Map<String, Object> modell){
        Iterable<Point> points = pointRepository.findAll();
        modell.put("points", points);
        return "points";
    }

    @PostMapping("/points")
    public String addPoints(@RequestParam String address,
                            @RequestParam String phone,
                            @RequestParam String email,
                            Map<String, Object> modell){
        Point point = new Point(address, phone, email);
        pointRepository.save(point);
        Iterable<Point> points = pointRepository.findAll();
        modell.put("points", points);
        return "points";
    }

    @RequestMapping(value="points/delete/{id}", method= RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id,Map<String, Object> modell) {
        Iterable<Orders> orders = ordersRepository.findAll();
        if(orders.iterator().hasNext()){
            for(Orders orders1 : orders)
                if(!(pointRepository.findById(id).get().getId()==orders1.getPoint().getId()))
                    pointRepository.deleteById(id);
        }else
            pointRepository.deleteById(id);
        return "redirect:/points";
    }

    @RequestMapping(value="pointsUpdates/{id}", method= RequestMethod.POST)
    public String clientsUpdates(@PathVariable Integer id,
                                 @RequestParam String address,
                                 @RequestParam String phone,
                                 @RequestParam String email,Map<String, Object> modell){

        Point point =pointRepository.findById(id).get();
        point.setAddress(address);
        point.setPhone(phone);
        point.setEmail(email);
        pointRepository.save(point);

        return "redirect:/points";
    }

    @GetMapping("/pointsUpdates/{id}")
    public String Updates(@PathVariable Integer id,Map<String, Object> modell){
        return "pointsUpdates";
    }
}
