package wx.dao;

import wx.Bean.Book;

import java.util.List;

public interface BookDao {
    public Book findBookById(Integer id);
    public List<Book> findBookByName(String name);
}
