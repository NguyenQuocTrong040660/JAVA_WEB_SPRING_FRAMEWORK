package com.training.fa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.fa.model.OrderDetail;
import com.training.fa.repository.OrderDetailRepository;

@ExtendWith(MockitoExtension.class)
public class OrderDetailServiceTest {

	@Mock
	OrderDetailRepository orderDetailRepository;
	
	@InjectMocks
	OrderDetailServicelmpl orderDetailService;
	
	OrderDetail orderDetail;
	
	List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
	@BeforeEach
	public void setup() {
		orderDetail = new OrderDetail();
		orderDetail.setId(1);
		orderDetail.setPrice(100);
		
		for (int i = 2; i <= 8; i++) {
			OrderDetail detail = new OrderDetail();
			detail.setId(i);
			detail.setPrice(100+i);
			orderDetails.add(detail);
		}
	}
	
	// JUnit test for saveOrder method
    @DisplayName("JUnit test for saveOrderDetail method")
    @Test
    public void givenOrderDetailObject_whenSaveOrderDetail_thenReturnOrderDetailObject(){
        // given - precondition or setup
    	
        given(orderDetailRepository.save(orderDetail)).willReturn(orderDetail);

        // when -  action or the behavior that we are going test
        OrderDetail savedOrderDetail = orderDetailService.saveOrderDetail(orderDetail);

        // then - verify the output
        assertThat(savedOrderDetail).isNotNull();
    }
    
    // JUnit test for getOrderDetail by Id method
    @DisplayName("JUnit test for getOrderDetail(int id) method")
    @Test
    public void givenOrderDetailId_whenGetOrderDetailById_thenReturnOrderDetailObject(){
        // given
        given(orderDetailRepository.findById(1)).willReturn(Optional.of(orderDetail));

        // when
        OrderDetail savedOrderDetail = orderDetailService.getOrderDetail(orderDetail.getId());

        // then
        assertThat(savedOrderDetail).isNotNull();

    }
    
    // JUnit test for getOrderDetail by Id method then throw exception
    @DisplayName("JUnit test for getOrderDetail(int id) method then throw exception")
    @Test
    public void givenOrderDetailId_whenGetOrderDetailById_thenThrowException(){
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		orderDetailService.getOrderDetail(0);
        });

        String expectedMessage = "not find by id entity OrderDetail!!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }    
    // JUnit test for deleteOrderDetail method
    @DisplayName("JUnit test for deleteOrderDetail method")
    @Test
    public void givenOrderDetailId_whenDeleteOrderDetail_thenNothing(){
        // given - precondition or setup
        int id = 1;

        willDoNothing().given(orderDetailRepository).deleteById(id);

        // when -  action or the behavior that we are going test
        orderDetailService.deleteOrderDetail(id);

        // then - verify the output
        verify(orderDetailRepository, times(1)).deleteById(id);
    }
    
    // JUnit test for updateOrderDetail method
    @DisplayName("JUnit test for updateOrderDetail method")
    @Test
    public void givenOrderDetailObject_whenUpdateOrderDetail_thenReturnUpdatedOrderDetail(){
    	
    	// given
        given(orderDetailRepository.save(orderDetail)).willReturn(orderDetail);
        orderDetail.setPrice(11);
        Date date = new Date();
        orderDetail.setUpdateTime(date);
        // when
        OrderDetail updatedOrderDetail = orderDetailService.updateOrderDetail(orderDetail);

        // then
        assertThat(updatedOrderDetail.getPrice()).isEqualTo(11);
        assertThat(updatedOrderDetail.getUpdateTime().toString()).isEqualTo(date.toString());
    }
    
    // JUnit test for getOrderDetailById method
    @DisplayName("JUnit test for getOrderDetailById method")
    @Test
    public void whenGetOrderDetailById_thenReturnList(){
        // given - precondition or setup
    	
        given(orderDetailRepository.findByOrderId(1)).willReturn(orderDetails);

        // when -  action or the behavior that we are going test
        List<OrderDetail> orderDetails1 = orderDetailService.findByOrderID(1);

        // then - verify the output
        assertThat(orderDetails1.size()).isEqualTo(orderDetails.size());
        assertThat(orderDetails1).isEqualTo(orderDetails);
    }
    
}
