import dao.ProductDAO;
import models.Product;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/heriniaina?serverTimezone=UTC";
        String user = "root";
        String password = "1811";
        Product product = new Product();
        product.setId(1);
        product.setName("AMD RYZEN 7 2400");

        ProductDAO productDAO = new ProductDAO(url,user,password);
        productDAO.saveProduct(product);
        System.out.println(productDAO.getProductById(1));
    }
}
