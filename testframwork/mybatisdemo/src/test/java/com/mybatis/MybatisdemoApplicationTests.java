package com.mybatis;
import com.mybatisdemos.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
@ContextConfiguration
public class MybatisdemoApplicationTests {
//    @Autowired
//    private StudentDao studentDaos;

    @Test
   public void getStudentById() throws IOException {
        String resource = "mappers/myStudentMapper.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();

        Student s = sqlSession.selectOne("com.mybatisdemos.dao.studentinfodao.MyStudentMapper.selectStudentByid", 1);
        log.info("s:  ",s);
    }

}
