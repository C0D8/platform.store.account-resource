pipeline{
    agent any
    stages{
        stage('Jenkis Account'){
            steps{
                echo 'Jankins account interface'
            }
        }
        stage('Build Interface'){
            steps{
                build job 'store account', wait: true
            }
        }

        stage('Build'){
            steps{
                sh 'mvn clean package'
            }
        }
       
    }
}