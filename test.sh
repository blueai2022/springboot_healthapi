curl -i -X POST http://localhost:8080/api/v1/users \
-H "Content-Type: application/json" \
-d '{
  "username": "bruce_lee",
  "fullName": "Bruce Lee",
  "email": "bruce.lee@gamil.com",
  "agency": "Example Agency 1",
  "appContact": "Chuck Norris",
  "appContactEmail": "chuck.norris@gmail.com"
  }'