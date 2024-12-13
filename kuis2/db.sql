CREATE DATABASE IF NOT EXISTS Quiz2_PBO_reg_2024_1123016;
USE Quiz2_PBO_reg_2024_1123016;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(88) NOT NULL,
    email VARCHAR(88) NOT NULL UNIQUE,
    password VARCHAR(88) NOT NULL
);

CREATE TABLE Artworks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(88) NOT NULL,
    description TEXT,
    image_path VARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

INSERT INTO users (name, email, password) VALUES
('mario', 'mario@', 'mar88');