/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
  
  pipeline	{

	/*agent any*/
	
	/*environment {
		    STAGE = ''
		}*/

			
		stage('Pipeline'){
				steps{
					script{
						println 'Pipeline'
						gradle()          
					}
				}
		}
		

		/*post {
			success {
				slackSend color: 'good', message: 'success!'
			}
			
			failure {
				slackSend color: 'danger', message: "Ejecución fallida"
				error "Ejecución fallida en stage"
			}
		}*/
	
	}
}

return this;