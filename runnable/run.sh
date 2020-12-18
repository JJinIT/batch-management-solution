docker-compose down
docker-compose build producer subscriber
docker-compose up --scale subscriber=3 -d
./autoscaling.sh subscriber &

