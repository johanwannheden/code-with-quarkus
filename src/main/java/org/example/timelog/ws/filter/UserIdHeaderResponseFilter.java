package org.example.timelog.ws.filter;

import org.example.timelog.CallContext;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class UserIdHeaderResponseFilter implements ContainerResponseFilter {

    @Inject
    CallContext callContext;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        if (callContext != null && callContext.getCurrentUserId() != null) {
            callContext.resetCurrentUserId();
        }
    }
}
