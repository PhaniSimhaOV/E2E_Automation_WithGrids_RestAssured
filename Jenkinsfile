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
        stage('Interactive Input') {
            steps {
                script {

                    // Variables for input
                    def inputConfig
                    def inputTest

                    // Get the input
                    def userInput = input(
                            id: 'userInput', message: 'Enter username and password:?',
                            parameters: [

                                    string(defaultValue: '',
                                            description: 'Enter username',
                                            name: 'username'),
                                    string(defaultValue: '',
                                            description: 'Enter Password',
                                            name: 'password'),
                            ])

                    // Save to variables. Default to empty string if not found.
                    inputConfig = userInput.username?:''
                    inputTest = userInput.password?:''

                    // Echo to console
                    echo("IQA Sheet Path: ${inputConfig}")
                    echo("Test Info file path: ${inputTest}")
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
                    sh "mvn clean test -Dcucumber.filter.tags=${cucumberTag} -DtestCase="${userInput}""
                  } else {
                    bat 'mvn clean test -Dcucumber.filter.tags="@DatabaseConnection"'
                    bat "mvn clean test -Dcucumber.filter.tags=${cucumberTag} -DtestCase="${userInput}""
                  }
                   
                }
            }
        }

        
        
    }
    post {
        always {
            echo "Post run actions ..."
        }
    }
}


