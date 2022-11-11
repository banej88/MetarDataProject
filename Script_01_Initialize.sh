#! /bin/sh
#CREATE USER 'banej'@'localhost' IDENTIFIED BY '31260';
#GRANT ALL PRIVILEGES ON java.* TO 'banej'@'localhost';
mysql --user='banej' --password='31260' <<EOF
DROP DATABASE IF EXISTS java;
CREATE DATABASE java;
USE java;
CREATE TABLE metar (id int AUTO_INCREMENT, icaocode VARCHAR(6), data VARCHAR(900), PRIMARY KEY(id));
CREATE TABLE subscriptions (id int AUTO_INCREMENT,icaocode VARCHAR(6), subscribed TINYINT(1) DEFAULT(0),PRIMARY KEY(id));
EOF
#Gets all links from html file to use as available icao code
wget -O - https://tgftp.nws.noaa.gov/data/observations/metar/stations/ >b.html
sed -n 's/.*href="\([^"]*\).*/\1/p' b.html >output2.txt
awk '{print substr($0,1,length($0)-4)}' <output2.txt> output.txt
rm b.html
rm output2.txt
#Loads data from output.txt to mariaDB as icaoCode, skips first two lines not related to code
mysql --user='banej' --password='31260' <<EOF
LOAD DATA LOCAL INFILE 'output.txt' INTO TABLE java.subscriptions IGNORE 2 LINES (icaocode);
EOF
rm output.txt
#Scheduling second bash script to pull metar data every second hour
#write out current crontab
#crontab -l > mycron
#echo new cron into cron file
#echo "* */2 * * * bash Script_02_Schedule.sh" >> mycron
#install new cron file
#crontab mycron
#rm mycron
