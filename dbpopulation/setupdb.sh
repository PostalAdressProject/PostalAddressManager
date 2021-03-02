#!/usr/bin/env bash
#
# ./gradlew clean build

# For single json file import
#mongoimport -d databaseName -c collectionName --file myJsonFile.json -u username -p password --authenticationDatabase admin

#with auth
# -u username -p password --authenticationDatabase admin
#mongoimport --host=localhost --port=27017 --db=address --collection=us --type=csv --file=us/*.csv --headerline

cd address

fun1 () {
    echo "run function1 $@"
    cd $1 $2
    # string replace gives bad substitution error
    # result=${str//$find/$replace}
    for i in *.csv;
    do
      echo ${i%.*};
      mongoimport --host=localhost --port=27017 --db=AddressAggregate --collection=$2 --type=csv --file=$i --headerline --mode=upsert --drop;
    done
    echo "end of import."
    cd ../../
}

for f in *; do
    if [ -d "$f" ]; then
        # $f is a directory
        for sf in $f/*; do
            if [ -d "$sf" ]; then
                # $f is a sub directory
                echo "$f""_""$sf"
                fun1 $sf $f
            fi
        done
    fi
done
