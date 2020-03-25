package com.thecodesmith.artifactory

import io.micronaut.http.client.annotation.Client

@Client('${artifactory.host}:${artifactory.port}')
interface ArtifactoryClient extends ArtifactoryApi {

}