pipeline {

    agent any

    stages {
        stage('Build on latest code') {
            steps {
               echo "Starting build..."
               bat " mvn clean package -DskipTests"
               bat "docker build -t=lazysaif/seleniumtest ."
            }
        }
        stage('Push image to docker') {
             environment{
                DOCKER_HUB = credentials('dockerhub-creds')
             }

             steps {
                echo "Logging in Docker..."
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push lazysaif/seleniumtest"
             }
        }

        stage('Start Grid') {
            steps {
                echo "Starting Grid..."
                bat "docker-compose -f grid.yaml up -d"
            }
        }
        stage('Eun tests') {
            steps {
                echo "Starting Tests..."
                bat "set BROWSER=chrome && set CUCUMBER_TAG=@BrokenTest && docker-compose -f docker-compose-tests.yaml up"
            }
        }
    }

    post {
        always {
            echo "Logging out of docker ..."
            bat "docker logout"

            echo "Stopping Docker..."
            bat "docker-compose -f grid.yaml down"
            bat "docker-compose -f docker-compose-tests.yaml down"
            archiveArtifacts artifacts: "output/report/index.html", followSymlinks: false
            archiveArtifacts artifacts: "output/report/emailable-report.html", followSymlinks: false
        }
    }
}