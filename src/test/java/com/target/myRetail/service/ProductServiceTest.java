package com.target.myRetail.service;

import com.target.myRetail.exception.ProductNotFoundException;
import com.target.myRetail.model.Money;
import com.target.myRetail.model.ProductItem;
import com.target.myRetail.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDescriptionRepository productDescriptionRepository;

    @Autowired
    private ProductService productService;

    ProductItem productItem;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks( this );
        productService = new ProductService( productRepository );
        productItem = new ProductItem(15117729,new Money(13.49, "USD" ),"");
    }

    @Test
    public void shouldReturnProductDetailsById() throws ProductNotFoundException {

        when(productRepository.findById( 15117729 )).thenReturn( Optional.of( productItem ) );

        ProductItem expected = productItem;
        Optional<ProductItem> actual = productService.getProductById( 15117729 );
        assertEquals(actual.get(),expected);
        verify(productRepository).findById( 15117729 );
    }

    @Test
    public void shouldCreateProductItem(){
        when(productRepository.save( productItem )).thenReturn( productItem );
        ProductItem productItem1 = productRepository.save( productItem);
        assertEquals( productItem1.getId(), productItem.getId() );
    }

    @Test
    public void shouldUpdateProductItem(){
        when(productRepository.save( productItem )).thenReturn( productItem );
        ProductItem productItem1 = productRepository.save( productItem);
        assertEquals( productItem1.getId(), productItem.getId() );
    }


}
