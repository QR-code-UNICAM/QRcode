# QR Code Generation Platform

This project was developed as an assignment for the examination of **Software Project Management**, a course in the master's degree programme in *Computer Science at the University of Camerino.* 
Our group adopt and simulate the practices typical of a SCRUM team. And we apply the methodologies and the tools explained during lecture hours.
## Directory

- [1. Introduction](#1-introduction)
- [2. Function Description](#2-function-description)
- [3. Technologies](#3-Technologies)
- [4. Project Architecture](#4-project-architecture)
- [5. Installation and Configuration](#5-installation-and-configuration)
- [6. Author List](#6-author-list)
- [7. Contact Details](#7-contact-details)

## 1. Introduction
This project is a QR code generation platform designed to create QR codes efficiently and effectively. Users can enter text, contact information and other data, and the platform will generate corresponding QR codes. At the same time, it provides the function of generating special effect types of QR codes, as well as color customization options to meet various needs.

This project is useful for a variety of purposes, including marketing, advertising, ticketing, contactless payments, inventory management, and more. QR codes offer a convenient way to encode information that can be easily scanned by smartphones, making it an accessible and versatile tool for both businesses and individuals.

**Usage:** 

Getting started with the platform is easy:

 - Visit the platform website.
 
 - Log in to the platform using email verification.

 - Enter specific data.
 
 - Select the type of QR code you want.

 - Customize the QR code (color) if needed.

 - Generate QR code.
 
 - Download or share the QR code as needed.


**Support Channels:**
- **Customer Support:** Email support or help desk system for queries or issues.
- **Community Forum:** A place for users to interact, share tips, and seek advice.
- **Social Media:** Updates, announcements, and user support.

The project is maintained by a dedicated team of developers focused on adding new features, enhancing user experience, fixing bugs, and ensuring compatibility with emerging technologies.

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>


## 2. Function Description
The platform supports generating various types of QR codes:
- General QR codes
- Custom-color QR codes
- Special-shaped QR codes
- QR codes with logos
- Optional picture-filled QR codes
- GIF QR codes
- input custom text for QR code generation 

Users can  view historically generated QR codes in the platform website.

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>


## 3. Technologis
- **Java Version:** 8
- **Back-end IDE:** IntelliJ IDEA
- **Front-end:** Visual Studio Code
- **Database:** MySQL and Redis for data storage and caching
- **Testing:** Unit testing for individual modules; Selenium for integration and acceptance testing

#### 1. IntelliJ IDEA
<p align="center">
  <img src="https://img0.baidu.com/it/u=4104454680,875699758&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313"/>
</p>

IntelliJ IDEA is a Java integrated development environment developed by JetBrains and is known as one of the best Java development tools in the industry. It provides a series of powerful functions such as intelligent code assistant, automatic code prompting, refactoring, J2EE support, version control integration, JUnit and CVS integration, etc., to help developers improve coding efficiency and ensure code quality. In addition, it supports multiple languages, including HTML, CSS, PHP, MySQL, Python, etc., and provides flexible layout and navigation modes, making it easier for developers to manage and maintain code. In short, IntelliJ IDEA is a powerful, flexible and easy-to-use Java development tool that provides comprehensive support for Java developers.

#### 2. Visual Studio Code
<p align="center">
  <img src="https://img1.baidu.com/it/u=3004107155,648519586&fm=253&fmt=auto&app=138&f=PNG?w=499&h=245"/>
</p>

Visual Studio Code is a free, open source code editor developed by Microsoft that supports operating systems such as Windows, Linux, and macOS. It integrates many features that a modern editor should have, such as syntax highlighting, customizable hotkey bindings, code snippet collection, etc., while also providing a rich extension ecosystem. VS Code supports a variety of programming languages, including JavaScript, TypeScript, Node.js, and others such as C++, C#, Java, Python, PHP, Go, etc., and has built-in Git version control functions and development environment functions, such as code completion, Code refactoring, etc. In addition, VS Code is developed based on the Electron framework and uses the Blink layout engine to render the user interface, which is lightweight and high-performance. In summary, Visual Studio Code is a powerful, easy-to-use, and highly customizable code editor suitable for development projects and teams of all sizes. In this project, VS Code is used for front-end development.

#### 3. Vue.js
<p align="center">
  <img src="https://p6.itc.cn/images01/20210326/d48e7395c21a4f82ba0f77789b93d4f9.jpeg" alt="alt text" width="500"/>
</p>

Vue.js (often shortened to “Vue”) is a progressive framework for building user interfaces. It is designed to enable developers to easily build complex and responsive front-end applications by implementing data-driven view components using a concise API. The core library of Vue.js focuses on the view layer, making it easy to integrate with other libraries or existing projects.
The main features of Vue.js include: lightweight, data-driven, componentized, flexible command system, template syntax, build tools, and powerful ecosystem. Vue.js is suitable for projects of all sizes, from simple static pages to complex single-page applications (SPA), which can be built using Vue.js. It is popular among front-end developers due to its gentle learning curve and ease of use. Whether you are a beginner or an experienced developer, you can use Vue.js to build efficient and maintainable front-end applications.

#### 4. MySQL
<p align="center">
  <img src="https://user-images.githubusercontent.com/100694015/235360201-d7578a3c-bda6-463e-8e55-460291e13d7f.png" alt="alt text" width="500"/>
</p>

MySQL is a database management system that helps to manipulate database stored in different tables in the computer. It is a relational database management system (often shortened to RDBMS) that uses Structured Query Language (SQL) to interact with the database. MySQL is released under an open-source license. It is fast, reliable, scalable, and easy to use.

#### 5. Redis
<p align="center">
  <img src="https://img1.baidu.com/it/u=587791904,183664975&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=288" alt="alt text" width="500"/>
</p>

Redis is an open source Key-Value database written in ANSI C language. It complies with the BSD protocol, supports the network, is a memory-based and persistent log database, and provides APIs in multiple languages. It is often called a data structure server because values can be of various data types, such as strings, hashes, maps, sets, and sorted sets. )wait. All operations in Redis are atomic, which means that each operation either executes successfully or fails and is not executed at all. In addition, Redis also supports data persistence, which can save the data in the memory to the disk and load it again for use when restarting. At the same time, Redis also supports data backup, that is, data backup in master-slave mode. The advantages of Redis include fast access speed, support for rich data types, and atomic operations. However, it also has some disadvantages, such as poor persistence support. Therefore, Redis is generally not used as the main database storage for data, but is used in conjunction with traditional relational databases. Redis is a high-performance key-value database with rich data types and atomic operations, supports data persistence and backup, and is widely used in various Internet projects.

#### 6. Selenium
<p align="center">
  <img src="https://img0.baidu.com/it/u=1790034696,743055891&fm=253&fmt=auto&app=138&f=JPEG?w=458&h=238" alt="alt text" width="500"/>
</p>

Selenium is a tool for web application testing, originally developed for automated testing of websites. It runs directly in the browser, just like a real user. Selenium supports all mainstream browsers, such as IE, Mozilla Firefox, Safari, Google Chrome, Opera and Edge, etc. Selenium’s main functions include testing compatibility with browsers to ensure that applications run well on different browsers and operating systems. Additionally, it can test the functionality of the system by creating regression tests to verify software functionality and user requirements. Selenium also supports automatic recording of actions and automatic generation of test scripts in different languages, such as .Net, Java, Perl, etc. Overall, Selenium is a powerful web application testing tool that provides flexible and scalable automated testing solutions to help developers ensure the quality and performance of web applications.

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>


## 4. Project Architecture
The architecture of this project is based on the principle of front-end and back-end separation, which enhances the modularity and scalability of the application. This approach allows the development teams to focus on their respective areas of expertise: the front-end team works on the user interface and user experience in Vue.js, ensuring a responsive and interactive design, while the back-end team, using Java, focuses on the server-side logic, database management, and API development. This separation simplifies maintenance, improves the development efficiency, and makes the application more robust and easier to deploy and scale.

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>


## 5. Installation and Configuration
- **IntelliJ IDEA:** Download and install from the official website. Configure JDK and necessary plugins.
- **Visual Studio Code:** Download and install from the official website. Install necessary extensions for Vue.js development.
- **Vue.js:** Install using npm or yarn. Follow official documentation for setup.
- **MySQL:** Download and install MySQL server. Configure database and user permissions.
- **Redis:** Download and install Redis server. Configure for caching.
- **Selenium:** Install Selenium WebDriver for your preferred programming language. Set up drivers for browser automation.

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>


## 6. Author List
- Guo yushan
- Gu yuzhan
- MA deyuan

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

## 7. Contact Details
| Contact | Mail |
| --- | --- |
| Gu yuzhan | yuzhan.gu@studenti.unicam.it |
| Guo yushan| yushan.guo@studenti.unicam.it |
| Ma deyuan | deyuan.ma@studenti.unicam.it |

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

