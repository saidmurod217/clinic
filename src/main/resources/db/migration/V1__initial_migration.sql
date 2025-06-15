-- Drop all tables if they exist (to avoid conflicts during development)
DROP TABLE IF EXISTS doctor_working_hours, working_hours, room_images, clinic_rooms, rooms,
    doctor_reviews, clinic_reviews, reviews, doctor_clinic, clinic_services, services,
    doctors, clinics, user_profiles, images, users;

-- USERS table - Single point of login for all roles
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    role ENUM('ADMIN', 'DOCTOR', 'CLINIC', 'USER') NOT NULL
);

-- PATIENT / REGULAR USER profile
CREATE TABLE user_profiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100),
    surname VARCHAR(100),
    age INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- CLINIC profile
CREATE TABLE clinics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(255),
    about TEXT,
    certification VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(20),
    weblink VARCHAR(255),
    since TIMESTAMP,
    rating FLOAT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- DOCTOR profile
CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100),
    surname VARCHAR(100),
    bio TEXT,
    certificates TEXT,
    rating FLOAT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- SERVICES offered
CREATE TABLE services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- SERVICES at CLINICS
CREATE TABLE clinic_services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_id INT NOT NULL,
    clinic_id INT NOT NULL,
    cost FLOAT,
    info TEXT,
    FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
    FOREIGN KEY (clinic_id) REFERENCES clinics(id) ON DELETE CASCADE
);

-- CLINIC ↔️ DOCTOR (many-to-many)
CREATE TABLE doctor_clinic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    clinic_id INT NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
    FOREIGN KEY (clinic_id) REFERENCES clinics(id) ON DELETE CASCADE
);

-- REVIEWS table (shared)
CREATE TABLE reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    content TEXT,
    star_count FLOAT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- REVIEWS to CLINIC
CREATE TABLE clinic_reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    review_id INT NOT NULL,
    clinic_id INT NOT NULL,
    FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE,
    FOREIGN KEY (clinic_id) REFERENCES clinics(id) ON DELETE CASCADE
);

-- REVIEWS to DOCTOR
CREATE TABLE doctor_reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    review_id INT NOT NULL,
    doctor_id INT NOT NULL,
    FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

-- ROOM types (e.g. operation, therapy, etc.)
CREATE TABLE rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    price FLOAT,
    title VARCHAR(100)
);

-- CLINIC ↔️ ROOM (many-to-many)
CREATE TABLE clinic_rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clinic_id INT NOT NULL,
    room_id INT NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES clinics(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

-- IMAGES
CREATE TABLE images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    img_url VARCHAR(255) NOT NULL
);

-- ROOM ↔️ IMAGES (many-to-many)
CREATE TABLE room_images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    room_id INT NOT NULL,
    image_id INT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE,
    FOREIGN KEY (image_id) REFERENCES images(id) ON DELETE CASCADE
);

-- WORKING HOURS (clinic level)
CREATE TABLE working_hours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_time TIMESTAMP NOT NULL,
    to_time TIMESTAMP NOT NULL,
    clinic_id INT NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES clinics(id) ON DELETE CASCADE
);


CREATE TABLE doctor_working_hours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    working_hours_id INT NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
    FOREIGN KEY (working_hours_id) REFERENCES working_hours(id) ON DELETE CASCADE
);
