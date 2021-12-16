package ru.leonchemic.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.leonchemic.dto.Category;
import ru.leonchemic.enums.CategoryType;
import ru.leonchemic.service.CategoryService;
import ru.leonchemic.utils.RetrofitUtils;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CategoryTest {
    static Retrofit client;
    static CategoryService categoryService;

    @BeforeEach
    void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        categoryService = client.create(CategoryService.class);
    }

    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.ELECTRONIC.getId();
        Response<Category> response = categoryService.getCategory(id).execute();
        assertThat(response.body().getId(), equalTo(id));
        assertThat(response.body().getTitle(), equalTo(CategoryType.ELECTRONIC.getTitle()));
    }
}
