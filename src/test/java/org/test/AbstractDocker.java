package org.test;

import com.consol.citrus.docker.client.DockerClient;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.IHookCallBack;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.*;

public class AbstractDocker extends TestNGCitrusTestDesigner {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(AbstractDocker.class);

    @Autowired
    private DockerClient dockerClient;

    /** Docker connection state, checks connectivity only once per test run */
    private static boolean connected = false;

    @BeforeSuite(alwaysRun = true)
    public void checkDockerEnvironment() {
        assertThat(dockerClient, not(nullValue()));

        try {
            Future<Boolean> future = Executors.newSingleThreadExecutor().submit(() -> {
                dockerClient.getEndpointConfiguration().getDockerClient().pingCmd().exec();
                return true;
            });

            future.get(5000, TimeUnit.MILLISECONDS);
            connected = true;
        } catch (Exception e) {
            log.warn("Skipping Docker test execution as no proper Docker environment is available on host system!", e);
        }
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        if (connected) {
            super.run(callBack, testResult);
        }
    }
}
