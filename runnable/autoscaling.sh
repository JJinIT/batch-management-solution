if [ -z "$1" ]
then
	echo "Check args"
	exit
fi

while true; do
	targets=`docker ps --format '{{.Names}}' | grep $1`
	percs=`docker stats $targets --no-stream --format '{{.CPUPerc}}' | awk -F'%' '{print $1}'`
	num=`docker ps | grep $1 | wc -l`

	for perc in $percs
	do
		if (( $(echo "$perc > 70" | bc -l )))
		then
			next=`echo "$num + 1" | bc -l`
			echo "scale out : ${next}"
			docker-compose up --scale "$1"=$next -d
		fi
	done

	sleep 20
done
