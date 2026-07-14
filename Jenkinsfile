pipeline {
    agent any

    tools {
    maven 'Maven3'
    }

    environment {
        IMAGE_NAME = "ott-platform"
        IMAGE_TAG = "latest"
        SPRING_PROFILES_ACTIVE = "local"
    }

    stages {

        stage('Checkout') {
            steps { 
                  git branch: 'main',
    url: 'https://github.com/vijayabalakrishnak/OTT.git'
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Docker Push') {
            steps {
                sh '''
                docker tag ${IMAGE_NAME}:${IMAGE_TAG} <dockerhub-username>/${IMAGE_NAME}:${IMAGE_TAG}
                docker push <dockerhub-username>/${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                docker rm -f ott-app || true
                docker run -d \
                --name ott-app \
                -p 8080:8080 \
                -e SPRING_PROFILES_ACTIVE=local \
                ${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }
    }
}
