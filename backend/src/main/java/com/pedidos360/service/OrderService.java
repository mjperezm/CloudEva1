package com.pedidos360.service;
import com.pedidos360.dto.OrderDTO;
import com.pedidos360.entity.Order;
import com.pedidos360.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id).map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerEmail(orderDTO.getCustomerEmail());
        order.setDescription(orderDTO.getDescription());
        order.setAmount(orderDTO.getAmount());
        order.setStatus(orderDTO.getStatus());
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getOrderNumber(),
                order.getCustomerName(), order.getCustomerEmail(),
                order.getDescription(), order.getAmount(), order.getStatus(),
                order.getCreatedAt(), order.getUpdatedAt(), order.getCreatedBy());
    }

    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderNumber(dto.getOrderNumber());
        order.setCustomerName(dto.getCustomerName());
        order.setCustomerEmail(dto.getCustomerEmail());
        order.setDescription(dto.getDescription());
        order.setAmount(dto.getAmount());
        order.setStatus(dto.getStatus());
        order.setCreatedBy(dto.getCreatedBy());
        return order;
    }
}
