package wx.Bean;

public class Book {
    int id;
    String name;
    String autho;
    double price;

    public Book() {
    }

    public Book(int id, String name, String autho, double price) {
        this.id = id;
        this.name = name;
        this.autho = autho;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutho() {
        return autho;
    }

    public void setAutho(String autho) {
        this.autho = autho;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", autho='" + autho + '\'' +
                ", price=" + price +
                '}';
    }
}
