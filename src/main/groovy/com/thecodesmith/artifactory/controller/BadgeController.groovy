package com.thecodesmith.artifactory.controller

import com.thecodesmith.artifactory.clients.ArtifactoryClient
import com.thecodesmith.artifactory.services.BadgeService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

import javax.inject.Inject

import static com.thecodesmith.artifactory.util.Constants.VERSION_NOT_FOUND

@Slf4j
@CompileStatic
@Controller('/badge')
class BadgeController {

    @Inject ArtifactoryClient artifactoryClient
    @Inject BadgeService badgeService

    @Get('/{repo}/{groupId}/{artifactId}')
    @Produces('image/svg+xml;charset=utf-8')
    HttpResponse<String> getBadge(String repo, String groupId, String artifactId) {
        try {
            log.info "Finding version for '$groupId:$artifactId' in $repo"
            def artifactoryStart = now()
            def version = artifactoryClient.findVersion(groupId, artifactId, repo) ?: VERSION_NOT_FOUND
            def artifactoryTime = now() - artifactoryStart

            log.info "Latest version for '$repo/$groupId:$artifactId': $version"
            def shieldsStart = now()
            def badge = badgeService.getVersionBadge(version)
            def shieldsTime = now() - shieldsStart

            log.info "Request performance: Artifactory: $artifactoryTime, shields.io: $shieldsTime"
            if (!badge) {
                throw new RuntimeException('Unable to acquire badge from https://shields.io: response is empty')
            }

            log.info "Returning badge: $badge"
            return HttpResponse.ok(badge)
        } catch (e) {
            log.error('Unable to generate badge', e)
            return HttpResponse.ok(badgeService.getNotFoundBadge())
        }
    }

    protected static Long now() {
        new Date().time
    }
}
