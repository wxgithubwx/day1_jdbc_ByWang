package wx.Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import wx.Bean.Book;
import wx.dao.BookDaoImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestBookDao {
    SqlSessionFactoryBuilder sessionFactoryBuilder=null;
    InputStream is=null;
    SqlSessionFactory sessionFactory=null;
   // SqlSession sqlSession=null;
    String source="config/SqlMapConfig.xml";

    @Before
    public void createsqlSession() throws IOException {
        sessionFactoryBuilder = new SqlSessionFactoryBuilder();
        is = Resources.getResourceAsStream(source);
        sessionFactory = sessionFactoryBuilder.build(is);
    }

    /**
     * 通过id查询单个     */
    @Test
    public void TestfindBookById(){
        BookDaoImpl bookDao=new BookDaoImpl(sessionFactory);
        Book bookById = bookDao.findBookById(3);
        System.out.println(bookById.toString());
    }

    /**
     * 模糊查询
     */
    @Test
    public void TestfindBookByName(){
        BookDaoImpl bookDao=new BookDaoImpl(sessionFactory);
        List<Book> books = bookDao.findBookByName("鲁迅");
        for (Book b:
             books) {
            System.out.println(b.toString());
        }
    }
}
