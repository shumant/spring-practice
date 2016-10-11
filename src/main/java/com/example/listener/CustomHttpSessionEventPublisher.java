package com.example.listener;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;

@Component
public class CustomHttpSessionEventPublisher extends HttpSessionEventPublisher {
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session destroyed!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Session created!");
    }
}
