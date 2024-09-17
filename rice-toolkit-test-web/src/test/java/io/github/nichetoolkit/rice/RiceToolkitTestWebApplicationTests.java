package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RiceToolkitTestWebApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(IdentityUtils.generateString());
        // 1835974778298056704
        String password = "123456";
        String encryptPassword = ShaWorker.encrypts(password);
        System.out.println(encryptPassword);
        // 941121347CFDD1A334FABCA970C1B9137B5E105470AC55C2B48A1B59492661AD
    }

}
