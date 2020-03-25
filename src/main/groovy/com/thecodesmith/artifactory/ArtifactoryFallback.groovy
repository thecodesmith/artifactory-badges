package com.thecodesmith.artifactory

import io.micronaut.http.annotation.QueryValue
import io.micronaut.retry.annotation.Fallback

import static com.thecodesmith.util.Constants.VERSION_NOT_FOUND

@Fallback
class ArtifactoryFallback implements ArtifactoryApi {

    @Override
    String findVersion(@QueryValue('g') String groupId, @QueryValue('a') String artifactId, @QueryValue('repos') String repos) {
        VERSION_NOT_FOUND
    }
}
