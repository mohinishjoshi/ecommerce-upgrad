package com.upgrad.ecommerce.services;

import com.upgrad.ecommerce.dto.OrderDTO;
import com.upgrad.ecommerce.models.Address;
import com.upgrad.ecommerce.models.Order;
import com.upgrad.ecommerce.models.Product;
import com.upgrad.ecommerce.models.User;
import com.upgrad.ecommerce.repositories.AddressRepository;
import com.upgrad.ecommerce.repositories.OrderRepository;
import com.upgrad.ecommerce.repositories.ProductRepository;
import com.upgrad.ecommerce.repositories.UserRepository;
import com.upgrad.ecommerce.utils.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    public OrderService(final OrderRepository orderRepository, final UserRepository userRepository,
                        final ProductRepository productRepository, final AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("id"));
        return orders.stream()
                .map((order) -> mapToDTO(order, new OrderDTO()))
                .collect(Collectors.toList());
    }

    public OrderDTO get(final String id) {
        return orderRepository.findById(id)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public String create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final String id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final String id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setUser(order.getUser() == null ? null : order.getUser().getId());
        orderDTO.setProduct(order.getProduct() == null ? null : order.getProduct().getId());
        orderDTO.setAddress(order.getAddress() == null ? null : order.getAddress().getId());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setQuantity(orderDTO.getQuantity());
        final User user = orderDTO.getUser() == null ? null : userRepository.findById(orderDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        order.setUser(user);
        final Product product = orderDTO.getProduct() == null ? null : productRepository.findById(orderDTO.getProduct())
                .orElseThrow(() -> new NotFoundException("product not found"));
        order.setProduct(product);
        final Address address = orderDTO.getAddress() == null ? null : addressRepository.findById(orderDTO.getAddress())
                .orElseThrow(() -> new NotFoundException("address not found"));
        order.setAddress(address);
        return order;
    }

}
