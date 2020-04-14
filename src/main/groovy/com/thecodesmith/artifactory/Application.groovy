package com.thecodesmith.artifactory

import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Value
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic

@Slf4j
@CompileStatic
class Application implements ApplicationEventListener<ServiceStartedEvent> {

    @Value('${artifactory.host}')
    String artifactoryHost

    static void main(String[] args) {
        Micronaut.run(Application)
    }

    @Override
    void onApplicationEvent(ServiceStartedEvent event) {
        log.info "Serving badges for Artifactory at $artifactoryHost"
    }
}