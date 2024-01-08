This is a card management system that gives users details about their transaction cards

It was built using 
  1. Spring boot
  2. Java
  3. Mysql
  4. JPA
  5. Webflux
  6. JWT


There service has 4 endpoints in total

* The service has a user management feature which has two endpoints
  <----------------------------------------------------------------------------------------------------------->
  1. Register endpoint (.../api/v1/auth/register)
       This is a post request that registers users on the service
       ----body----
       {
        "firstName" : "firstName",
        "lastName" : "lastName",
        "email" : "email",
        "password" : "password"  
      }


  2.login endpoint (.../api/v1/auth/authenticate)
      This is a post request that authenticates users on the service. This returns a JWT token which will be used for subsequent requests
       ----body----
      {
          "email" : "email",
          "password" : "password" 
      }
  <----------------------------------------------------------------------------------------------------------->

  

* The service has a card management feature which is queried to get card details and performance details. It has two endpoints
<----------------------------------------------------------------------------------------------------------->
    1.Verify card scheme endpoint (.../api/v1/card-scheme/verify/{bin})
        This is a get request which is used to retrieve card detail that resides on a third party API
        ----body----
        NO body


   2. Card statistics endpoint (.../api/v1/card-scheme/stats?start=0&limit=4)
        This is a get request which is used to retrieve paginated card detail statistics of existing card or  cards that have been successfully retrieved from the third party API
        ----body----
        NO body
    

