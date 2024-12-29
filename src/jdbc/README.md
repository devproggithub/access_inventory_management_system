# Inventory Management System

## Introduction
Ce projet met en œuvre un système de gestion d'inventaire avec accès distant  qui permet aux utilisateurs de gérer les produits dans une base de données par le biais d’une architecture client-serveur.
Le système prend en charge les opérations CRUD (Create, Read, Update, Delete) pour la gestion des produits 
et utilise une base de données MySQL pour le stockage des données.

## Caractéristiques
- Ajouter un produit : Ajoutez de nouveaux produits à l’inventaire.
- Mise à jour du produit : Modifier les détails des produits existants.
- Supprimer le produit : Retirer les produits de l’inventaire.
- Recherche de produits par nom : Recherchez des produits en utilisant leur nom.
- Communication client-serveur : utilise des sockets pour la communication entre le client et le serveur.

## Structure de projet
```
access_inventory_management_system/
├── src/
│   ├── client/                 
│   │   ├── InventoryClient.java
│   ├── server/                
│   │   ├── ClientHandler.java
│   │   ├── Server.java
│   ├── dao/                   
│   │   ├── ProductDAO.java
│   ├── model/                 
│   │   ├── Product.java
│   ├── jdbc/                 
│   │   ├── DatabaseConnection.java
│   ├── README.md             
└── database/                   
    ├── schema.sql            
    ├── data.sql              
```

## Installation
1- Clone the repository:
```
git clone https://github.com/devproggithub/access_inventory_management_system.git
```
2- Mettre en place la base de données :
 - Créer une base de données MySQL nommée inventorydb.
 - Exécutez le script database/schema.sql pour créer le schéma de base de données.
 - Éventuellement, remplissez la base de données avec des données d’exemple en utilisant database/data.sql.
 - 
3- Update DatabaseConnection.java:
Modifier l’URL de la base de données, le nom d’utilisateur et le mot de passe pour correspondre à votre configuration MySQL.

## Technologies utilisées
- Java : langage de programmation.
- MySQL : Système de gestion des bases de données.
- Programmation des sockets : communication client-serveur.
- JDBC : Connectivité de base de données Java pour les opérations sur bases de données.







