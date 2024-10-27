docker-compose -f grid.yaml up --scale chrome=1 -d
set BROWSER=chrome && docker-compose up

docker-compose -f grid.yaml down
docker-compose down