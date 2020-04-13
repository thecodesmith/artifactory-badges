package com.thecodesmith.artifactory.controller

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@CompileStatic
@Controller('/')
class RootController {

    @Get('/')
    HttpResponse index() {
        HttpResponse.ok(status: 200)
    }
}
