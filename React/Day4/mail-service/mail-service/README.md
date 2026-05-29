# Mail Service — Spring Boot

A Spring Boot REST API for sending emails via Gmail SMTP.

---

## Project Structure

```
mail-service/
├── src/main/java/com/company/
│   ├── MailServiceApplication.java       ← Main entry point
│   ├── bean/
│   │   └── MailDetail.java               ← Request body model
│   ├── controller/
│   │   └── MailController.java           ← REST endpoints
│   └── service/
│       ├── MailService.java              ← Interface
│       └── MailServiceImpl.java          ← Implementation
├── src/main/resources/
│   └── application.properties           ← Gmail SMTP config
└── pom.xml
```

---

## Setup

### 1. Generate Gmail App Password

Your regular Gmail password will NOT work. You need an App Password:

1. Go to https://myaccount.google.com/security
2. Enable **2-Step Verification**
3. Go to **Security → 2-Step Verification → App passwords**
4. Select App: **Mail**, Device: **Other** → click **Generate**
5. Copy the 16-character password

### 2. Update application.properties

```properties
spring.mail.username=ankush8114@gmail.com
spring.mail.password=YOUR_16_CHAR_APP_PASSWORD
```

### 3. Run the application

```bash
mvn spring-boot:run
```

---

## API Endpoints

### Send Plain Email

**POST** `http://localhost:8080/api/send-mail`

```json
{
  "recipient": "someone@example.com",
  "subject": "Hello",
  "msgBody": "This is a test email from Ankush!"
}
```

---

### Send Email with Attachment

**POST** `http://localhost:8080/api/send-mail-attachment`

```json
{
  "recipient": "someone@example.com",
  "subject": "File Attached",
  "msgBody": "Please find the attached file.",
  "attachment": "C:/Users/Ankush/Documents/report.pdf"
}
```

> Note: `attachment` must be an **absolute file path** on the machine running the server.

---

## Tech Stack

- Java 17
- Spring Boot 3.2
- Spring Boot Starter Mail
- Jakarta Mail
