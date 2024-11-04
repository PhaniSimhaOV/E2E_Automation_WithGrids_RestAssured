pipeline {

    agent any

    stages {
        stage('Build Jar') {
            steps {
                echo "Building Jar..."
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                echo "Building Image..."
                bat "docker build -t=lazysaif/seleniumtest ."
            }
        }
        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials('dockerhub-creds')
        }
            steps {
                echo 'Pushing Image....'
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push lazysaif/seleniumtest"
            }
        }

        stage('Execute test cases') {
            steps {
                    bat 'run.bat'
                }
        }
    }

    post {
        always {
            echo "Logging out of Docker..."
            bat "docker logout"
        }
    }
}