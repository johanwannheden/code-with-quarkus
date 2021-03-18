package org.example.timelog.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;

@ApplicationScoped
public class FirebaseInitializer {

    @ConfigProperty(name = "google.application.credentials")
    String applicationCredentialsFilePath;

    private static final Logger LOGGER = Logger.getLogger(FirebaseInitializer.class.getSimpleName());

    void onStart(@Observes StartupEvent event) {
        try {
            initializeFirebase();
        } catch (IOException | RuntimeException e) {
            LOGGER.fatal("Firebase initialization failed", e);
            Quarkus.asyncExit();
        }
    }

    private void initializeFirebase() throws IOException {
        try {
            FirebaseApp.getInstance();
            return;
        } catch (IllegalStateException e) {
            // ignore, app not yet initialized
        }

        var stream = FirebaseInitializer.class.getResourceAsStream(applicationCredentialsFilePath);
        if (stream == null) {
            throw new IllegalArgumentException("Invalid path for Firebase token file: " + applicationCredentialsFilePath);
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(stream))
                .setProjectId("timelog-301819")
                .build();

            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        System.out.println(firebaseApp);
    }

}
