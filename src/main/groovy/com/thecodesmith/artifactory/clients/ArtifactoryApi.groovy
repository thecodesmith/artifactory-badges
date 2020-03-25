package com.thecodesmith.artifactory.clients

import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@CompileStatic
interface ArtifactoryApi {

    @Get('/artifactory/api/search/latestVersion?a={a}&g={g}&repos={repos}')
    @Consumes(MediaType.TEXT_PLAIN)
    String findVersion(@QueryValue('g') String groupId, @QueryValue('a') String artifactId, @QueryValue('repos') String repos)
}