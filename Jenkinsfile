pipeline {
    agent any

    environment {
        registryCredential = "docker-hub"
        dockerImageName = 'kgr4163/jenkins-practice'
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

        stage('Docker Run') {
            steps {
                echo 'Pull docker image and docker image run'

                sshagent (credentials: ['d37a8a1a-0559-4369-af8b-c42ded2f1f1f']) {
                    sh "ssh -o StrictHostKeyChecking=no ec2-user@3.39.201.123 'docker pull kgr4163/jenkins-practice'"
                    sh "ssh -o StrictHostKeyChecking=no ec2-user@3.39.201.123 'docker rm -f \$(docker ps -aq --filter name=springboot)'"
                    sh "ssh -o StrictHostKeyChecking=no ec2-user@3.39.201.123 'docker run -d --name=springboot -p 8083:8083 kgr4163/jenkins-practice'"
                }
            }

            post {
                success {
                    echo 'Successfully run docker image'
                }
                failure {
                    echo 'fail to running docker image'
                }
            }
        }
    }
}