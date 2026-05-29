package com.wipro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.wipro.model.Product;
import com.wipro.util.DBConnection;

public class ProductDAO {

    // 1. ADD PRODUCT
    public void addProduct(Product p)
    {
        String sql = "insert into products(name ,price) values(? ,?)"; 

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());

            ps.executeUpdate();
            System.out.println("Data Added successfully");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // 2. VIEW ALL PRODUCTS
    public void getAllProducts()
    {
        String sql = "select * from products";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getDouble("price")
                );
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // 3. UPDATE PRODUCT
    public void updateProduct(Product p)
    {
        String sql = "update products set name = ?, price = ? where id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getId());

            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("Product updated successfully");
            else
                System.out.println("Product not found");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // 4. DELETE PRODUCT
    public void deleteProduct(int id)
    {
        String sql = "delete from products where id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("Product deleted successfully");
            else
                System.out.println("Product not found");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}