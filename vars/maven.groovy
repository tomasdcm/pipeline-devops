def call(){
	
	
	String[] str	
      	str = params.stage.split(';')
	
	
	def flag = true
	for (int i = 0; i < str.size(); i++) {
		switch(str[0]) {
			case "compile":		   
			case "sonar":
			case "test":
			case "code":
			case "run":
			case "nexus":
			case "":
			    flag = true
		  
			 default:
			    flag = false
			    break
		}	
	}
	
	
	
	if(flag)
	{
		if(str.contains('compile') || params.stage.isEmpty() )
		{	
			stage('Compile Code') {            
            			bat "mvn clean compile -e"
        		}
		}

		if(str.contains('sonar') || params.stage.isEmpty())
		{
			stage('SonarQube analysis') {            
				    def scannerHome = tool 'sonar-scanner';
				    withSonarQubeEnv('sonar-server') { 
				    bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.sources=src -Dsonar.java.binaries=build "
			    }
           
        		}
		}

		if(str.contains('test') || params.stage.isEmpty())
		{
			stage('Test Code') {           
				bat "mvn clean test -e" 
			}
		}
		
		if(str.contains('code') || params.stage.isEmpty())
		{
			stage('Jar Code') {           
                    		bat "mvn clean package -e"            
        		}
		}
		
		if(str.contains('run') || params.stage.isEmpty())
		{
			stage('Run Jar') {           
                    		bat "start /min mvn spring-boot:run &"           
        		}
		}

		if(str.contains('nexus') || params.stage.isEmpty())
		{
			stage('Nexus') {            
				bat "curl -v --user admin:holamundo --upload-file C:/Users/Tomás/.jenkins/workspace/pipeline_sonar_feature-sonar/build/DevOpsUsach2020-0.0.1.jar http://http://8e4a-181-43-194-204.ngrok.io/repository/test-repo/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar "            
			}
		}

	}
	else{
		println 'Ingresó una o más stages que no existen. Favor corregir y volver a construir pipeline.'
	}

}

return this;
