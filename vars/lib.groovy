def call(String stageName){
  if ("$stageName"=='Initialize'){
                    echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
  }

 else if("$stageName"=='Maven Build Test Install') {

    sh 'mvn -B -DskipTests clean package'
		sh 'mvn test'
		sh 'mvn -B -DskipTests clean install'
                }
            }
       


