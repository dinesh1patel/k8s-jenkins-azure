node {

    stage("Git Clone"){

        git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'https://github.com/dinesh1patel/k8s-jenkins-azure'
    }

     stage('Gradle Build') {

       sh './gradlew build'

    }

    stage("Docker build"){
        sh 'docker version'
        sh 'docker build -t jhooq-docker-demo .'
        sh 'docker image list'
        sh 'docker tag jhooq-docker-demo dinik11/jhooq-docker-demo:jhooq-docker-demo'
    }

    withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
        sh 'docker login -u dinik11 -p $PASSWORD'
    }

    stage("Push Image to Docker Hub"){
        sh 'docker push  dinik11/jhooq-docker-demo:jhooq-docker-demo'
    }
    
    stage("kubernetes deployment"){
        //sh 'az aks get-credentials --resource-group DefaultResourceGroup-SUK --name aks-test-cluster'
        sh 'kubectl apply -f k8s-spring-boot-deployment.yml'
    }
} 
