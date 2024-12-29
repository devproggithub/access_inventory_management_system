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
