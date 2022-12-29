package com.training.fa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.training.fa.model.User;
import com.training.fa.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserServicelmpl userService;
	
	User user;
	
	List<User> users;
	
	Page<User> userPage ;
	
	@BeforeEach
	public void setup() {
		user = new User();
		user.setId(1L);
		user.setEmail("example@example");
		user.setPasswork("asdfgh");
		
		users = new ArrayList<User>();
		
		for (long i = 2; i <= 8; i++) {
			User user1 = new User();
			user1.setId(i);
			user1.setEmail("email"+i);
			user1.setPasswork("asdf"+i);
			users.add(user1);
		
		}
		userPage= new PageImpl<User>(users);
	}
	
	// JUnit test for findAll method
    @DisplayName("JUnit test for findAll method")
    @Test
    public void whenFindAll_thenReturnList(){
        // given - precondition or setup

		Optional<Integer> page = Optional.empty();
		Pageable pageable = PageRequest.of(page.orElse(0), 5);
    	
        given(userRepository.findAll(pageable)).willReturn(userPage);

        // when -  action or the behaviour that we are going test
        Page<User> userPage1 = userService.findAll(pageable);

        // then - verify the output
        assertThat(userPage1.getSize()).isEqualTo(userPage.getSize());
        assertThat(userPage1).isEqualTo(userPage1);
    }
    
    // JUnit test for getAllUsers method
    @DisplayName("JUnit test for getAllUsers method")
    @Test
    public void whenGetAllUsers_thenReturnList(){
        // given - precondition or setup

    	
        given(userRepository.findAll()).willReturn(users);

        // when -  action or the behaviour that we are going test
        List<User> users1 = userService.getAllUser();

        // then - verify the output
        assertThat(users1.size()).isEqualTo(users.size());
        assertThat(users1).isEqualTo(users);
    }
    
    // JUnit test for getUser(Long id) method
    @DisplayName("JUnit test for getUser(Long id) method")
    @Test
    public void givenUserId_whenGetUser_thenReturnUserObject(){
        // given - precondition or setup
    	
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        
        // when -  action or the behaviour that we are going test
        User savedUser = userService.getUser(user.getId());

        // then - verify the output
        
        assertThat(savedUser).isEqualTo(user);
    }
    
    // JUnit test for getUser(Long id) method then throw exception
    @DisplayName("JUnit test for getUser(Long id) method then throw exception")
    @Test
    public void givenUserId_whenGetUser_thenThrowException(){
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		userService.getUser(0L);
        });

        String expectedMessage = "not find by id entity user!!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        
    }
    
    // JUnit test for saveUser(User user) method
    @DisplayName("JUnit test for saveUser(User user) method")
    @Test
    public void givenUserObject_whenSaveUser_thenReturnUserObject(){
        // given - precondition or setup
    	
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        
        // when -  action or the behaviour that we are going test
        User savedUser = userService.getUser(user.getId());

        // then - verify the output
        assertThat(savedUser).isNotNull();
    }
    
 // JUnit test for deleteUser(Long id) method
    @DisplayName("JUnit test for deleteUser(User user) method")
    @Test
    public void givenUserId_whenDeleteUser_thenNothing(){
    	// given - precondition or setup
    	Long id1 = null;        
    	long id = 1;
    	
        willDoNothing().given(userRepository).deleteById(id);
        willDoNothing().given(userRepository).deleteById(id1);

        // when -  action or the behavior that we are going test
        userService.deleteById(id);
        userService.deleteById(id1);
        
        // then - verify the output
        verify(userRepository, times(1)).deleteById(id);
        verify(userRepository, times(1)).deleteById(id1);
    }
    
    
}
