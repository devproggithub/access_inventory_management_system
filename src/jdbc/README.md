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
│   ├── client/                 # Client-side code
│   │   ├── InventoryClient.java
│   ├── server/                 # Server-side code
│   │   ├── ClientHandler.java
│   │   ├── Server.java
│   ├── dao/                    # Data Access Object (DAO) layer
│   │   ├── ProductDAO.java
│   ├── model/                  # Model classes
│   │   ├── Product.java
│   ├── jdbc/                   # Database connection utilities
│   │   ├── DatabaseConnection.java
│   ├── README.md               # Project documentation
└── database/                   # Database scripts
    ├── schema.sql              # Database schema definition
    ├── data.sql                # Sample data for testing
```
