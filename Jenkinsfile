pipeline {

    agent any

    stages {
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
            echo "Stopping Docker..."
            bat "docker-compose -f grid.yaml down"
            bat "docker-compose -f docker-compose-tests.yaml down"
            archiveArtifacts artifacts: "output/report/index.html", followSymlinks: false
            archiveArtifacts artifacts: "output/report/emailable-report.html", followSymlinks: false
        }
    }
}