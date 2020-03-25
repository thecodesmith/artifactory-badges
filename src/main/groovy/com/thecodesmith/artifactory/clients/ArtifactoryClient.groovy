package com.thecodesmith.artifactory.clients

import groovy.transform.CompileStatic
import io.micronaut.http.client.annotation.Client

@CompileStatic
@Client('${artifactory.host}:${artifactory.port}')
interface ArtifactoryClient extends ArtifactoryApi {

}