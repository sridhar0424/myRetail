package com.target.myRetail.ProductInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.target.myRetail.Exceptions.DefaultException;
import com.target.myRetail.client.ProductInfoClient;
import com.target.myRetail.model.Item;
import com.target.myRetail.model.Product;
import com.target.myRetail.model.ProductInfo;
import com.target.myRetail.model.Product_description;
import com.target.myRetail.service.ProductInfoService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Sridhar K - 04/23/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {
    @MockBean
    private ProductInfoClient productInfoClient;
    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findProductInfoById_NullpointerTest() {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProduct(new Product());
        when(productInfoClient.getProductInfo(anyInt())).thenReturn(productInfo);

        try {
            productInfoService.findProductInfoById(1234);
        } catch (DefaultException e) {
            assertThat(e.getDisplayMessage().equals("Error at ProductInfoService->findProductInfoById->RestClientException|Nullpointer"));

        }
    }

    @Test
    public void findProductInfoById_HttpClientErrorExceptionTest() {
        doThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND)).when(productInfoClient).getProductInfo(anyInt());
        try {
            productInfoService.findProductInfoById(1234);
        } catch (DefaultException e) {
            assertThat(e.getDisplayMessage().equals("404 NOT_FOUND"));

        }
    }

    @Test
    public void findProductInfoById_RestClientExceptionTest() {
        doThrow(new RestClientException("Some message")).when(productInfoClient).getProductInfo(anyInt());
        try {
            productInfoService.findProductInfoById(1234);
        } catch (DefaultException e) {
            assertThat(e.getDisplayMessage().equals("Some message"));

        }
    }

    @Test
    public void findProductInfoById_HappyPathTest() throws DefaultException {

        ProductInfo productInfo = new ProductInfo();
        Product product = new Product();
        Item item = new Item();
        Product_description product_description = new Product_description();
        product_description.setTitle("some title");
        item.setProduct_description(product_description);
        product.setItem(item);
        productInfo.setProduct(product);
        when(productInfoClient.getProductInfo(anyInt())).thenReturn(productInfo);

        assertThat(productInfoService.findProductInfoById(1234).equals("some title"));

    }
}
