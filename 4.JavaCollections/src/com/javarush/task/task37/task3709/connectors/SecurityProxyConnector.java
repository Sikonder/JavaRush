package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

/**
 * Created by Sikonder on 12.08.2017.
 */
public class SecurityProxyConnector implements Connector{
    SecurityChecker securityChecker;
    SimpleConnector simpleConnector;
    private String resourceString;

    public SecurityProxyConnector(String resourceString) {
        this.resourceString = resourceString;
        simpleConnector = new SimpleConnector(resourceString);
        securityChecker = new SecurityCheckerImpl();
    }

    @Override
    public void connect() {
        if(securityChecker.performSecurityCheck()){
            simpleConnector.connect();
        }
    }
}
