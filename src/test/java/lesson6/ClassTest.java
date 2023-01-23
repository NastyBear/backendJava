package lesson6;

import com.github.javafaker.Faker;
import lesson5.CategoryService;
import lesson5.Product;
import lesson5.ProductService;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ClassTest {

    public static ProductService productService;

    Product product;
    Product product2;
    String result;
    int id;
    static String resource;
    SqlSession session = null;

    public static void main( String[] args ) throws IOException {
        SqlSession session = null;
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new
                    SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();
            db.dao.CategoriesMapper categoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
            db.model.CategoriesExample example = new db.model.CategoriesExample();

            example.createCriteria().andIdEqualTo(1L);
            List<db.model.Categories> list = categoriesMapper.selectByExample(example);
            System.out.println(categoriesMapper.countByExample(example));

            db.model.Categories categories = new db.model.Categories();
            categories.setTitle("apple");
            categoriesMapper.insert(categories);
            session.commit();

            db.model.CategoriesExample example2 = new db.model.CategoriesExample();
            example2.createCriteria().andTitleLike("%apple%");
            List<db.model.Categories> list2 = categoriesMapper.selectByExample(example2);
            db.model.Categories categories2 = list2.get(0);
            categories2.setTitle("banana");
            categoriesMapper.updateByPrimaryKey(categories2);
            session.commit();

            categoriesMapper.deleteByPrimaryKey(categories2.getId());
            session.commit();

        } finally {
            session.close();
        }


    }
    static db.dao.CategoriesMapper categoriesMapper;
    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() throws IOException {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @Test

    void getProductTest () throws IOException {
        db.model.Categories categories = new db.model.Categories();
        categories.setTitle("apple");
        categoriesMapper.insert(categories);
        session.commit();
    }

    @Test
    void createProductTest() throws IOException {

        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(),CoreMatchers.is(true));
        db.model.CategoriesExample example2 = new db.model.CategoriesExample();
        example2.createCriteria().andTitleLike("%apple%");
        List<db.model.Categories> list2 = categoriesMapper.selectByExample(example2);

    }
    //    @Test
//     void modifyProductTest () throws IOException {
//        Response<Product>response = productService.modifyProduct(result).execute();
//        assertThat(response.body().getTitle(), equalTo("Cheese"));
//    }

    @SneakyThrows
        //@AfterEach
        //@Test
    void tearDown(){
        Response<ResponseBody>response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(),CoreMatchers.is(true));
    }


}
