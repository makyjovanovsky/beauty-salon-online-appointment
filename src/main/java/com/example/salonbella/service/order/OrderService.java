package com.example.salonbella.service.order;

import com.example.salonbella.controller.cart.ShoppingCart;
import com.example.salonbella.entity.CartDetailEntity;
import com.example.salonbella.entity.OrderEntity;
import com.example.salonbella.entity.UserEntity;
import com.example.salonbella.repository.OrderRepository;
import com.example.salonbella.repository.ProductRepository;
import com.example.salonbella.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private static final String ACCOUNT_SID = "AC773ecbd8592a5cad8169cd06c3ce6e13";
    private static final String AUTH_TOKEN = "51247e92a82c11f5a929c4e3ae6c2953";

    @Autowired
    public OrderService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public void saveOrder(ShoppingCart shoppingCart) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userRepository.findByUsername(username);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCartDetails(shoppingCart.getCartDetails());
        orderEntity.setUser(user);
        orderEntity.setLocalDate(LocalDate.now());
        orderEntity.setTotal(calculateTotalPrice(shoppingCart));
        orderEntity.setStatus("PENDING");
        orderRepository.save(orderEntity);
    }

    public void changeQuantities(Map<String, String> allParams, ShoppingCart shoppingCart) {
        for (String id : allParams.keySet()) {
            for (CartDetailEntity c : shoppingCart.getCartDetails()) {
                if (c.getId() == Long.parseLong(id)) {
                    c.setQuantity(Integer.parseInt(allParams.get(id)));
                    break;
                }
            }
        }
    }


    public double calculateTotalPrice(ShoppingCart shoppingCart) {
        double total = 0.0;
        for (CartDetailEntity c : shoppingCart.getCartDetails()) {
            total += (c.getQuantity() * productRepository.findById(c.getId()).get().getPrice());
        }
        return total;
    }

    public void clearCart(ShoppingCart shoppingCart) {
        for (int i = 0; i < shoppingCart.getCartDetails().size(); i++) {
            shoppingCart.getCartDetails().remove(i);
            i--;
        }
    }

    public List<OrderEntity> getUserOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return makeOrderDetails(username);
    }

    public List<OrderEntity> makeOrderDetails(String username) {
        UserEntity user = userRepository.findByUsername(username);
        List<OrderEntity> orderEntityList = orderRepository.findAllByUser(user);
        for (OrderEntity order : orderEntityList) {
            for (CartDetailEntity cartDetailEntity : order.getCartDetails()) {
                String name = productRepository.findById(cartDetailEntity.getId()).get().getName();
                int quantity = cartDetailEntity.getQuantity();
                order.getOrderDetails().add(new OrderDetail(name, quantity));
            }
        }
        return orderEntityList;
    }

    @Transactional
    public void cancelOrder(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            if (getUser().getId().equals(orderEntity.get().getUser().getId())) {
                orderRepository.delete(orderEntity.get());
            }
        }
    }

    @Transactional
    public void cancelOrderAdmin(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        orderEntity.ifPresent(orderRepository::delete);
    }

    @Transactional
    public void changeOrderStatus(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        orderEntity.ifPresent(entity -> entity.setStatus("AWAITING PICKUP"));
//        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
//        Message message = Message.creator(
//                        new com.twilio.type.PhoneNumber(getUser().getNumber().replaceFirst("0","+389")),
//                        new com.twilio.type.PhoneNumber("+1 815 567 9673"),
//                        "YOUR ORDER IS HERE")
//                .create();
    }

    public UserEntity getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userRepository.findByUsername(username);
        return user;
    }

    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll();
        for (OrderEntity order : orderEntities) {
            for (CartDetailEntity cartDetailEntity : order.getCartDetails()) {
                String name = productRepository.findById(cartDetailEntity.getId()).get().getName();
                int quantity = cartDetailEntity.getQuantity();
                order.getOrderDetails().add(new OrderDetail(name, quantity));
            }
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setOrderDetails(order.getOrderDetails());
            orderResponse.setLocalDate(order.getLocalDate());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setTotal(order.getTotal());
            orderResponse.setName(order.getUser().getName());
            orderResponse.setSurname(order.getUser().getSurname());
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

}

