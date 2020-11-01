package com.simle.ioc.model;

import com.simle.ioc.enums.DirType;

public class Dir {
    private final String dir;
    private final DirType type;

    public Dir(String dir, DirType type) {
        this.dir = dir;
        this.type = type;
    }

    public String getDir() {
        return dir;
    }

    public DirType getType() {
        return type;
    }
}
