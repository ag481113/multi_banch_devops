def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-pwd-in-jenkins', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t aditya1035/demo-app:test-pipeline .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push aditya1035/demo-app:test-pipeline'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
