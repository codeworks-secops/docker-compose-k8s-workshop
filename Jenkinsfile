pipeline {

    agent any

    stages {

        stage("Building docker image") {

            steps {

                echo "Building docker image ..."
                sh 'docker -v'

            }

        }

         stage("Push docker image") {

            steps {

                echo "Pushing docker image ..."

            }

        }

        stage("Clean docker image") {

            steps {

                echo "Cleaning docker image ..."
            }

        }

    }

}