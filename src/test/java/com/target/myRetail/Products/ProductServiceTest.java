package com.target.myRetail.Products;

import com.target.myRetail.Exceptions.DefaultException;
import com.target.myRetail.entity.CurrentPrice;
import com.target.myRetail.entity.ProductPrice;
import com.target.myRetail.entity.ProductsRepository;
import com.target.myRetail.model.Product;
import com.target.myRetail.model.ProductInfo;
import com.target.myRetail.model.Products;
import com.target.myRetail.model.ProductsPrice;
import com.target.myRetail.service.ProductInfoService;
import com.target.myRetail.service.ProductService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Sridhar K - 04/21/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @MockBean
    private ProductsRepository productsRepository;
    @MockBean
    private ProductInfoService productInfoService;
    @Autowired
    private ProductService productService;

    @Test(expected = DefaultException.class)
    public void findProductById_NullDaoTest() throws DefaultException {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProduct(new Product());

        when(productInfoService.findProductInfoById(anyInt())).thenReturn("Some name");
        when(productsRepository.findByProductId(anyInt())).thenReturn(null);
        productService.findProductById(1234);
    }

    @Test(expected = DefaultException.class)
    public void findProductById_ProductInfoThrowsExceptionTest() throws DefaultException {
        doThrow(new DefaultException(HttpStatus.NOT_FOUND, "some message")).when(productInfoService).findProductInfoById(anyInt());
        productService.findProductById(1234);
    }

    @Test
    public void findProductById_HappyPathTest() throws DefaultException {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProduct(new Product());

        ProductPrice products = new ProductPrice();
        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setValue(12d);
        currentPrice.setCurrency_code("USD");
        products.setCurrentPrice(currentPrice);
        products.setProductId(1234);
        when(productInfoService.findProductInfoById(anyInt())).thenReturn("Some name");
        when(productsRepository.findByProductId(anyInt())).thenReturn(products);
        Products product = productService.findProductById(1234);
        assertThat(product.getName().equalsIgnoreCase("Some name"));
        assertThat(product.getProductId() == 1234);
        assertThat(product.getCurrentPrice().getValue().equals(12d));
        assertThat(product.getCurrentPrice().getCurrency_code().equalsIgnoreCase("USD"));
    }

    @Test
    public void updateData_HappyPathTest() throws DefaultException {
        ProductPrice products = new ProductPrice();
        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setValue(12d);
        currentPrice.setCurrency_code("USD");
        products.setCurrentPrice(currentPrice);
        products.setProductId(1234);
        when(productsRepository.findByProductId(anyInt())).thenReturn(products);
        ProductPrice products1 = new ProductPrice();
        CurrentPrice currentPrice1 = new CurrentPrice();
        currentPrice1.setValue(400d);
        currentPrice1.setCurrency_code("Rs");
        products1.setCurrentPrice(currentPrice1);
        products1.setProductId(1234);
        when(productsRepository.save(products)).thenReturn(products1); 
        Products newProduct = new Products();
        newProduct.setProductId(1234);
        ProductsPrice productsPrice = new ProductsPrice();
        productsPrice.setValue(400d);
        productsPrice.setCurrency_code("Rs");
        newProduct.setCurrentPrice(productsPrice);
        productService.updateProduct(newProduct,1234);
    }
}
