package pl.alergeek.model;

import java.io.InputStream;

public interface Task {

    default String solve() {
        throw new RuntimeException("Not implemented method!");
    }

    void setFile(InputStream file);
}
