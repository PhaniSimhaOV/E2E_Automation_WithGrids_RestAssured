pipeline {

    agent any

    environment{
        JENKINS_TRIGGERED_BY = "${currentBuild.getBuildCauses()[0].shortDescription}"
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


        stage('Run tests') {
            steps {
                echo "Starting Tests..."
                bat "mvn clean test"
            }
        }
    }

    post {
        always {
            echo "Post run actions ..."
        }
    }
}