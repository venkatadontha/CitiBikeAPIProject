Feature: Get Status Code for the API - "GET" request


  Scenario Outline:
    Given user triggers GET API request call
    Then  Status code should be <statusCode>
    Then  user should get the total Networks Count <networkCount> in Response body
    Then  user should get the city "<city>" and "<country>"
    Then  user should get <latitude> and <longitude> corresponding to city "<city>"
    
  Examples:
    |statusCode|networkCount|city     |country|latitude|longitude|
    |    200   |    645     |Frankfurt|DE 	  |50.1072 |8.66375  |