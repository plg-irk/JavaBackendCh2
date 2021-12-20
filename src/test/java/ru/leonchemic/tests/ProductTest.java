package ru.leonchemic.tests;

import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.leonchemic.dto.Product;
import ru.leonchemic.enums.CategoryType;
import ru.leonchemic.service.ProductService;
import ru.leonchemic.utils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;
import ru.leonchemic.utils.DbUtils;
import ru.leonchemic.db.dao.ProductsMapper;


public class ProductTest {
    static Retrofit client;
    static ProductService productService;
    static Faker faker = new Faker();
    Product product;
    static Integer productID;
    static ProductsMapper productsMapper;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        productsMapper = DbUtils.getProductsMapper();
    }

    @BeforeEach
    void setUp() throws IOException {
        product = new Product()
                .withTitle(faker.food().dish())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());

        Integer countProductsBefore = DbUtils.countProducts(productsMapper);
        Response<Product> response = productService.createProduct(product).execute();
        Integer countProductsAfter = DbUtils.countProducts(productsMapper);
        assertThat(countProductsAfter, equalTo(countProductsBefore+1));


        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));

        productID = response.body().getId();
    }

    @Test
    void getProductTest() throws IOException {
        Response<Product> response = productService.getProduct(productID).execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    @Test
    void putProductTest() throws IOException {
        product = new Product()
                .withId(productID)
                .withTitle(product.getTitle())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(product.getCategoryTitle());

        Response<Product> response = productService.putProduct(product).execute();
        assertThat(response.code(), equalTo(200));
    }

    @AfterEach
    void teardown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(productID).execute();
        assertThat(response.code(), equalTo(200));
    }


}
