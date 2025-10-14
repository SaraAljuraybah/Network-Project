# 🧠 Online Reservation System  
### IT328 – Computer Networks Project | King Saud University  

---

## 🎯 Project Overview

The **Online Reservation System** is a Java-based networking project that allows multiple clients to register, log in, and make real-time reservations through a **multi-threaded server**.  

It simulates how modern booking systems (such as hotel, event, or facility reservations) work over a TCP network connection, emphasizing **socket programming**, **client–server communication**, and **GUI development** using **Java Swing**.

This project was developed as part of the **IT328 Computer Networks** course at **King Saud University**.

---

## 🚀 Main Objectives

1. **Apply networking fundamentals** through practical client–server socket programming.  
2. **Build a multi-user system** where multiple clients can connect simultaneously.  
3. **Design an interactive user interface (GUI)** using Java Swing.  
4. **Implement core reservation functionalities:**
   - Register a new account  
   - Log in with existing credentials  
   - Make and confirm reservations  
   - View and cancel reservations  
   - Real-time update of availability for all connected users  

---

## 🧩 System Architecture

```
src/
 ├── GUI/         → User interfaces (Login, Registration, Main Menu)
 ├── client/      → Client-side socket handling
 └── server/      → Server logic and reservation data
```

**Communication Type:** TCP Sockets  
**Server Model:** Multi-threaded concurrent server  
**Data Handling:** Real-time updates shared with all connected clients  

---

## 💻 Technologies Used

| Category | Technology |
|-----------|-------------|
|Programming|             |
| Language  |    Java     |
| IDE       |   NetBeans  |
| GUI Framework | Java Swing |
| Network API | java.net.Socket, java.net.ServerSocket |
| Architecture | Client–Server Model |
| Version Control | Git + GitHub |

---

## 🧠 Key Features

- Secure registration and login system.  
- Real-time synchronization between multiple clients.  
- Clean and modular GUI design (MVC-inspired).  
- Automatic update of available time slots.  
- Reservation cancellation and dynamic reallocation.  
- Local testing supported via `localhost` or LAN.  

---

## ⚙️ How to Run the Project

1. **Open the project** in NetBeans IDE.  
2. **Run the `Server.java`** file first to start the server.  
3. **Run the `Client.java`** file (or the GUI main form) for each user.  
4. Make sure all clients connect using the **same IP and port number**.  
5. Interact through the GUI:
   - Register → Login → Reserve → Cancel  

---

## 🧑‍💻 Team Members

| Name | Role |
|------|------|
| **Sara Aljuraybah** | Lead Developer, GUI Designer |
| **Dana Alosaimy** | Client Logic Implementation |
| **Haya Alomar** | Server & Thread Management |
| **Gharsah Almosaad** | GUI & Testing |

---

## 🏫 Course Information

| Item | Details |
|------|----------|
| **Course** | IT328 – Computer Networks |
| **Instructor** | *(Add instructor’s name if required)* |
| **University** | King Saud University |
| **Semester** | 1447H – Fall 2025 |

---

## 🌱 Future Enhancements

- Add database integration (MySQL) for persistent data storage.  
- Include email confirmation system.  
- Implement admin dashboard for managing all reservations.  
- Expand system to web or mobile versions using REST APIs.

---

## 🏆 Summary

The project demonstrates how a **distributed multi-client system** operates over a network using Java Sockets.  
It combines networking theory with real-world programming practice, preparing students to build scalable and interactive systems.  

> 💬 *“Built with passion for innovation and teamwork by IT students at King Saud University.”*
