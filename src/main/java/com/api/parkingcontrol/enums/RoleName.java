package com.api.parkingcontrol.enums;

import java.util.ArrayList;
import java.util.List;

public enum RoleName {
    ROLE_ADMIN, ROLE_USER;

    public static List<String> enumRoleNames() {
        RoleName[] roleNamesArray = RoleName.values();
        List<String> enumRoleNames = new ArrayList<>();
        for (RoleName roleName : roleNamesArray) {
            enumRoleNames.add(roleName.toString());
        }
        return enumRoleNames;
    }
}
