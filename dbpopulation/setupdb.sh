#!/usr/bin/env bash
#
# ./gradlew clean build

# For single json file import
#mongoimport -d databaseName -c collectionName --file myJsonFile.json -u username -p password --authenticationDatabase admin

#with auth
# -u username -p password --authenticationDatabase admin
#mongoimport --host=localhost --port=27017 --db=address --collection=us --type=csv --file=us/*.csv --headerline

cd us/wa

for i in *.csv;
do
  echo ${i%.*};
  mongoimport --host=localhost --port=27017 --db=address --collection=us_wa --drop --type=csv --file=$i --headerline --mode=upsert;
done

echo "end of import."