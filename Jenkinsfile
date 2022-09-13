pipeline {
     environment {
     	projectName="din-docker-demo"
	dockerId="dinik11"
        imageName="$dockerId/$projectName:jenkins${BUILD_NUMBER}"
    }
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
                        sh "echo ${PATH}"
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
                //Docker Repo Config
		//sh '''
		//REPO_NAME="din-docker-demo"
		//REPO_USERNAMENAME="dinik11"
                //IMAGE_NAME="$REPO_USERNAMENAME/$REPO_NAME:jenkins${BUILD_NUMBER}"
		//docker version
		//docker build -t $REPO_NAME .
		//docker image list
		//docker tag $REPO_NAME $IMAGE_NAME
                //'''
		sh 'docker version'
                //sh 'docker build -t $REPO_NAME .'
                sh 'docker image list'
                //sh 'docker tag $REPO_NAME $IMAGE_NAME'
		//sh 'docker build -t {imageName} .'
		sh 'docker build -t $imageName .'
            }
        }
        stage('Push Image to Docker Hub') {
            steps {
                //Docker Repo Config
		//sh '''
		//REPO_NAME="din-docker-demo"
		//REPO_USERNAMENAME="dinik11"
                //IMAGE_NAME="$REPO_USERNAMENAME/$REPO_NAME:jenkins${BUILD_NUMBER}"
		//withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
                //    docker login -u $REPO_USERNAMENAME -p $PASSWORD
                //}
                //docker push  $IMAGE_NAME
                //'''
                withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
			sh 'docker login -u $dockerId -p $PASSWORD'
                }
		sh 'docker push  {imageName}'
            }
        }
        stage('kubernetes deployment') {
            steps { 
                sh 'az aks get-credentials --resource-group DefaultResourceGroup-SUK --name aks-test-cluster'
                sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
		sh 'kubectl set image deployments/din-springboot springboot=$imageName'
            }
        }
    }
    post { 
    	always { 
		echo 'Build Steps Completed'
	}
    }
}
