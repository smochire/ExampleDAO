package dao;

import models.Product;

import java.sql.*;

public class ProductDAO {
    private String url;
    private String username;
    private String password;

    public ProductDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void saveProduct(Product product)
    {
        try {
            Connection con = DriverManager.getConnection(url,username,password);
            if(product.getId()!=0){
                String sql ="UPDATE products SET name = ? WHERE id = ? ;";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1,product.getName());
                statement.setLong(2, product.getId());
                statement.execute();
            }else{
                String sql="INSERT INTO products (name) VALUES (?);";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1,product.getName());
                statement.execute();
            }
            System.out.println(product.getName()+" save into the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"\n");
        }
    }

    public Product getProductById(int id)
    {
        try {
            Connection con = DriverManager.getConnection(url,username,password);
            String sql ="SELECT * FROM products WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            Product product = new Product();
            while (resultSet.next())
            {
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
