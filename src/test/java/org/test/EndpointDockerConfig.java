package org.test;

// import java.util.Collections;

import com.consol.citrus.docker.client.DockerClient;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
// import com.consol.citrus.xml.namespace.NamespaceContextBuilder;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointDockerConfig extends EndpointConfig {

    @Bean
    public DockerClient dockerClient() {
        return CitrusEndpoints.docker().client().url("unix:///run/docker.sock").build();
    }
}
