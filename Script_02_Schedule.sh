#! /bin/sh
#Schedule in cron to get newest txt metar data ( downloads txt files to metarData dir )
URL=https://tgftp.nws.noaa.gov/data/observations/metar/stations/
wget -O - https://tgftp.nws.noaa.gov/data/observations/metar/stations/ >b2.html
#Saves what is inisde href tags in html file
sed -n 's/.*href="\([^"]*\).*/\1/p' b2.html >output3.txt
sed -i "s|^|$URL|" output3.txt
sed -i '1,2d' output3.txt
rm b2.html
#Creates metarData dir and downloads links from txt file skipping files if no newer version is located on the server
mkdir -p metarData
wget -N --directory-prefix=metarData --content-disposition --trust-server-names -i output3.txt
rm output3.txt
#Loops through directory and concatinates txt files to one metardata file
find metarData/ -type f -name "*.TXT" | while read txt; do
cat $txt >>metardata.txt;
done
#Removes rows with dates that are not needed
awk 'NR % 2 == 0' metardata.txt > metardataAdj1.txt
#Adds comma so icao code can be seperated to enable bulk load into mariaDB from metardataAdj2
sed 's/\(^....\)/\1,/' <metardataAdj1.txt>metardataAdj2.txt
rm metardata.txt
rm metardataAdj1.txt
#Bulk loads metardata from metdataAdj file
mysql --user='banej' --password='31260' <<EOF
LOAD DATA LOCAL INFILE 'metardataAdj2.txt' INTO TABLE java.metar FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
(icaocode,data);
EOF
rm metardataAdj2.txt
