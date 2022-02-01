/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(String pipelineType){

	figlet 'Gradle'

	if (pipelineType == 'CI'){
		figlet 'Integracion Continua'

		stage('Build & Unit Test'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			script{
			bat "gradle build"
			}
		}

		stage('Sonar'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			script{
			def scannerHome = tool 'sonar-scanner';
				    withSonarQubeEnv('sonar-server') { 
				    bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.sources=src -Dsonar.java.binaries=build " 
			}
			}
		}

		stage('Run'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			script{
			bat "gradle bootRun "  
			}
		}

		stage('Test'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			script{
			bat "gradle test"
			}
		}

		stage('UploadSnapshotJar'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			script{
			bat "curl -v --user admin:holamundo --upload-file C:/Users/Tomás/.jenkins/workspace/pipeline_sonar_feature-sonar/build/DevOpsUsach2020-0.0.1.jar http://8e4a-181-43-194-204.ngrok.io/repository/test-repo/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar "            
			}
		}
	} else {
		figlet 'Delivery Continuo'

		stage('DownloadSnapshotJar'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			                script{
                    bat "curl -L  -u admin:123456 http://7fb6-186-79-184-102.ngrok.io/repository/test-repo/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar --output DevOpsUsach2020-0.0.1.jar" 
                }                                    


		}
		stage('RunSnapshotJar'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			script{
                    		bat "start /min mvn spring-boot:run &" 
                	}                                    
		}
		stage('TestSnapshotJar'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			script{
                  	  bat "mvn clean test -e"    
                	}                                                 
		}
		stage('UploadReleaseJar'){
			STAGE = env.STAGE_NAME
			figlet "Stage: ${env.STAGE_NAME}"
			                script{
                    bat "curl -v --user admin:123456 --upload-file C:/Users/tom/.jenkins/workspace/pipeline-maven_feature-nexus-qa/DevOpsUsach2020-0.0.1.jar http://7fb6-186-79-184-102.ngrok.io/repository/test-repo/com/devopsusach2020/DevOpsUsach2020/1.0.0/DevOpsUsach2020-1.0.0.jar " 
                }                                    


		}
	}
}

return this;
