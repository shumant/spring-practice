package com.example.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class LogoutListener implements ApplicationListener<HttpSessionDestroyedEvent> {
    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent event) {
//        event.getSecurityContexts().forEach(this::logout);
        logout(new SecurityContextImpl());
    }

    private void logout(SecurityContext context) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Logout!");
        System.out.println("----------------------------------------------------------------------------");

    }
}
