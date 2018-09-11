# ShopEasy-server
Java server application performing the backend processing for the shop easy android application.

When the customer enters the mall he/she has to first connect with the server which has been set up there. The server manages all the 
tasks performed during the shopping using the mobile application through different customers. The server should have the capacity to 
handle many simultaneous transactions from a number of customers. The connection between the mobile application and the server is through
Wi-Fi. The server can also provide the APK file to install the application for first time users.
After the mobile application connects with the server the customer starts building the shopping list by scanning the barcode of those 
items which he/she wants to buy. The application uses the mobile phones (Smartphone) inbuilt camera to scan the barcode over the item. 
For that android provides native APIs for the camera and the scanning.
When the customer finishes with the scanning task then he/she can submit the shopping list to the server. On receiving the shopping list
the server sends the shopping list to one of the handlers to collect all the items in the list. A handler is the person who collects the 
items for the customers. One handler can mange 3-4 customers shopping list at the same time.
The server also redirect the mobile application to the payment portal where the customer can select the mode of payment (credit card,
debit card, net banking etc.) and pay the bill amount for the purchased items. After successful payment of the bill, the soft copy of 
the bill generated is automatically downloaded to the customer’s mobile, in some format such as PDF or DOC.
When all the tasks are completed the customer can collect the shopping bag full of items purchased by him from the outlet of the shopping
mall. Or the customer have the choice for the home delivery, for that the customer have to give some additional information such as name,
address, phone no. etc. and after paying the delivery charges the bag can be given out for delivery.
There are some items which cannot be carried away by the customer such as wheat or rice bags, furniture, and other big things. Sometimes 
the customer doesn’t want to carry the bag because he/she have to visit some other place also, in such cases the customer can enjoy the
home delivery option.


Run the shopeasy - server application before opening the android application in the mobile.

To start the server, run the following java file.
shopEasy-server > src > start.java

You also need to set up the database for the application to run successfully.
