package com.training.fa.service;

import static org.assertj.core.api.Assertions.assertThat;



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



import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



import com.training.fa.model.Category;
import com.training.fa.model.Product;
import com.training.fa.repository.ProductRepository;
import com.training.fa.service.ProductServicelmpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	@Mock
    ProductRepository productRepository;
    
    @InjectMocks
    ProductServicelmpl productServicelmpl;
    
    Product product;
    
    List<Product> products = new ArrayList<Product>();
    
    @BeforeEach
    public void setup() {
        product = new Product();
        product.setId(1);
        product.setName("product 1");
        product.setPrice(100);
        product.setCategory(new Category(1,"Category1"));
        product.setContent("content 1");
        product.setImagePath("image1.jpg");
        
        for (int i = 2; i <= 10; i++) {
            Product product1 = new Product();
            product1.setId(i);
            product1.setName("product name "+i);
            product1.setPrice(100+i);
            product1.setCategory(new Category(i,"Category"+i));
            product1.setContent("content "+i);
            product1.setImagePath("image"+i+".jpg");
            products.add(product1);
        }
    }



   // JUnit test for saveProduct method
    @DisplayName("JUnit test for saveProduct method")
    @Test
    public void givenProductObject_whenSaveProduct_thenReturnProductObject(){
        given(productRepository.save(product)).willReturn(product);
        
        Product saveProduct = productServicelmpl.saveProduct(product);
        
        assertThat(saveProduct).isNotNull();
        
        System.out.println("--Save product--");
        System.out.println(product);
    }
    
    //getProduct
    @DisplayName("JUnit test for getProduct(int id) method")
    @Test
    public void givenproduct_whenGetProductById_thenReturnProductObject(){
        given(productRepository.findById(1)).willReturn(Optional.of(product));
        
        Product getProduct = productServicelmpl.getProduct(product.getId());
        
        assertThat(getProduct).isNotNull();
    }
    
    //updateProduct
    @DisplayName("JUnit test for updateProduct method")
    @Test
    public void givenProductObject_whenUpdateProduct_thenReturnUpdatedProduct(){
        given(productRepository.save(product)).willReturn(product);
        product.setId(200);
        product.setName("product 200");
        product.setPrice(200);
        Category category = new Category(200,"Category200");
        product.setCategory(category);
        product.setContent("content 200");
        product.setImagePath("image200.jpg");
        Product productUpdate = productServicelmpl.updateProduct(product);
        
        assertThat(productUpdate.getName()).isEqualTo("product 200");
        assertThat(productUpdate.getPrice()).isEqualTo(200);
        assertThat(productUpdate.getCategory().toString()).isEqualTo(category.toString());
        assertThat(productUpdate.getContent()).isEqualTo("content 200");
        assertThat(productUpdate.getImagePath()).isEqualTo("image200.jpg");
        System.out.println("--Update product--");
        System.out.println(product);           
    }
    
    // JUnit test for product by Id method then throw Exception
    @DisplayName("JUnit test for getOrder(int id) method then throw exception")
    @Test
    public void givenProductId_whenGetProductById_thenThrowException(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productServicelmpl.getProduct(0);
        });



       String expectedMessage = "not find by id entity Product!!";
        String actualMessage = exception.getMessage();



       assertTrue(actualMessage.contains(expectedMessage));



   }
    
    //find all
    @DisplayName("JUnit test for geAlltProduct method")
    @Test
    public void givenAllProduct_whenGetAllProduct_thenReturnProductList(){
        given(productRepository.findAll()).willReturn(products);
        
        List<Product> list = productServicelmpl.getAllProduct();
        
        System.out.println("--All product--");
        for (Product product : list) {
            System.out.println(product);
        }
        assertThat(list.size()).isEqualTo(9);
        
    }
    
    //deleteProduct
    @DisplayName("JUnit test for deleteProduct method")
    @Test
    public void givenProductId_whenDeleteProduct_thenNothing(){
        willDoNothing().given(productRepository).deleteById(1);
        
        productServicelmpl.deleteProduct(1);
        verify(productRepository, times(1)).deleteById(1);
        
    }
}
