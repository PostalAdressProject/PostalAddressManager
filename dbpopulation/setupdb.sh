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
    cd $1
    # string replace gives bad substitution error
    # result=${str//$find/$replace}
    for i in *.csv;
    do
      echo ${i%.*};
      #      --drop
      mongoimport --host=localhost --port=27017 --db=address --collection="address" --type=csv --file=$i --headerline --mode=upsert;
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
                fun1 $sf
            fi
        done
    fi
done




#for dir; do
#  echo "dir."
#  for f in "$dir"/*; do
#    fun1 "$f"
#  done
#done

#for d in */ ; do
#    for sd in */ ; do
#        echo "$d""_""$sd"
#        done
#done


#cd us/wa
#pwd | awk -F/ '{print $NF}'
##fun1 $NF

# bulk import not giving the di structure
#mongorestore --host=localhost --port=27017 --nsInclude=address.* address/