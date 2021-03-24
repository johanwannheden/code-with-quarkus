package org.example.timelog.user.service;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.example.timelog.reporting.model.UserEntity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager em;

    public UserEntity verifyUser(String tokenId, String email) throws FirebaseAuthException {
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }

        if (!ProfileManager.getLaunchMode().isDevOrTest()) {
            if (tokenId == null) {
                throw new IllegalArgumentException("Token must not be null");
            }
            FirebaseAuth instance = FirebaseAuth.getInstance();
            var token = instance.verifyIdToken(tokenId);

            if (!token.isEmailVerified()) {
                throw new IllegalArgumentException("Invalid email address: address is not verified");
            }

            if (!Objects.equals(email, token.getEmail())) {
                throw new IllegalArgumentException("Invalid email address: does not match address of token");
            }
        }

        var query = em.createQuery("select u from UserEntity u where u.email = :email");
        query.setParameter("email", email);
        try {
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalArgumentException("Invalid email address: user unknown");
        }
    }

    public boolean verifyEmailAddressExists(String email) {
        var query = em.createQuery("" //
                                   + "select "//
                                   + "  case when (count(1) > 0) then true "//
                                   + "  else false end "//
                                   + "from UserEntity u "//
                                   + "where u.email = :email");
        query.setParameter("email", email);
        return Boolean.TRUE.equals(query.getSingleResult());
    }

    public UserEntity findUser(String userId) {
        return Optional.ofNullable(em.find(UserEntity.class, userId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));
    }
}
