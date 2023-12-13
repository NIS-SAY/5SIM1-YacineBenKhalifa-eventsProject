pipeline {
    agent any

    environment {
        SCANNER_HOME=  tool 'sonarqube-scanner'
    }

    stages {
        stage('Checkout from Git') {
            steps {
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/NIS-SAY/5SIM1-YacineBenKhalifa-eventsProject.git'
            }  
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Run SonarQube Analysis') {
    steps {
        script {
            withSonarQubeEnv('sonarserver') {
                sh ''' 
                    $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.host.url=http://192.168.33.10:9000/ \
                    -Dsonar.login=1d105ea9eafe459607475b492717636f383c0155 \
                    -Dsonar.projectKey=eventsProject \
                    -Dsonar.projectName=eventsProject \
                    -Dsonar.java.binaries=.
                '''
            }
        }
    }
}


     
       
   
}














