package org.example.demo1;

import jakarta.ws.rs.FormParam;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

public class EmailRequestForm {

    @FormParam("to")
    private String to;

    @FormParam("subject")
    private String subject;

    @FormParam("body")
    private String body;

    @FormDataParam("attachment")
    private InputStream attachment;

    @FormDataParam("attachment")
    private String attachmentFileName;

    // Getters and setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public InputStream getAttachment() {
        return attachment;
    }

    public void setAttachment(InputStream attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }
}
