pipeline{
    agent any
    stages{
        stage('Run Tests'){
            steps{
                //withMaven(jdk: 'Java 1.8', maven: 'Maven3') {
                sh"/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn clean test -Dcucumber.filter.tags=\"${TAG}\""
                    //bat "mvn clean test -Dcucumber.filter.tags=\"${TAG}\""
                //}
            }
        }
        stage('Allure Report Generation'){
            steps{
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}