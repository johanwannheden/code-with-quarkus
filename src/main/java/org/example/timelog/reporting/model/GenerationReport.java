package org.example.timelog.reporting.model;

import java.io.InputStream;

public class GenerationReport {
    private final InputStream data;
    private final String filename;

    private GenerationReport(Builder builder) {
        data = builder.data;
        filename = builder.filename;
    }

    public InputStream getData() {
        return data;
    }

    public String getFilename() {
        return filename;
    }

    public static final class Builder {
        private InputStream data;
        private String filename;

        public Builder withData(InputStream data) {
            this.data = data;
            return this;
        }

        public Builder withFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public GenerationReport build() {
            return new GenerationReport(this);
        }
    }
}
