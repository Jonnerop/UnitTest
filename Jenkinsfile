pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Jonnerop/UnitTest.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publibat Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publibat Coverage Report') {
            steps {
                jacoco()
            }
        }
    }
}
