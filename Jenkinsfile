pipeline {
    agent any
    options {
        skipDefaultCheckout(true)
    }
    stages {
        stage('Echo Shell Test') {
            steps { 
                sh 'ls'
            }
        }
        stage('Echo Test') {
            steps { 
                echo 'Test from stage Echo Test'
            }
        }
        stage('Environment Analysis') {
            parallel {
                stage('Priting All Global Variables') {
                    steps {
                        sh """
                        env
                        """
                    }
                }
                stage('Execute Shell') {
                    steps {
                        sh 'echo "Hello"'
                    }
                }
                stage('Print ENV variable') {
                    steps {
                        sh "echo ${APP_ENV}"
                    }
                }
            }
        }
        stage('Git Clone') {
            steps { 
                git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'https://github.com/dinesh1patel/k8s-jenkins-azure'
            }
        }
        stage('Gradle build') {
            steps { 
                sh './gradlew build'
            }
        }
        stage('Docker Build') {
            steps { 
                sh 'docker version'
                sh 'docker build -t din-docker-demo .'
                sh 'docker image list'
                sh 'docker tag din-docker-demo dinik11/din-docker-demo:v3'
            }
        }
        stage('Push Image to Docker Hub') {
            steps { 
                withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
                    sh 'docker login -u dinik11 -p $PASSWORD'
                }
                sh 'docker push  dinik11/din-docker-demo:v3'
            }
        }
        stage('kubernetes deployment') {
            steps { 
                sh 'az aks get-credentials --resource-group DefaultResourceGroup-SUK --name aks-test-cluster'
                sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
            }
        }
    }
}
