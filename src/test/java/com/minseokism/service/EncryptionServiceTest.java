package com.minseokism.service;

import com.minseokism.App;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Minseokism on 2017-08-19.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class EncryptionServiceTest {
    @Autowired
    EncryptionService service;

    @Test
    public void encriptionTest() {
        String key = "hi";
        String id = "myName";
        String encId = null;
        String decId = null;
        try {
            encId = service.encrypt(key,id);
            decId = service.decrypt(key, encId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(encId);
        Assert.assertEquals(id, decId);
    }
}
