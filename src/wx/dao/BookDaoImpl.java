package wx.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import wx.Bean.Book;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private SqlSessionFactory sqlSessionFactory;

    public BookDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Book findBookById(Integer id) {
        //sqlSession 是线程不安全的 , 所以它的最佳使用范围在方法体内
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Book book = sqlSession.selectOne("findBookById", id);
        if(sqlSession!=null){
            sqlSession.close();
        }
        return book;
    }

    @Override
    public List<Book> findBookByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Book> books = sqlSession.selectList("findBookByName", name);
        if(sqlSession!=null){
            sqlSession.close();
        }
        return books;
    }
}
