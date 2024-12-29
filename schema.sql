CREATE DATABASE InventoryDB;
USE InventoryDB;
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,              
    category VARCHAR(50),                    
    quantity INT NOT NULL,                   
    price DECIMAL(10, 2) NOT NULL            
);
