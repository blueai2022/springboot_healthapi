curl -i -X POST http://localhost:8080/api/v1/users \
-H "Content-Type: application/json" \
-d '{
  "username": "jane_smith",
  "fullName": "Jane Smith",
  "email": "jane.smith@gamil.com",
  "agency": "Example Agency 2",
  "appContact": "John Smith",
  "appContactEmail": "john.smith@gmail.com"
  }'