package org.scopetext.util;

import junit.framework.Assert;

import org.junit.Test;
import org.scopetext.model.javabean.type.MessageType;

/**
 * Created by john.qualls on 10/15/2016.
 */
public class JavaBeanTypeUtilTest {

    @Test
    public void nullStringRepresentationTest() {
        Assert.assertNull(JavaBeanTypeUtil.getMessageType(null));
    }

    @Test
    public void emptyStringRepresentationTest() {
        Assert.assertNull(JavaBeanTypeUtil.getMessageType(""));
    }

    @Test
    public void incorrectStringRepresentationTest() {
        Assert.assertNull(JavaBeanTypeUtil.getMessageType("BLAH"));
    }

    @Test
    public void getMessageTypeTest() {
        MessageType expectedMessageType = MessageType.TEXT,
            actualMessageType = JavaBeanTypeUtil.getMessageType("TEXT");
        Assert.assertEquals(expectedMessageType, actualMessageType);
    }
}
