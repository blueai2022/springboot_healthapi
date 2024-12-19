curl -i -X POST http://localhost:8080/api/v1/users \
-H "Content-Type: application/json" \
-d '{
  "username": "john_doe",
  "fullName": "John Doe",
  "email": "john.doe@example.com",
  "agency": "Example Agency",
  "appContact": "John Contact",
  "appContactEmail": "john.contact@example.com"
  }'