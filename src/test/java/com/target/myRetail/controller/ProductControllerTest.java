package com.target.myRetail.controller;

import com.target.myRetail.model.Money;
import com.target.myRetail.model.ProductItem;
import com.target.myRetail.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductDescriptionService productDescriptionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnProductList() throws Exception {

        ProductItem productItem1 = new ProductItem(15117729,new Money(13.49, "USD" ),"");
        ProductItem productItem2 = new ProductItem(12667733,new Money(15.49, "EUR" ),"");
        List<ProductItem> expected = new ArrayList<>();
        expected.add( productItem1 );
        expected.add( productItem2 );

        when(productService.getProductList()).thenReturn( expected );

        mockMvc.perform( get( "/Products/list" )
                .contentType( MediaType.APPLICATION_JSON ).accept( MediaType.APPLICATION_JSON )
        ).andExpect( status().isOk() ).equals( expected );
        verify( productService ).getProductList();

    }


    @Test
    void shouldReturnProductById() throws Exception {

        ProductItem productItem1 = new ProductItem(15117729,new Money(13.49, "USD" ),"");

        when(productService.getProductById(15117729)).thenReturn( Optional.of( productItem1 ) );

        mockMvc.perform( get( "/Products/15117729" )
                .contentType( MediaType.APPLICATION_JSON ).accept( MediaType.APPLICATION_JSON )
        ).andExpect( status().isOk() ).equals( productItem1 );
        verify( productService ).getProductById( 15117729 );

    }




}
