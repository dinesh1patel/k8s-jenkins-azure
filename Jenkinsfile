//Declarative notation
pipeline {
  agent any {
    stages {
      stage("Git Clone") {
        steps {
          git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'https://github.com/dinesh1patel/k8s-jenkins-azure'
        }
      }
      stage('Gradle Build') {
        steps {
          sh './gradlew build'
        }
      }
      stage("Docker build"){
        steps {
          sh 'docker version'
          sh 'docker build -t din-docker-demo .'
          sh 'docker image list'
          sh 'docker tag din-docker-demo dinik11/din-docker-demo:din-docker-demo'
        }
      }
      withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
        sh 'docker login -u dinik11 -p $PASSWORD'
      }
      stage("Push Image to Docker Hub"){
        steps {
          sh 'docker push  dinik11/din-docker-demo:din-docker-demo'
        }
      }
      stage("kubernetes deployment"){
        steps {
          //sh 'az aks get-credentials --resource-group DefaultResourceGroup-SUK --name aks-test-cluster'
          sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
        }
      }
    }
  }
}
