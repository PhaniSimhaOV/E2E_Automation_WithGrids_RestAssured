#!/bin/groovy
def EMAIL_RECEIVERS = "md.saifansari@gmail.com"

pipeline {

    agent any

    environment{
        JENKINS_TRIGGERED_BY = "${currentBuild.getBuildCauses()[0].shortDescription}"
    }

    parameters {
            choice(name: 'environment', choices: ['uat', 'dev'], description: 'Which env to select?')
            choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Which browser to select to?')
            string(name: 'CUCUMBER_TAG', defaultValue: '@SmokeTest', description: 'Enter the tag/tags name.')
            string(name: 'reportsMail', defaultValue: EMAIL_RECEIVERS, description: 'Send report to these people.')
        }

   options {
    timeout(time: 2, unit: 'HOURS')
   }

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
                bat "docker-compose -f grid.yaml up --scale ${params.BROWSER}=1 -d"
            }
        }
        stage('Run tests') {
            steps {
                echo "Starting Tests..."
                bat "set BROWSER=${params.BROWSER} && set CUCUMBER_TAG=${params.CUCUMBER_TAG} && docker-compose -f docker-compose-tests.yaml up"
                script {
                    if (fileExists('output/report/testng-failed.xml')) {
                        error('Failed test cases found...')
                    }
                }
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
            cleanWs()
        }
    }
}