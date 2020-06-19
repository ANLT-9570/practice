package com.dg.main.serviceImpl.setting;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {
    @Autowired
    Environment environment;
    public void getEnv(){
        System.out.println(environment.getActiveProfiles());
        System.out.println(environment.getDefaultProfiles());
        System.out.println(environment.getProperty("server.port"));
    }

}
