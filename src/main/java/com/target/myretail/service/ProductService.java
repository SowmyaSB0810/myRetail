package com.target.myretail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.model.Money;
import com.target.myretail.model.ProductItem;
import com.target.myretail.repository.ProductRepository;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductItem> getProductList() throws IOException {
        getProductDesc( 1234 );
        return productRepository.findAll();
    }

    public void createProduct(int id, Money money){
        ProductItem item = new ProductItem(id, money,"");
        productRepository.save( item );
    }

    public void updateProduct(int id, Money money) throws ProductNotFoundException {
        Optional<ProductItem> productItem = getProductById( id );
        productRepository.save( new ProductItem(id, money,"") );

    }

    public Optional<ProductItem> getProductById(int id) throws ProductNotFoundException {
        Optional<ProductItem> productItem = productRepository.findById( id );
        if(productItem.isPresent()){
            productItem.get().setProduct_desc( getProductDesc( id ) );
            return productItem;
        }else{
            throw new ProductNotFoundException( "The product with the provided id is not present");
        }
    }

    public void deleteProductById(int id) throws ProductNotFoundException {
        getProductById( id );
        productRepository.deleteById( id );
    }

    public String getProductDesc(int id){
        String URL = getURL();
        String desc = "";
        if(!URL.isEmpty()){
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Map> map = null;
            try {
                map = mapper.readValue(response.getBody(), Map.class);
                desc = String.valueOf( map.get("product_desc"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return desc;

    }

    private String getURL(){
        Properties props = new Properties();
        ClassLoader loader = PropertiesUtil.class.getClassLoader();
        String applicationProperties = Objects.requireNonNull(loader.getResource(
                "application.properties")).getPath();
        String URL="";
        try {
            props.load(new FileInputStream(applicationProperties));
            URL = props.getProperty("product_desc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return URL;
    }

}
