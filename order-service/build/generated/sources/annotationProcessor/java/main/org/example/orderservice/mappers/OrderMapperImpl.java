package org.example.orderservice.mappers;

import java.time.Instant;
import javax.annotation.processing.Generated;
import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.entities.OrderStatus;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-25T14:21:38+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        long productId = 0L;
        long quantity = 0L;
        Instant orderDate = null;
        OrderStatus orderStatus = null;
        long amount = 0L;

        id = order.getId();
        productId = order.getProductId();
        quantity = order.getQuantity();
        orderDate = order.getOrderDate();
        orderStatus = order.getOrderStatus();
        amount = order.getAmount();

        OrderDto orderDto = new OrderDto( id, productId, quantity, orderDate, orderStatus, amount );

        return orderDto;
    }

    @Override
    public Order toModel(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDto.id() );
        order.productId( orderDto.productId() );
        order.quantity( orderDto.quantity() );
        order.orderDate( orderDto.orderDate() );
        order.orderStatus( orderDto.orderStatus() );
        order.amount( orderDto.amount() );

        return order.build();
    }
}
