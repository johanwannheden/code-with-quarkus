package org.example.timelog.ws.filter;

import org.example.timelog.CallContext;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.util.Optional;

@Provider
@PreMatching
public class UserIdHeaderRequestFilter implements ContainerRequestFilter {

    @Inject
    CallContext callContext;

    private static final Logger LOGGER = Logger.getLogger(UserIdHeaderRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Optional.ofNullable(requestContext.getHeaderString("x-user-id"))
                .ifPresent(this::handleUserId);
    }

    private void handleUserId(String user) {
        if (callContext != null) {
            callContext.setCurrentUserId(user);
        }
        LOGGER.info("Processing request for user " + user);
    }
}
