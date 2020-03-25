package com.thecodesmith.artifactory.services

import com.thecodesmith.artifactory.clients.ShieldsIoClient
import groovy.transform.CompileStatic

import javax.inject.Inject
import javax.inject.Singleton

@CompileStatic
@Singleton
class BadgeService {

    @Inject ShieldsIoClient client

    String getVersionBadge(String version) {
        getBadge('version', version, 'blue')
    }

    String getBadge(String label, String message, String color) {
        client.getBadge(label, message, color)
    }

    String getNotFoundBadge() {
        this.getClass().getResourceAsStream('/not-found.svg').text
    }
}
