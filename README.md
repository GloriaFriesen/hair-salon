# Hair Salon

#### Hair Salon Java Web Application, Current Version: 03/24/2017

#### By Gloria Friesen

## Description
This Java web application allows the user to build and manage the stylists and clients at a hair salon.

## Setup/Installation Instructions
* In terminal, clone my repository:
  * `$ git clone https://github.com/GloriaFriesen/hair-salon.git`
* Connect to PSQL and run:
  * `# CREATE DATABASE hair_salon;`
* In the terminal, run:
  * `$ psql hair_salon < hair_salon.sql`
* In PSQL, run:
  * `# CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;`
* In the terminal:
  * Switch to hair-salon directory
  * To run the program, use command `$ gradle run`
* In your browser:
  * Go to localhost:4567


## Specifications

|Behavior|Input|Output|
|:---:|:---:|:---:|
|Application instantiates Stylist class|stylist|true|
|Application instantiates Stylist class with name|"Abby"|"Abby"|
|Application instantiates Stylist class with date of hire|08/16/2011|08/16/2011|
|Application instantiates Stylist class with favorite service|"color"|"color"|
|Application instantiates Stylist class with an ID|stylist|true|
|Application saves all Stylists to database|stylist|true|
|Application finds Stylist in database with it's ID|1|stylist|
|Application deletes Stylist from database|stylist|null|
|Application updates Stylist|"Abby"|"Pepper Jack"|
|Application instantiates Client class|client|true|
|Application instantiates Client class with name|"Tina"|"Tina"|
|Application instantiates Client class with a Stylist|"Tina"|1|
|Application instantiates Client class with an ID|client|true|
|Application saves all Clients to database|client|true|
|Application finds Client in database with it's ID|1|client|
|Application deletes Client from database|client|null|
|Application updates Client|"Tina"|"Louise"|


## Known Bugs
None that I know of.

## Support and contact details
Questions? Concerns? Suggestions? Reach out to me via github: <https://github.com/GloriaFriesen>.

## Technologies Used
* _Java_
* _Gradle_
* _JUnit_
* _Spark_
* _Velocity_
* _Postgres_
* _SQL_

## License
This software is licensed under the MIT license.
Copyright (c) 2017 Gloria Friesen.
