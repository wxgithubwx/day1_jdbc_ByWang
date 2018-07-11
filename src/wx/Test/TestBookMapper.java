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

public class TestBookMapper {
    private int id=3;//查询的id
    private int deleteId=16;//删除id
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    private InputStream inputStream;
    private  String resource="config/SqlMapConfig.xml";
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    public void closeSqlSession(){
        if(sqlSession!=null){
            sqlSession.close();
        }
    }

    @Before
    public void createSqlSession() throws IOException {
        sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        inputStream= Resources.getResourceAsStream(resource);
        sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
    }


    /**
     * 根据id查询单个
     */
    @Test
    public void findBookById(){
         sqlSession = sqlSessionFactory.openSession();
        Book book = sqlSession.selectOne("findBookById", id);
        System.out.println(book.toString());
        this.closeSqlSession();
    }

    /**
     * 根据名称迷糊查询
     */
    @Test
    public void findBooksByName(){
        sqlSession=sqlSessionFactory.openSession();
        List<Book> books = sqlSession.selectList("findBooksByName", "鲁迅");
        for (Book b:
             books) {
            System.out.println(b.toString());
        }
        this.closeSqlSession();
    }

    /**
     * 添加用户
     */
    @Test
    public void insertBook(){
        sqlSession=sqlSessionFactory.openSession();
        Book book=new Book();
        book.setName("爱的教育");
        book.setAutho("简爱");
        book.setPrice(13);
        System.out.println("插入前id是："+book.getId());
        int i = sqlSession.insert("insertBook", book);
        sqlSession.commit();
        System.out.println("插入后id是："+book.getId());
        System.out.println(i);
        if(i>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        this.closeSqlSession();
    }

    /**
     * 自增方式2
     */
    @Test
    public void insertBook2(){
        sqlSession=sqlSessionFactory.openSession();
        Book book=new Book();
        book.setName("三只松鼠");
        book.setAutho("童话故事王");
        book.setPrice(99);
        System.out.println("插入前id是："+book.getId());
        int i = sqlSession.insert("insertBook2", book);
        sqlSession.commit();
        System.out.println("插入后id是："+book.getId());
        System.out.println(i);
        if(i>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        this.closeSqlSession();
    }


    /**
     * 删除
     */
    @Test
    public void deleteBook(){
        sqlSession=sqlSessionFactory.openSession();
        int i = sqlSession.delete("deleteBook", deleteId);
        sqlSession.commit();
        System.out.println(i);
        if(i>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        this.closeSqlSession();
    }

    /**
     * 修改
     */
    @Test
    public void updateBook(){
        sqlSession=sqlSessionFactory.openSession();
        Book book=new Book();
        book.setName("假如给我三天光明");
        book.setAutho("xx");
        book.setPrice(66);
        book.setId(17);
        int updateBook = sqlSession.update("updateBook", book);
        sqlSession.commit();
        if(updateBook>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
        this.closeSqlSession();
    }

}
