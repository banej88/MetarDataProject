# BrankoDemo

Services implemented:

POST /subscriptions

   * JSON payload { "icaoCode": "A302" } subscribes to A302 airport service

POST /airport/A302/METAR

   * JSON payload { "data": "METAR A302 XXXX XXXX XXXX" } will store METAR data for airport given in path

GET /subscriptions

   * Shows all subscribed airports by showing icaoCodes only

GET /airport/A302/METAR

   * Shows latest METAR data database entry for airport given in path

DELETE /subscriptions/A302
  
   * Deletes subscription for airport given in path

Linux bash scripts:

Script_01_Initialize.sh
Creates databas, tables and fills subscription table with data.
If you need to schedule second script to pull metar data please uncomment last part in initialize script so it can be added to crontab after start.

Script 02 Schedule.sh
Downloads metar data and appends it into metar table.

NOTES:
Script implementation uses mariaDB.
Make sure that you have it installed and add user or grant privileges. 
Change username and password to your own in script and properties file. 
If other database is used, then syntax needs to be changed in bash script and properties file in spring directory.

Extra Tasks:

They are introduced in v1.0.1

Services implemented:

GET /subscriptions/active/1

  * Shows active airports ( 0 for inactive )

PUT /subscriptions/A302

  * Sets path variable A302 to value in body example JSON payload "active": "0" to represent inactive airport
  * If you use "active": "1" it is treated as active

GET /subscriptions/search/{icaoCode}
  * Fetches airports containing part of provided icaoCode

GET /airport/A302/METAR/subsetView
  * Fetches view that shows wind strength and temperature

POST /airport/A302/METAR/decode

  * JSON payload: "data": "METAR LDZA 121200Z 0902MPS 090V150 2000 R04/P2000N R22/P2000N OVC050 0/M01 Q1020="
  * Inserts metar data to decodedmetar table as decoded fields
  * Time, wind strength, temperature and overall visibility are decoded


