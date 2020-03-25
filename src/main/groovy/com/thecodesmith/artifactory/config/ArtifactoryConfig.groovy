package com.thecodesmith.artifactory.config

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.ConfigurationProperties

@CompileStatic
@ConfigurationProperties('artifactory')
class ArtifactoryConfig {
    String host
    String port
    String user
    String token
}
