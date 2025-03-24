# Patient Management - JEE Web Application (Spring MVC, Thymeleaf, Spring Data JPA)

## 📋 Description

This project is a **JEE Web Application** developed with **Spring MVC**, **Thymeleaf**, and **Spring Data JPA**.  
It allows you to **manage a list of patients** with the following features:

- ✅ Display the list of patients
- ✅ Patients pagination
- ✅ Search patients by keyword
- ✅ Delete a patient
- ✅ Additional improvements (UI, validation, etc.)

---

## 🚀 Technologies Used

- ☕ **Java 17+**
- 🌱 **Spring Boot**
- 🌐 **Spring MVC**
- 🗃️ **Spring Data JPA**
- 📝 **Thymeleaf**
- 🔗 **Hibernate**
- 💾 **H2 Database** (embedded for development)
- 🎨 **Bootstrap** (for responsive design)

---

## 🔐 Security Implementation

The application includes secure authentication with:

- **User roles**: ADMIN and USER
- **Protected routes**:
  - `/admin/**` → Accessible only to ADMIN
  - `/patients/**` → Accessible to authenticated users
- **Password encryption** using BCrypt
- **CSRF protection** enabled
- **Secure session management**

### Login Credentials (for testing):
- **Admin**: admin / admin123
- **User**: user / user123

---

## Features

### Part 1: Patient Management

1. **Display Patients**: The list of patients is displayed with information such as name, age, and other relevant details.
2. **Pagination**: Results are paginated for better data management.
3. **Search Patients**: A search field allows filtering patients by name or other criteria.
4. **Delete a Patient**: Users can delete a patient from the list.
5. **Additional Improvements**: Added functionalities such as updating patient information.

### Part 2: Security & Templates

1. **Spring Security Integration**:
   - Secure authentication
   - Role-based authorization
   - Custom login/logout pages

2. **Thymeleaf Templates**:
   - Reusable page layouts
   - Security-aware fragments (show/hide elements based on roles)
   - CSRF token integration

3. **Form Validation**:
   - Client-side validation with Thymeleaf
   - Server-side validation with Spring Validator
   - Custom error messages

---

## 📸 Screenshots

![image](https://github.com/user-attachments/assets/c8e37082-0135-4a1f-a0ee-5d6d41603f59)

![image](https://github.com/user-attachments/assets/1127aef4-e55c-4335-b4ef-3acc4b2284d7)
![image](https://github.com/user-attachments/assets/5e5e8185-b321-439f-a6d3-355b4bd8cc43)
![image](https://github.com/user-attachments/assets/d5dc6a39-f0da-4d26-9f58-d1f0b56c35cf)
![image](https://github.com/user-attachments/assets/50107f0c-c565-446d-a741-2e1e7fc5b91d)




