package org.test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.docker.client.DockerClient;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
// import com.consol.citrus.report.TestListeners;
import com.consol.citrus.message.MessageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestControllerIT extends TestNGCitrusTestDesigner {

    @Autowired
    private HttpClient client;

    @Test
    @CitrusTest
    public void get() {
        http().client(client).send().post("/").accept(MediaType.TEXT_HTML_VALUE).payload("testing");

        http().client(client).receive().response(HttpStatus.OK).messageType(MessageType.PLAINTEXT).payload("postgres");

    }

}
