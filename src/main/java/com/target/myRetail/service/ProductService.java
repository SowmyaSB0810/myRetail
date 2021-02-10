package com.target.myRetail.service;

import com.target.myRetail.exception.ProductNotFoundException;
import com.target.myRetail.model.Money;
import com.target.myRetail.model.ProductItem;
import com.target.myRetail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductItem> getProductList(){
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
//            ProductDescriptionService productDescriptionService = new ProductDescriptionService(  );
//            String productDescription = productDescriptionService.getProductDescriptionById( id );
//            productItem.get().setProduct_desc( productDescription );
            return productItem;
        }else{
            throw new ProductNotFoundException( "The product with the provided id is not present");
        }

    }

    public void deleteProductById(int id) throws ProductNotFoundException {
        getProductById( id );
        productRepository.deleteById( id );
    }

}
