curl -i -X POST http://localhost:8080/api/v1/users \
-H "Content-Type: application/json" \
-d '{
  "username": "mike_tyson",
  "password": "badass_secret",
  "fullName": "Mike Tyson",
  "email": "mike.tyson@gamil.com",
  "agency": "Example Agency 13",
  "appContact": "Allen Iverson",
  "appContactEmail": "allen.iverson@gmail.com"
  }'