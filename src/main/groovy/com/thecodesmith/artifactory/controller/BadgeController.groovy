package com.thecodesmith.artifactory.controller

import com.thecodesmith.artifactory.clients.ArtifactoryClient
import com.thecodesmith.artifactory.services.BadgeService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue

import javax.annotation.Nullable
import javax.inject.Inject

import static com.thecodesmith.artifactory.util.Constants.VERSION_NOT_FOUND

@Slf4j
@CompileStatic
@Controller('/badge')
class BadgeController {

    static final String DEFAULT_LABEL = 'version'
    static final String DEFAULT_COLOR = 'blue'

    @Inject ArtifactoryClient artifactoryClient
    @Inject BadgeService badgeService

    @Value('${artifactory.host}')
    String artifactoryHost

    @Value('${shields.host}')
    String shieldsHost

    @Get('/{repo}/{groupId}/{artifactId}')
    @Produces('image/svg+xml;charset=utf-8')
    HttpResponse<String> getBadge(String repo,
                                  String groupId,
                                  String artifactId,
                                  @QueryValue Optional<String> label,
                                  @QueryValue Optional<String> color) {
        try {
            log.info "Finding version for '$groupId:$artifactId' in $repo"
            def artifactoryStart = now()
            def version = artifactoryClient.findVersion(groupId, artifactId, repo) ?: VERSION_NOT_FOUND
            def artifactoryTime = now() - artifactoryStart

            log.info "Latest version for '$repo/$groupId:$artifactId': $version"
            def shieldsStart = now()
            def badge = badgeService.getBadge(label.orElse(DEFAULT_LABEL), version, color.orElse(DEFAULT_COLOR))
            def shieldsTime = now() - shieldsStart

            log.info """|Request performance:
                        |  ${formatResponseTime(artifactoryTime)} - Artifactory ($artifactoryHost)
                        |  ${formatResponseTime(shieldsTime)} - Shields: ($shieldsHost)""".stripMargin()
            if (!badge) {
                throw new RuntimeException("Unable to acquire badge from $shieldsHost: response is empty")
            }

            log.info "Returning badge: $badge"
            return HttpResponse.ok(badge)
        } catch (e) {
            log.error('Unable to generate badge', e)
            return HttpResponse.ok(badgeService.errorBadge)
        }
    }

    protected static Long now() {
        new Date().time
    }

    protected static String formatResponseTime(Long duration) {
        "$duration ms".padLeft(7)
    }
}
