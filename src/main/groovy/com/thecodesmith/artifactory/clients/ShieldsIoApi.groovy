package com.thecodesmith.artifactory.clients

import groovy.transform.CompileStatic
import io.micronaut.http.annotation.Get

@CompileStatic
interface ShieldsIoApi {

    @Get('/badge/{label}-{message}-{color}')
    String getBadge(String label, String message, String color)
}