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
If other databse is uses syntax needs to be changed in bash script and properties file in spring directory.
