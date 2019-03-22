## Requirements
* Maven
* JDK 8

## Steps to run the application
1. Download project
2. Go into the project directory
3. Run: mvn clean install
4. Go into target directory
5. Run: java -jar money-transfer-1.0-SNAPSHOT.jar

## Available API
| Query | Description |
| ------- | ----------- |
| POST /account     | Create a new account. Returns id. |
| GET /account/{id} | Returns account information by id. |
| PUT /account/{id}/add?amount=amount | Add amount on account. |
| PUT /account/{id}/subtract?amount=amount | Subtract amount from account. |
| POST /account/{id}/transfer?recipientId=recipientId=recipientId&amount=amount | Transfer amount from one account to another. |
| DELETE /account/{id} | Delete account by id. |