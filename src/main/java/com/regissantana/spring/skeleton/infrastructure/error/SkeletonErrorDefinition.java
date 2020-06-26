package com.regissantana.spring.skeleton.infrastructure.error;

public enum SkeletonErrorDefinition implements ErrorDefinition{

    ZIP_CODE_MANDATORY("RS-SK-01", "o CEP é um campo obrigatório."),
    ;

    private String code;
    private String message;

    SkeletonErrorDefinition(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
