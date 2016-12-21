package org.scopetext.util;

import org.scopetext.model.javabean.type.MessageType;

/**
 * Contains utility methods for the enums in org.scopetext.model.javabean.type.
 */
public class JavaBeanTypeUtil {

    /**
     * Retrieves a MessageType based on it's String representation.
     *
     * @return The MessageType, or null if the String representation referenced no MessageType.
     * @Param stringRepresentation Used to retrieve the MessageType.
     */
    public static MessageType getMessageType(String stringRepresentation) {
        MessageType messageType = null;

        return messageType;
    }

    /**
     * Helper for retrieving a type from its values.
     *
     * @param stringRepresentation Retrieves a type if the representation matches a value.
     * @param values The values to search.
     * @return The matching type, or null if there is no match.
     */
    private static Object getTypeFromValues(String stringRepresentation, Object[] values) {
/*        if(stringRepresentation != null && !stringRepresentation.isEmpty()){
            for(MessageType type : MessageType.values()) {
                if(type.getStringRepresentation().equals(stringRepresentation))
                    //messageType = type;
            }
        }*/
        return null;
    }
}