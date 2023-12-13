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
                // Add SonarQube analysis step here
                script {
                    withSonarQubeEnv('sonarserver') {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonner.host.url=http://http://192.168.33.10:9000/ -Dsonner.login=1d105ea9eafe459607475b492717636f383c0155 -Dsonner.projectKey=eventsProject -Dsonner.projectName=eventsProject \
                        -Dsonner.java.binaries=.  '''
                    }
                }
            }
        }

        stage('Prepare Version') {
            steps {
                echo "Prepare Version"
            }
        }

        stage('Distribute Version to Nexus') {
            steps {
               echo "Distribute Version to Nexus"
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build Docker image using Dockerfile
                sh 'docker build -t your-docker-image-name .'
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                // Push Docker image to DockerHub
                sh 'docker push your-docker-image-name'
            }
        }

        stage('Run Docker Compose') {
            steps {
                // Run Docker Compose with Spring Boot and MySQL images
                sh 'docker-compose up -d'
            }
        }
    }

   
}














