package wx.mapper;

import wx.Bean.Book;

import java.util.List;

public interface BookMapper {
    public Book findBookById(int id);
    public List<Book> findBooksByName(String name);
    public int insertBook(Book book);
    public int insertBook2(Book book);
    public int deleteBook(int id);
    public int updateBook(Book book);
}
