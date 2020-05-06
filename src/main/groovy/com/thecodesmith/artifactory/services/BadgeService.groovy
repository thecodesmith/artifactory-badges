package com.thecodesmith.artifactory.services

import com.thecodesmith.artifactory.clients.ShieldsIoClient
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.client.exceptions.HttpClientResponseException

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
@CompileStatic
class BadgeService {

    @Inject ShieldsIoClient client

    String getBadge(String label, String message, String color) {
        try {
            client.getBadge(label, escaped(message), color)
        } catch (HttpClientResponseException e) {
            log.error "Response from Shields client: $e.status.code - $e.status.reason", e
            if (e.response.body.present && isSvg(e.response.body() as String)) {
                log.info 'Response body contains SVG, returning it'
                return e.response.body()
            }
            errorBadge
        }
    }

    String escaped(String version) {
        version.replaceAll('-', '--')
               .replaceAll('_', '__')
               .replaceAll(' ', '_')
    }

    String getNotFoundBadge() {
        this.getClass().getResourceAsStream('/badges/not-found.svg').text
    }

    String getErrorBadge() {
        this.getClass().getResourceAsStream('/badges/error.svg').text
    }

    boolean isSvg(String text) {
        text.startsWith('<svg ')
    }
}
