# code_challange

A simple code to process a backend system like twitter.

METHODS:

Posting
  Send POST request to /addMessage with parameters: 
    - user - who is posting. If it is new it is created.
    - message - message that is sending
  You will be abe to add a message for current user.

Wall
  Send GET request to /getMessages/{userName} where {userName} is the name of current user. 
  You will get the list of messages for current user 

Following
  Send POST request to /followUser with parameters:
    - user - name of user that will be someone following
    - followedUser - name of user that will be followed
  After that You will be able to follow another user
  
Timeline
  Send GET request to /getTimeline/{userName} where {userName} is the name of current user.  
  You will get the timeline with all messages from users that are current user following in proper orther.
  
Other
  Send GET request to /getUsers 
  You will get the list of all users that are registered to this system
  
  
  
RUNNING:

It is using Spring Boot and gradle. You can run it from console, use: ./gradlew build && java -jar build/libs/code_challenge-0.1.jar
