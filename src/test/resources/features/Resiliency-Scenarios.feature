Feature: Testing how the system responds under adverse conditions

  Scenario: A request sent while the database is down should result in a 500
    Given The SQL service timeouts after 2 millisecond
    When A request GET all request is sent to the service
    Then A 500 error should be returned
    And bytes have been received by proxy
    And bytes have not been sent to database


  Scenario: A post request sent while the connection timeouts during the response from the SQL server
    Given The SQL service timeouts after 2 millisecond upstream
    When A post request is sent to the service
    Then A 500 error should be returned
    And bytes have been received by proxy
    And bytes have been sent to database