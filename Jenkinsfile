pipeline {

    agent any
     tools {
            maven 'Maven'  // This is the name of the Maven installation you just configured
        }
    environment{
        JENKINS_TRIGGERED_BY = "${currentBuild.getBuildCauses()[0].shortDescription}"
    }

 parameters {
            choice(name: 'Device', choices: ['Mobile', 'Web'], description: 'Which browser to select to?')
            choice(name: 'Module', choices: ['Lead generation','Create test drive & followup','Booking','Invoice','ExtendedWarranty','SOT'], description: 'Please select the module to built')
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
        stage('Run Tests') {
            steps{
                echo "Starting Tests..."

            
            script {
                def cucumberTag = ''

                // Set the Cucumber tag based on selected module and device
                if (params.Module == 'Lead generation' && params.Device == 'Mobile') {
                    cucumberTag = '@CreateNewLead'
                } else if (params.Module == 'Create test drive & followup' && params.Device == 'Mobile') {
                    cucumberTag = '@StartTestDriveAndAddFollowUp'
                } else if (params.Module == 'Complete Test Drive' && params.Device == 'Web') {
                    cucumberTag = '@CompleteTestDrive'
                } else if (params.Module == 'Booking' && params.Device == 'Web') {
                    cucumberTag = '@Booking'
                } else if (params.Module == 'Invoice' && params.Device == 'Web') {
                    cucumberTag = '@Invoice'
                } else if (params.Module == 'ExtendedWarranty' && params.Device == 'Web') {
                    cucumberTag = '@ExtendedWarranty'
                } else if (params.Module == 'SOT' && params.Device == 'Web') {
                    cucumberTag = '@SOT'
                }

                echo "CUCUMBER_TAG: ${cucumberTag}"
                  if (isUnix()) {
                    sh 'mvn clean test -Dcucumber.filter.tags="@DatabaseConnection"'
                    sh "mvn clean test -Dcucumber.filter.tags=${cucumberTag}"
                  } else {
                    bat 'mvn clean test -Dcucumber.filter.tags="@DatabaseConnection"'
                    bat "mvn clean test -Dcucumber.filter.tags=${cucumberTag}"
                  }
                }
            }
            
        }

        // stage('Interactive Input') {
        //     def userInput = input(
        //         id: 'userInput', message: 'Enter path of test reports:',
        //         parameters: [
        //             string(defaultValue: 'None', description: 'Path of config file', name: 'Config'),
        //             string(defaultValue: 'None', description: 'Test Info file', name: 'Test')
        //         ]
        //     )

        //     def inputConfig = userInput['Config'] ?: ''
        //     def inputTest = userInput['Test'] ?: ''

        //     echo "IQA Sheet Path: ${inputConfig}"
        //     echo "Test Info file path: ${inputTest}"

        //     writeFile file: "inputData.txt", text: "Config=${inputConfig}\r\nTest=${inputTest}"
        //     archiveArtifacts 'inputData.txt'
        // }
        
    }
    post {
        always {
            echo "Post run actions ..."
        }
    }
}


