package org.example.demo1;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

@Path("/email")
public class EmailResource {

    @EJB
    private EmailService emailService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response sendEmailWithAttachment(
            @FormDataParam("to") String to,
            @FormDataParam("subject") String subject,
            @FormDataParam("body") String body,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataBodyPart fileDetail) {

        try {
            emailService.sendEmailWithAttachment(to, subject, body, fileInputStream, fileDetail.getContentDisposition().getFileName());
            return Response.ok("Email envoyé avec succès avec pièce jointe").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Échec de l'envoi de l'email : " + e.getMessage()).build();
        }
    }
}
