package wx.Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import wx.Bean.Book;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    SqlSessionFactoryBuilder sessionFactoryBuilder=null;
    InputStream is=null;
    SqlSessionFactory sessionFactory=null;
    SqlSession sqlSession=null;
    String source="config/SqlMapConfig.xml";

    /**
     * 关闭sqlSession
     */
    public void closesqlSession(){
        if(sqlSession!=null){
            sqlSession.close();
        }
    }

    @Before
    public void createsqlSession() throws IOException {
        // 1. 创建 SqlSessionFactoryBuilder 对象，加载 SqlMapConfig.xml 配置文件
        sessionFactoryBuilder = new SqlSessionFactoryBuilder();
         is = Resources.getResourceAsStream(source);
        // 2. 创建 SqlSessionFactory 对象
         sessionFactory = sessionFactoryBuilder.build(is);
        // 3. 创建 SqlSession 对象
         sqlSession = sessionFactory.openSession();
        // 4. 执行 SqlSession 对象执行查询，获取结果 Grade
    }

    /******************按id查1条********************/
    @Test
    public void testQueryBookById() throws IOException, IOException {
        Book book = sqlSession.selectOne("findBookById", 3);
        System.out.println(book.toString());
        if(sqlSession!=null){
            sqlSession.close();
        }
    }

    /******************按name查多条********************/
    @Test
    public void testQueryBookByName() throws IOException, IOException {
        List<Book> books = sqlSession.selectList("findBookByName", "冰心");//选d
        for (Book b:books) {
            System.out.println(b.toString());
        }
        this.closesqlSession();
        /*if(sqlSession!=null){
            sqlSession.close();
        }*/
    }

    /******************插入数据********************/
    @Test
    public void insertBook(){
        Book book=new Book();
        book.setName("鲁迅全集");
        book.setAutho("鲁迅");
        book.setPrice(100);
        System.out.println(book.getId());
        sqlSession.insert("book.insertBook",book);
        //要想插入到数据库，要提交
        sqlSession.commit();
        System.out.println("插入成功");
        System.out.println(book.getId());
        this.closesqlSession();
       /* if(sqlSession!=null){
            sqlSession.close();
        }*/
    }
}
