package com.thecodesmith.artifactory.config

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.ConfigurationProperties

@CompileStatic
@ConfigurationProperties('artifactory')
class ArtifactoryConfig {

    String host
    String port
    User user

    @ConfigurationProperties('user')
    static class User {
        String name
        String token
    }
}
