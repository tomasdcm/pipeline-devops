def call(){
  
  pipeline {

	agent any	
	

	parameters {
  		choice choices: ['gradle', 'maven'], description: 'Favor indicar herramienta de construccion', name: 'builTools'
		string(defaultValue: '', name: 'stage', description: 'Favor ingresar la(s) etapas a ejecutar, separando con ;', trim: true)
		
		
	}

	stages{
		stage('Pipeline'){
			steps{
				script{				    
					println "Pipeline"

					if(params.builTools == 'gradle')
					{
					   gradle()
					}
					else
					{
					   maven()
					}
				}
			}
		}		
		
	}
}

}

return this;
