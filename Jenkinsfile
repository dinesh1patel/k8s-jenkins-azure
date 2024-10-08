pipeline {
     environment {
     	projectName="din-docker-demo"
	dockerId="dinik11"
        imageName="$dockerId/$projectName:jenkins${BUILD_NUMBER}"
	deploymentName="din-springboot"
	containerName="springboot"
    }
    agent any
    options {
        skipDefaultCheckout(true)
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '5', daysToKeepStr: '', numToKeepStr: '5')
    }
    stages {
        stage('Shell Output Test') {
            steps { 
                sh 'ls'
            }
        }
        stage('Echo Test') {
            steps { 
                echo 'Test from stage Echo Test'
            }
        }
        stage('Parallel Tasks Step') {
            parallel {
                stage('Priting All Global Variables') {
                    steps {
                        sh """
                        env
                        """
                    }
                }
                stage('Execute Echo Shell') {
                    steps {
                        sh 'echo "Hello"'
                    }
                }
                stage('Print PATH variable') {
                    steps {
                        sh "echo ${PATH}"
                    }
                }
            }
        }
        // Pipeline SCM Checkout from a GitHub Repository
	    // Syntax
	    // git branch: 'branch_name',
        // credentialsId: 'credential_by_id stored as global variable in Jenkins',
        // url: 'git@test.com/proj/test_proj.git'
        stage('Git Clone') {
            steps { 
                git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'https://github.com/dinesh1patel/k8s-jenkins-azure'
            }
        }
        // Gradle compilation and build
        stage('Gradle build') {
            steps { 
                sh './gradlew build'
            }
        }
        stage('Docker Build') {
            steps {
                //'''
		//sh 'docker version'
                //sh 'docker build -t $REPO_NAME .'
                //sh 'docker image list'
                //sh 'docker tag $REPO_NAME $IMAGE_NAME'
				
		sh 'docker version'
                sh 'docker image list'
		sh 'docker build -t ${imageName} .'
            }
        }
        stage('Push Image to Docker Hub') {
            steps {
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
			sh 'docker login -u ${dockerId} -p ${PASSWORD}'
                }
		sh 'docker push  ${imageName}'
            }
        }
        stage('kubernetes deployment') {
            steps { 
		sh 'az login --scope https://management.core.windows.net//.default'
                sh 'az aks get-credentials --resource-group DefaultResourceGroup-SUK --name aks-test-cluster'
                sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
		sh 'kubectl set image deployments/${deploymentName} ${containerName}=$imageName'
            }
        }
    }
    post { 
    	always { 
		echo 'Build Steps Completed '
	}
    }
}
