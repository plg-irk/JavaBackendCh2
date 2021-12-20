package ru.leonchemic;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ru.leonchemic.db.dao.CategoriesMapper;
import ru.leonchemic.db.dao.ProductsMapper;
import ru.leonchemic.db.model.Categories;
import ru.leonchemic.db.model.CategoriesExample;
import ru.leonchemic.db.model.ProductsExample;

import java.io.IOException;
@Slf4j
public class Main {
    static Faker faker = new Faker();
    private static String resource = "mybatisConfig.xml";

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();
        CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
        ProductsMapper productsMapper = sqlSession.getMapper(ProductsMapper.class);

        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
        log.info(String.valueOf(categoriesCount));

        long productsCount = productsMapper.countByExample(new ProductsExample());
        log.info(String.valueOf(productsCount));



//        createNewCategory(categoriesMapper);

    }

    private static void createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.animal().name());

        categoriesMapper.insert(newCategory);
    }

    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }
}
