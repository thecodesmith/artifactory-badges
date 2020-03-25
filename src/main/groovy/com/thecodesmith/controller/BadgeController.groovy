package com.thecodesmith.controller

import com.thecodesmith.artifactory.ArtifactoryClient
import com.thecodesmith.generator.BadgeGenerator
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

import javax.imageio.ImageIO
import javax.inject.Inject

import static com.thecodesmith.util.Constants.VERSION_NOT_FOUND

@Slf4j
@Controller('/badge')
class BadgeController {

    @Inject ArtifactoryClient client
    @Inject BadgeGenerator badgeGenerator

    @Get('/')
    HttpStatus index() {
        HttpStatus.OK
    }

    @Get('/{repo}/{groupId}/{artifactId}')
    @Produces('image/png')
    byte[] getBadge(String repo, String groupId, String artifactId) {
        log.info "Finding version for '$groupId:$artifactId' in $repo"
        def result = client.findVersion(groupId, artifactId, repo) ?: VERSION_NOT_FOUND

        log.info "Results: $result"
        def badge = badgeGenerator.generate(artifactId, result, result != VERSION_NOT_FOUND)

        def outputStream = new ByteArrayOutputStream()
        ImageIO.write(badge, 'png', outputStream)
        outputStream.toByteArray()
    }
}