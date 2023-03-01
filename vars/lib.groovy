def call(String stageName){
  if ("$stageName"=='Initialize'){
          echo "PATH = ${M2_HOME}/bin:${PATH}"
          echo "M2_HOME = /opt/maven"
	  echo "This is ${stageName} stage."
  }
 else if ("$stageName"=='Git Clone'){
        echo "This is ${stageName} stage."
        sh 'git clone https://github.com/mtomar247/SharedLibrary.git '
 }
	
 else if("$stageName"=='Maven Build Test Install') {
	echo "This is ${stageName} stage."
	sh 'mvn -B -DskipTests clean package'
	sh 'mvn test'
	sh 'mvn -B -DskipTests clean install'
       }
 else if("$stageName"=='Build Docker Image') {
	echo "This is ${stageName} stage.Image Build is in progress...."
	sh 'docker build -t springboottest-docker-image .'
	echo "Docker build Image is success." 
       }      
 else if("$stageName"=='Logging into AWS ECR') {
	echo "This is ${stageName} stage.Logging is in progress...."
	sh """aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 780315893543.dkr.ecr.us-east-1.amazonaws.com"""
	echo "Logging is success." 
       }    

 else if("$stageName"=='Pushing to ECR') {
	 echo "This is ${stageName} stage.Pushing is in progress...."
	sh """docker tag springboottest-docker-image:latest 780315893543.dkr.ecr.us-east-1.amazonaws.com/minakshi-ecr"""
        sh """docker push 780315893543.dkr.ecr.us-east-1.amazonaws.com/minakshi-ecr"""
	echo "Image Pushing is success." 
       }    
//else if("$stageName"=='kubernetes deployment') {
//	echo "This is ${stageName} stage.Deployment is in progress...."
//	sh 'aws eks --region us-east-1 update-kubeconfig --name KubernetesCluster'
  //      sh 'kubectl apply -f Deployment.yaml'
//	echo "Deployment is success." 
  //     }
       
}

