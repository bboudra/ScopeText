package org.scopetext.model.javabean;

import org.scopetext.model.javabean.type.MessageType;

/**
 * Java bean that contains all properties of a Message.
 * Created by john.qualls on 9/24/2016.
 */
public class Message {
    MessageType type;
    String regularExpression;

    /**
     * Message constructor
     * @param type The type of message, only text messages are allowed at the moment.
     * @param regularExpression The regular expression used to search incoming messages.
     */
    public Message(MessageType type, String regularExpression) {
        this.type = type;
        this.regularExpression = regularExpression;
    }

    public MessageType getType() {
        return type;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }
}
