-- Création de la base de données
CREATE DATABASE Pharmacie_Management;

-- Utilisation de la base de données
USE Pharmacie_Management;

-- Table "Utilisateurs"
CREATE TABLE Utilisateurs (
  id_utilisateur INT PRIMARY KEY AUTO_INCREMENT,
  nom_utilisateur VARCHAR(100) NOT NULL,
  mot_de_passe VARCHAR(100) NOT NULL,
  type_utilisateur ENUM('administrateur', 'pharmacien') NOT NULL
);

-- Table "Clients"
CREATE TABLE Clients (
  id_client INT PRIMARY KEY AUTO_INCREMENT,
  nom_client VARCHAR(100) NOT NULL,
  adresse_client VARCHAR(100) NOT NULL,
  telephone_client VARCHAR(20) NOT NULL,
  credit_client DECIMAL(10, 2) DEFAULT 0.0
);

-- Table "Medicaments"
CREATE TABLE Medicaments (
  id_medicament INT PRIMARY KEY AUTO_INCREMENT,
  nom_medicament VARCHAR(100) NOT NULL,
  description_medicament VARCHAR(255),
  stock_medicament INT NOT NULL
);

-- Table "Ordonnances"
CREATE TABLE Ordonnances (
  id_ordonnance INT PRIMARY KEY AUTO_INCREMENT,
  id_client INT NOT NULL,
  id_medicament INT NOT NULL,
  date_ordonnance DATE NOT NULL,
  quantite INT NOT NULL,
  FOREIGN KEY (id_client) REFERENCES Clients(id_client),
  FOREIGN KEY (id_medicament) REFERENCES Medicaments(id_medicament));
