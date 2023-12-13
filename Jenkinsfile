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


        stage('Prepare Version') {
    steps {
        // Prepare version (if needed)
        script {
            def versionFile = 'path/to/version.properties'
            def newVersion = readFile(versionFile).trim()

            // Assume you want to increment the version (you can customize as needed)
            def versionComponents = newVersion.split("\\.")
            versionComponents[2] = (versionComponents[2] as Integer + 1).toString()
            newVersion = versionComponents.join('.')

            writeFile file: versionFile, text: newVersion

            echo "Updated version to: ${newVersion}"
        }
    }
}

stage('Distribute Version') {
    steps {
        // Deploy to Nexus or other artifact repository
        script {
            def mavenSettings = '''
                <?xml version="1.0" encoding="UTF-8"?>
                <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
                          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                                              http://maven.apache.org/xsd/settings-1.0.0.xsd">
                  <servers>
                    <server>
                      <id>nexus</id>
                      <username>your-nexus-username</username>
                      <password>your-nexus-password</password>
                    </server>
                  </servers>
                </settings>
            '''

            writeFile file: 'settings.xml', text: mavenSettings

            // Deploy to Nexus
            sh 'mvn deploy --settings settings.xml'
        }
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














