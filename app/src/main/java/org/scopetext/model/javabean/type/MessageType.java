package org.scopetext.model.javabean.type;

/**
 * Used to distinguish the incoming message type. Created by john.qualls on 10/10/2016.
 */
public enum MessageType {
    TEXT("TEXT");

    private final String stringRepresentation;

    MessageType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }
}
