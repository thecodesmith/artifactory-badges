package com.thecodesmith.artifactory.clients

import groovy.transform.CompileStatic
import io.micronaut.http.client.annotation.Client

@CompileStatic
@Client('${shields.host}')
interface ShieldsIoClient extends ShieldsIoApi {

}