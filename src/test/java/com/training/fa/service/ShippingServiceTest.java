package com.training.fa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.fa.model.Shipping;
import com.training.fa.repository.ShippingRepository;

@ExtendWith(MockitoExtension.class)
public class ShippingServiceTest {

	@Mock
	ShippingRepository shippingRepository;
	
	@InjectMocks
	ShippingServicelmpl shippingService;
	
	Shipping shipping;
	
	List<Shipping> shippings;
	
	@BeforeEach
	public void setup () {
		shipping = new Shipping();
		shipping.setId(1);
		shipping.setShippingName("Customer 1");
		shipping.setShippingAddress("Address 1");
		shipping.setShippingPhone("099");
		
		shippings = new ArrayList<Shipping>();
		
		for ( int i = 2 ; i <= 8 ; i++) {
			Shipping shipping1 = new Shipping();
			shipping1.setId(i);
			shipping1.setShippingName("Customer "+i);
			shipping1.setShippingAddress("Address "+i);
			shipping1.setShippingPhone("099"+i);
			shippings.add(shipping1);
		}
	}
	
	// JUnit test for getAllShipping method
    @DisplayName("JUnit test for getALlShipping method")
    @Test
    public void whenGetALlShippinggetALlShipping_thenReturnList(){
        // given - precondition or setup
    	
        given(shippingRepository.findAll()).willReturn(shippings);

        // when -  action or the behavior that we are going test
        List<Shipping> shippings1 = shippingService.getAllShipping();

        // then - verify the output
        assertThat(shippings1.size()).isEqualTo(shippings.size());
        assertThat(shippings1).isEqualTo(shippings);
    }
    
    // JUnit test for saveShipping method
    @DisplayName("JUnit test for saveShipping method")
    @Test
    public void givenShippingObject_whenSaveShipping_thenReturnShippingObject(){
        // given - precondition or setup
    	
        given(shippingRepository.save(shipping)).willReturn(shipping);

        // when -  action or the behavior that we are going test
        Shipping savedShipping = shippingService.saveShipping(shipping);

        // then - verify the output
        assertThat(savedShipping).isNotNull();
    }
    

    // JUnit test for getShipping by Id method
    @DisplayName("JUnit test for getShipping(int id) method")
    @Test
    public void givenShiipingId_whenGetShipping_thenReturnShippingObject(){
        // given
        given(shippingRepository.findById(1)).willReturn(Optional.of(shipping));

        // when
        Shipping savedShipping = shippingService.getShipping(1);
        
        // then
        assertThat(savedShipping).isNotNull();

    }

    // JUnit test for getShipping by Id method
    @DisplayName("JUnit test for getShipping(int id) method then throw exception")
    @Test
    public void givenShiipingId_whenGetShipping_thenThrowException(){
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		shippingService.getShipping(0);
        });

        String expectedMessage = "not find by id entity Shipping!!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    
 // JUnit test for updateShipping method
    @DisplayName("JUnit test for updateShipping method")
    @Test
    public void givenShippingObject_whenUpdateShipping_thenReturnUpdatedShipping(){
    	
    	// given
        given(shippingRepository.save(shipping)).willReturn(shipping);
        shipping.setShippingName("MP");
        shipping.setShippingAddress("Can Tho");
        
        // when
        Shipping updatedShipping = shippingService.updateShipping(shipping);

        // then
        assertThat(updatedShipping.getShippingName()).isEqualTo("MP");
        assertThat(updatedShipping.getShippingAddress()).isEqualTo("Can Tho");
    }

    // JUnit test for deleteOrder method
    @DisplayName("JUnit test for deleteShipping method")
    @Test
    public void givenShippingId_whenDeleteShipping_thenNothing(){
        // given - precondition or setup
    	Integer id1 = null;        
    	int id = 1;
    	
        willDoNothing().given(shippingRepository).deleteById(id);
        willDoNothing().given(shippingRepository).deleteById(id1);

        // when -  action or the behavior that we are going test
        shippingService.deleteShipping(id);
        shippingService.deleteShipping(id1);
        
        // then - verify the output
        verify(shippingRepository, times(1)).deleteById(id);
        verify(shippingRepository, times(1)).deleteById(id1);
    }

}
