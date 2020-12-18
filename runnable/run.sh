cd /home/ubuntu/batch
cd theone
git pull
cd ..
docker-compose down
docker-compose build producer subscriber
docker-compose up --scale subscriber=3 -d

