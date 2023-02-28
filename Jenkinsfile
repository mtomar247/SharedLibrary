pipeline {
    agent any
    tools {
        maven "Maven"
        jdk "JDK"
    }
    stages {
        stage('Initialize'){
            steps{
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
            }
        }
        stage('Maven Build Test Install') {
            steps {
                dir('springboottest'){
                sh 'mvn -B -DskipTests clean package'
		sh 'mvn test'
		sh 'mvn -B -DskipTests clean install'
                }
            }
       }
//	stage('Sonar Scan'){
//		 steps{
//			 dir('springboottest'){
//			 withSonarQubeEnv('sonarqube'){
//		 sh 'mvn sonar:sonar'
//		 }
//	   }
//	 }
//	}
        stage('Build Docker Image'){
            steps{
                dir('springboottest'){
                    sh 'docker build -t springboottest-docker-image .'
                }
            }
	}
	 stage('Logging into AWS ECR') {
            steps {
                script {
                sh """aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 780315893543.dkr.ecr.us-east-1.amazonaws.com"""
                }
                 
            }
        }
	stage('Pushing to ECR') {
     	    steps{  
               script {
                sh """docker tag springboottest-docker-image:latest 780315893543.dkr.ecr.us-east-1.amazonaws.com/minakshi-ecr"""
                sh """docker push 780315893543.dkr.ecr.us-east-1.amazonaws.com/minakshi-ecr"""
         }
        }
      } 
	        stage("kubernetes deployment"){
			steps{
				script{
					dir('springboottest'){
		sh 'aws eks --region us-east-1 update-kubeconfig --name KubernetesCluster'
        sh 'kubectl apply -f Deployment.yaml'
    }
				}
} 
}
}
}

