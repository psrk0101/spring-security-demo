package com.study.security.model.entity.enm;

public enum Role {
    MEMBER("ROLE_MEMBER")
    ,ADMIN("ROLE_ADMIN")
    ;

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
