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
                build job: 'store.account', wait: true
            }
        }

        stage('Build'){
            steps{
                sh 'mvn clean package'
            }
        }

        stage('Build Image'){

            steps{
                script{
                    account = docker.build("c0d8/account:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }

        }

        stage('Push Image'){
            steps{
                script{
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential'){
                        account.push("${env.BUILD_ID}")
                        account.push("latest")
                    }
                }
            }
        }
       
    }
}