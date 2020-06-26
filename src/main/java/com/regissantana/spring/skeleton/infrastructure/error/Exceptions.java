package com.regissantana.spring.skeleton.infrastructure.error;

public final class Exceptions {

    public static void asBusinessException(final ErrorDefinition orderErrorDefinition) {
        //TODO: Create custom exception
        throw new RuntimeException(orderErrorDefinition.getMessage());
    }

}
