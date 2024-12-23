You can use standard Markdown styling for README.md:

# Video Media Service with gRPC 🎥
*Created by **WRMDX***

## Overview 🚀
A microservices-based video media platform using gRPC for service communication.

## Project Structure 📁
```
mediaappgrpc/
├── proto/               # Protocol Buffers definitions
├── mediaserver/         # Backend service (port 9091)
└── mediaclient/        # Frontend service (port 8080)
```

## Technologies Used 💻
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring&logoColor=white)
- ![gRPC](https://img.shields.io/badge/gRPC-244c5a?style=flat&logo=google&logoColor=white)
- ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
- ![Protocol Buffers](https://img.shields.io/badge/Protocol%20Buffers-4285F4?style=flat&logo=google&logoColor=white)
- ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)

## Setup ⚙️
1. Start MySQL on port 3307
2. Create database: `video_db`
3. Run MediaServer: `mvn spring-boot:run` (port 9091)
4. Run MediaClient: `mvn spring-boot:run` (port 8080)

## API Reference 📚
### Upload Video
```http
POST /api/videos
Content-Type: application/json

{
    "title": "Video Title",
    "description": "Description",
    "url": "video_url",
    "durationSeconds": 120,
    "creator": {
        "id": 2,
        "name": "WRMDX",
        "email": "wrdmx@mail.ru"
    }
}
```

---
Created with ❤️ by WRMDX