pipeline {

    agent any

    environment{
        JENKINS_TRIGGERED_BY = "${currentBuild.getBuildCauses()[0].shortDescription}"
    }

    parameters {
            choice(name: 'environment', choices: ['uat', 'dev'], description: 'Which env to select?')
            choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Which browser to select to?')
            string(name: 'CUCUMBER_TAG', defaultValue: '@SmokeTest', description: 'Enter the tag/tags name.')
        }

   options {
    timeout(time: 2, unit: 'HOURS')
   }

    stages {
        stage('Clean Workspace') {
            steps {
                script {
                    // Delete the contents of the 'src' directory
                    echo "Cleaning up the 'src' directory"
                    // Make sure you use 'dir' to target the correct directory
                    dir('src') {
                        deleteDir()  // Deletes all files and subdirectories in 'src'
                    }
                }
            }
        }

        stage('Checkout from Github') {
            steps {
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
               echo "Starting build..."
               bat " mvn clean package -DskipTests"
            }
        }

        stage('Run tests') {
            steps {
                echo "Starting Tests..."
            }
        }
    }

    post {
        always {
            echo "Post run actions ..."
        }
    }
}