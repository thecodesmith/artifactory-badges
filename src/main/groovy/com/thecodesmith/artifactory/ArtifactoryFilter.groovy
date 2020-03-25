package com.thecodesmith.artifactory

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher

import javax.inject.Inject

@Slf4j
@CompileStatic
@Filter('/artifactory/api/**')
class ArtifactoryFilter implements HttpClientFilter {

    @Inject ArtifactoryConfig config

    @Override
    Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        chain.proceed(request.basicAuth(config.user.name, config.user.token))
    }
}
