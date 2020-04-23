package com.thecodesmith.artifactory.clients

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher

@Slf4j
@Filter('/**')
@CompileStatic
class GenericClientFilter implements HttpClientFilter {
    
    @Override
    Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        log.info "HTTP request $request.method $request.uri"
        chain.proceed(request)
    }
}
