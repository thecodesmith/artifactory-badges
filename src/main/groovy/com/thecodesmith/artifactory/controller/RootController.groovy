package com.thecodesmith.artifactory.controller

import com.thecodesmith.artifactory.config.ArtifactoryConfig
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.inject.Inject

@CompileStatic
@Controller('/')
class RootController {

    @Inject ArtifactoryConfig config

    @Get('/')
    HttpResponse index() {
        HttpResponse.ok(status: 200)
    }

    @Get('/config')
    HttpResponse config() {
        HttpResponse.ok(host: config.host, port: config.port)
    }
}
