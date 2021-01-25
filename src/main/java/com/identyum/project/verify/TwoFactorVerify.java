package com.identyum.project.verify;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.verify.VerifyClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorVerify {
    public NexmoClient nexmoClient(Environment environment) {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey(environment.getProperty("nexmo.api.key"))
                .apiSecret(environment.getProperty("nexmo.api.secret"))
                .build();

        return client;
    }

    public VerifyClient nexmoVerifyClient(NexmoClient nexmoClient) {
        return nexmoClient.getVerifyClient();
    }
}
