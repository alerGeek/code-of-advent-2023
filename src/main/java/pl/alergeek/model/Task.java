package pl.alergeek.model;

import java.util.List;

public interface Task {

    default String solve(List<String> input) {
        throw new RuntimeException("Not implemented method!");
    }
}
