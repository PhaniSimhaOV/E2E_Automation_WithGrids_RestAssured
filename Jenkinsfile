pipeline {

    agent any

    environment{
        JENKINS_TRIGGERED_BY = "${currentBuild.getBuildCauses()[0].shortDescription}"
    }

 parameters {
            choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Which browser to select to?')
//             choice(name: 'Module', choices: ['Lead generation','Test drive','Booking','Invoice','Exwarranty','SOT'], description: 'Please select the module to built')
//
            string(name: 'CUCUMBER_TAG', defaultValue: '@SmokeTest', description: 'Enter the tag/tags name.')
            string(name: 'reportsMail', defaultValue: EMAIL_RECEIVERS, description: 'Send report to these people.')
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
                bat "set CUCUMBER_TAG=${params.CUCUMBER_TAG}"
            }
        }

        stage('Clean up') {
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