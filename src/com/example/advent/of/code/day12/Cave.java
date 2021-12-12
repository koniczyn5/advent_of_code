package com.example.advent.of.code.day12;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cave {

    private String name;
    private boolean bigCave;
    private Set<Cave> connections;

    Cave(String name) {
        this.name = name;
        bigCave = name.equals(name.toUpperCase());
        connections = new HashSet<>();
    }

    String getName() {
        return name;
    }

    boolean isBigCave() {
        return bigCave;
    }

    Set<Cave> getConnections() {
        return connections;
    }

    void addConnection(Cave cave) {
        connections.add(cave);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave cave = (Cave) o;
        return name.equals(cave.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
