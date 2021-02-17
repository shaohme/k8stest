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
public class EndpointConfig {

    @Bean
    public HttpClient client() {
        final String testServerPortProp = "test.server.port";

        int testPort = -1;
        try {
            testPort = Integer.parseInt(System.getProperty(testServerPortProp));
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("%s has illegal argument", testServerPortProp), e);
        }

        String hostname = System.getProperty("test.server.hostname");
        if (StringUtils.isEmpty(hostname) || hostname.equals("null")) {
            throw new IllegalArgumentException(String.format("invalid http hostname '%s'", hostname));
        }
        return CitrusEndpoints.http().client().requestUrl(String.format("http://%s:%d", hostname, testPort)).build();
    }

    // @Bean
    // public NamespaceContextBuilder namespaceContextBuilder() {
    // final NamespaceContextBuilder namespaceContextBuilder = new
    // NamespaceContextBuilder();
    // namespaceContextBuilder.setNamespaceMappings(Collections.singletonMap("xh",
    // "http://www.w3.org/1999/xhtml"));
    // return namespaceContextBuilder;
    // }
}
