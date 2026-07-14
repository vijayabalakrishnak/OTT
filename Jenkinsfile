pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = "ott-platform"
        CONTAINER_NAME = "ott-app"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/vijayabalakrishnak/OTT.git'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:latest .'
            }
        }

        stage('Remove Old Container') {
            steps {
                sh '''
                docker rm -f ${CONTAINER_NAME} || true
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker run -d \
                --name ${CONTAINER_NAME} \
                -p 8091:8080 \
                ${IMAGE_NAME}:latest
                '''
            }
        }

        stage('Verify Application') {
            steps {
                sh 'curl http://localhost:8091/actuator/health'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed.'
        }

        always {
            echo 'Pipeline execution finished.'
        }
    }
}
