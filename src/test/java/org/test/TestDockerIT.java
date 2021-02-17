package org.test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.docker.client.DockerClient;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
// import com.consol.citrus.report.TestListeners;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;


public class TestDockerIT extends AbstractDocker {

    @Autowired
    private DockerClient dockerClient;

    @BeforeTest
    public void checkDocker() {
        assertThat(dockerClient, not(nullValue()));
        // needed?
        // docker().client(dockerClient).inspectContainer(System.getProperty("docker.app.image.name"))
        //     .validateCommandResult((container, context) -> assertThat("", container.getState().getRunning()));
    }

    @Test
    @CitrusTest
    public void testSystemProperties() {
        // String s = System.getProperty("docker.alias");
        // Logger l = LoggerFactory.getLogger(TestDockerIT.class);
        // l.error("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA '{}'", s);
        assertThat(System.getProperty("docker.app.image.name"), not(allOf(isEmptyString(), equalTo("null"))));
        // assertThat(, equalTo("web-app"));
        // assertThat(System.getProperty("abcd"), equalTo("ANC"));
    }

    // @Test
    // @CitrusTest
    // public void testDeploymentState() {
    // }


}
