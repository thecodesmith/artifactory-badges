package com.thecodesmith.artifactory.clients

import com.thecodesmith.artifactory.util.Constants
import io.micronaut.http.annotation.QueryValue
import io.micronaut.retry.annotation.Fallback

@Fallback
class ArtifactoryFallback implements ArtifactoryApi {

    @Override
    String findVersion(@QueryValue('g') String groupId, @QueryValue('a') String artifactId, @QueryValue('repos') String repos) {
        Constants.VERSION_NOT_FOUND
    }
}
