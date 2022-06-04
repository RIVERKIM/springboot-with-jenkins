pipeline {
    agent any

    environment {
        registryCredential = "docker-hub"
        dockerImageName = 'jenkins-practice'
        dockerImage = ''
    }

    stages {
        stage('checkout') {
            steps {
                echo 'Checkout project'
                checkout scm
            }
            post {
                success {
                    echo 'Successfully checkout project'
                }
                failure {
                    echo 'fail checkout project'
                }
            }
        }

        stage('Build') {
            steps {
                dir('.') {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean build'
                }
            }
            post {
                success {
                    echo 'Successfully build project'
                }
                failure {
                    echo 'fail build project'
                }
            }
        }

        stage('Build Docker') {
            steps {
                echo 'Build Docker'
                script {
                    dockerImage = docker.build dockerImageName
                }
            }

            post {
                success {
                    echo 'Successfully build docker image'
                }
                failure {
                    echo 'fail build docker image'
                }
            }
        }

        stage('Push DockerHub') {
            steps {
                echo 'Push image to DockerHub'
                script {
                    docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
                        dockerImage.push("latest")
                    }
                }
            }
            post {
                success {
                    echo 'Successfully pushing docker image'
                }
                failure {
                    echo 'fail pushing docker image'
                }
            }
        }
    }
}