pipeline {
    agent any

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
                    def scannerHome = tool 'sonarqube-scanner'
                    withSonarQubeEnv('SonarQube Server') {
                    sh "${scannerHome}/bin/sonar-scanner"
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














