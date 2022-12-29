package com.training.fa.service;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import com.training.fa.model.Order;
import com.training.fa.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;
	
	@InjectMocks
	OrderServicelmpl orderService;
	
	Order order;
	
	@BeforeEach
	public void setup() {
		order = new Order(1, 0, null, null, null);
				
	}
	
	// JUnit test for saveOrder method
    @DisplayName("JUnit test for saveOrder method")
    @Test
    public void givenOrderObject_whenSaveOrder_thenReturnOrderObject(){
        // given - precondition or setup
    	
        given(orderRepository.save(order)).willReturn(order);

        System.out.println(orderRepository);
        System.out.println(orderService);

        // when -  action or the behavior that we are going test
        Order savedOrder = orderService.saveOrder(order);

        System.out.println(savedOrder);
        // then - verify the output
        assertThat(savedOrder).isNotNull();
    }
    
 
    // JUnit test for getOrder by Id method
    @DisplayName("JUnit test for getOrder(int id) method")
    @Test
    public void givenOrderId_whenGetOrderById_thenReturnOrderObject(){
        // given
        given(orderRepository.findById(1)).willReturn(Optional.of(order));

        // when
        Order savedOrder = orderService.getOrder(order.getId());

        // then
        assertThat(savedOrder).isNotNull();

    } 
    
    // JUnit test for getOrder by Id method then throw Exception
    @DisplayName("JUnit test for getOrder(int id) method then throw exception")
    @Test
    public void givenOrderId_whenGetOrderById_thenThrowException(){
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		orderService.getOrder(0);
        });

        String expectedMessage = "not find by id entity Order!!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    
 // JUnit test for updateOrder method
    @DisplayName("JUnit test for updateOrder method")
    @Test
    public void givenOrderObject_whenUpdateOrder_thenReturnUpdatedOrder(){
    	
    	// given
        given(orderRepository.save(order)).willReturn(order);
        order.setOrderStatus(1);
        Date date = new Date();
        order.setUpdateTime(date);
        // when
        Order updatedOrder = orderService.updateOrder(order);

        // then
        assertThat(updatedOrder.getOrderStatus()).isEqualTo(1);
        assertThat(updatedOrder.getUpdateTime().toString()).isEqualTo(date.toString());
    }

    // JUnit test for deleteOrder method
    @DisplayName("JUnit test for deleteOrder method")
    @Test
    public void givenOrderId_whenDeleteOrder_thenNothing(){
        // given - precondition or setup
        int id = 1;

        willDoNothing().given(orderRepository).deleteById(id);

        // when -  action or the behavior that we are going test
        orderService.deleteOrder(id);

        // then - verify the output
        verify(orderRepository, times(1)).deleteById(id);
    }

}
