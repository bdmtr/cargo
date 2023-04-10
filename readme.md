<h1>Cargo Delivery</h1>
<h2>EPAM OnlineUA Java Program 2022</h2>

The website of the cargo delivery company contains information about the directions of delivery, as well as tariffs (tariffs depend on the distance, weight and dimensions of the cargo). To calculate shipping, Google Matrix API is implemented.

There are roles:
- User
- Authorized User
- Manager


An unregistered user can view information on the site by sorting or filtering it by delivery direction, and also has the
ability to calculate the cost of services.

An authorized user can create an order for delivery of goods and specify the delivery address. The order contains
information about the type of cargo, weight, volume and date of delivery. An authorized user in his account can pay for the delivery invoice.

The manager processes orders and forms invoices for payment, and can also receive reports on deliveries (by days and
destinations).

Passwords are hashed with BCrypt algorithm.

![Image alt](https://github.com/bdmtr/cargo/blob/master/schm.png)

![Image alt](https://github.com/bdmtr/cargo/blob/master/scr1.png)

![Image alt](https://github.com/bdmtr/cargo/blob/master/scr2.png)

![Image alt](https://github.com/bdmtr/cargo/blob/master/scr3.png)






