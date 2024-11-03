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
            steps {
                echo 'Pushing Image....'
                bat "docker push lazysaif/seleniumtest"
            }
        }
    }
}