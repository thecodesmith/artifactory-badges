# Artifactory Badge Service

_Pretty artifact version badges for Artifactory_

## Features

This microservice uses the [Artifactory REST
API](https://www.jfrog.com/confluence/display/JFROG/Artifactory+REST+API#ArtifactoryRESTAPI-ArtifactLatestVersionSearchBasedonLayout)
and [shields.io](https://shields.io) to generate version badges that look like
this: ![version badge example](https://img.shields.io/badge/version-1.2.3-blue).

The Artifactory API is used to find the latest version of a specified artifact,
and [shields.io](https://shields.io) generates the badge in SVG format.

## Usage

To run the service using the Docker image:

    docker run \
        -p 8080:8080 \
        -e ARTIFACTORY_HOST=<your artifactory server> \
        -e ARTIFACTORY_PORT=<port> \
        -e ARTIFACTORY_USER=<user> \
        -e ARTIFACTORY_TOKEN=<token> \
        thecodesmith/artifactory-badges

Then test it using the `/badge` endpoint on badge service API:

    curl localhost:8080/badge/<repo>/<groupId>/<artifactId>

To use the service in a Markdown document, use this syntax:

    ![Latest Version](http://localhost:8080/badge/<repo>/<groupId>/<artifactId>)

## Development

### Run the app

Run the project via Gradle:

    ./gradlew run

Build the app (compile and run tests):

    ./gradlew build

Create a runnable fat jar with all dependencies:

    ./gradlew shadowJar

The jar will be in `build/libs/artifactory-badges-<version>-all.jar` and can be
run like this:

    java -jar build/libs/artifactory-badges-<version>-all.jar

### Configuration

Set the Artifactory connection properties using one of Micronaut's
[configuration methods](https://docs.micronaut.io/latest/guide/config.html). The
simplest way is with environment variables or system properties. Below are the connection properties which need to be configured.

System Property     | Environment Variable | Example
---------------     | -------------------- | -------
`artifactory.host`  | `ARTIFACTORY_HOST`   | `https://artifactory.company.com`
`artifactory.port`  | `ARTIFACTORY_PORT`   | `443`
`artifactory.user`  | `ARTIFACTORY_USER`   | `my-service-account`
`artifactory.token` | `ARTIFACTORY_TOKEN`  | `iOiIyIiwid...`
