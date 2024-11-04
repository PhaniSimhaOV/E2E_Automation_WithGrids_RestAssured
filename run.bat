docker system prune -f
docker build -t=lazysaif/seleniumtest .
docker-compose -f grid.yaml up --scale chrome=1 -d
set BROWSER=chrome && set CUCUMBER_TAG=@BrokenTest && docker-compose -f docker-compose-tests.yaml up
docker-compose -f grid.yaml down
docker-compose down