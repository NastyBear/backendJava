package lesson5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ProductTest {

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
}
