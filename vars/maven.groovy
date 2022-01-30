def call() {
        
        stage('Compile') {
            steps {
                script{
                        echo 'Compile'
                    }
                }
            }
        
        stage('Test') {
            steps {
                script{
                        echo 'Test'
                    }
                }
            }
        
        stage('Jar') {
            steps {
                script{
                        echo 'Jar'
                    }
                }
            }
        
        stage('Run') {
            steps {
                script{
                        echo 'Run'
                    }
                }
            }
        
        stage('TestApp') {
            steps {
                    echo 'TestApp'
                }
            }
        
}

return this;