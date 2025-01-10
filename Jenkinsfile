pipeline {
    agent any
    tools {
        maven 'Maven' // Maven installation configured in Jenkins
    }
    environment {
        JENKINS_TRIGGERED_BY = "${env.BUILD_USER}" // More reliable for user trigger info
    }
    parameters {
        choice(name: 'Device', choices: ['Mobile', 'Web'], description: 'Select the device')
        choice(name: 'Module', choices: [
            'Lead generation', 
            'Create test drive & followup', 
            'Booking', 
            'Invoice', 
            'ExtendedWarranty', 
            'SOT'
        ], description: 'Select the module to build')
    }
    options {
        timeout(time: 2, unit: 'HOURS')
    }
    stages {
        stage('Clean Workspace') {
            steps {
                script {
                    echo "Cleaning up workspace..."
                    deleteDir() // Cleans the entire workspace
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
                    // Interactive input for username and password
                    def userInput = input(
                        id: 'userInput',
                        message: 'Enter username and password:',
                        parameters: [
                            string(name: 'username', defaultValue: '', description: 'Enter username'),
                            string(name: 'password', defaultValue: '', description: 'Enter password')
                        ]
                    )

                    // Store inputs
                    def inputConfig = userInput.username ?: ''
                    def inputTest = userInput.password ?: ''

                    // Determine Cucumber tag based on parameters
                    def cucumberTag = ''
                    if (params.Module == 'Lead generation' && params.Device == 'Mobile') {
                        cucumberTag = '@CreateNewLead'
                    } else if (params.Module == 'Create test drive & followup' && params.Device == 'Mobile') {
                        cucumberTag = '@StartTestDriveAndAddFollowUp'
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

                    // Run Maven commands
                    if (isUnix()) {
                        sh 'mvn clean test -Dcucumber.filter.tags="@DatabaseConnection"'
                        sh "mvn clean test -Dcucumber.filter.tags='${cucumberTag}' -DtestCase='${inputConfig},${inputTest}'"
                    } else {
                        bat 'mvn clean test -Dcucumber.filter.tags="@DatabaseConnection"'
                        bat "mvn clean test -Dcucumber.filter.tags='${cucumberTag}' -DtestCase='${inputConfig},${inputTest}'"
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Post run actions..."
        }
    }
}
