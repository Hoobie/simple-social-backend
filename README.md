# simple-social-backend

## Requirements
JDK 1.8

## How to build
```bash
./gradlew build
```

## How to run
```bash
java -jar rest-api/build/libs/simple-social-backend-rest-api-0.1.0-SNAPSHOT.jar 
```

## API

### Posting

|URI|Method|Params|Success response|Error response|Notes|
|---|---|---|---|---|---|
|/user/{userName}/post|POST|{"message": "content"}|Code: 200, Content: ID of the post|Code: 400 - if the message is too long|Creates a post and a user (if does not exist). The Content-Type: application/json header is required!|

### Wall

|URI|Method|Params|Success response|Error response|Notes|
|---|---|---|---|---|---|
|/user/{userName}/posts|GET||Code: 200, Content: An array of user's posts (in reverse chronological order)||Returns an empty array if a user does not exist|


### Following

|URI|Method|Params|Success response|Error response|Notes|
|---|---|---|---|---|---|
|/following/{followerName}/{followeeName}|POST||Code: 200|Code: 400 - if a user does not exist|Creates a follow relationship|

### Timeline

|URI|Method|Params|Success response|Error response|Notes|
|---|---|---|---|---|---|
|/user/{userName}/timeline|GET||Code: 200, Content: An array of followes' posts (in reverse chronological order)||Returns an empty array if a user does not exist|
