def call(String stageName){
  if ("$stageName"=='Initialize'){
          echo "PATH = ${M2_HOME}/bin:${PATH}"
          echo "M2_HOME = /opt/maven"
	  echo "This is $stageName stage."
  }

 else if("$stageName"=='Maven Build Test Install') {
	echo "This is $stageName stage."
	sh 'mvn -B -DskipTests clean package'
	sh 'mvn test'
	sh 'mvn -B -DskipTests clean install'
       }
 else if("$stageName"=='Build Docker Image') {
	echo "This is $stageName stage.Image Build is in progress...."
	sh 'docker build -t springboottest-docker-image .'
	echo "Docker build Image is success." 
       }      
 else if("$stageName"=='Logging into AWS ECR') {
	echo "This is $stageName stage.Image Build is in progress...."
	sh 'docker build -t springboottest-docker-image .'
	echo "Docker build Image is success." 
       }    



}
       


