package com.wipro.main;

import java.util.Scanner;

import com.wipro.dao.ProductDAO;
import com.wipro.model.Product;

public class MainApp {
    public static void main(String[] args)
    {
        ProductDAO dao = new ProductDAO();

        try (Scanner sc = new Scanner(System.in)) {
            while(true)
            {
                System.out.println("\n1. Add product");
                System.out.println("2. View product");
                System.out.println("3. Update product");
                System.out.println("4. Delete product");
                System.out.println("5. Exit");

                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch(choice)
                {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.next();

                        System.out.print("Enter price: ");
                        double price = sc.nextDouble();

                        dao.addProduct(new Product(name, price));
                        break;

                    case 2:
                        dao.getAllProducts();
                        break;

                    case 3:
                        System.out.print("Enter product ID to update: ");
                        int id = sc.nextInt();

                        System.out.print("Enter new name: ");
                        String newName = sc.next();

                        System.out.print("Enter new price: ");
                        double newPrice = sc.nextDouble();

                        dao.updateProduct(new Product(id, newName, newPrice));
                        break;

                    case 4:
                        System.out.print("Enter product ID to delete: ");
                        int deleteId = sc.nextInt();

                        dao.deleteProduct(deleteId);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }
    }
}