/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author eduar
 */
@ManagedBean
@RequestScoped
public class CorreoController {

    private String to;
    private String from;
    private String subject;
    private String body;
    private String username;
    private String password;
    private String smtp;
    private int port;
    private int key;
    public final String correoCuerpo = "Abre el siguiente link para activar tu cuenta de Cella:: confirmacion.xhtml?";
    /**
     * Creates a new instance of CorreoController
     */
     
    /*
    user = cella.unam.is@gmail.com 
    pass = cella123+
    */
    public CorreoController() {
        this.key = 0;
        this.to = null;
        this.from = "eduardo_rubio_lezama@ciencias.unam.mx";
        //this.from = null;
        this.subject = "activa tu cuenta Cella";
        //this.subject = null;
       //this.body = null;
       this.username = "eduardo_rubio_lezama@ciencias.unam.mx";
       //this.username = null;
        this.password = "angiesismine";
        //this.password = null;
        this.smtp = "smtp.gmail.com";
        //this.smtp = null;
        this.port = 587;//25 Puerto 587 para que habilite el correo Gmail
        this.body = "Abre el siguiente link para activar tu cuenta de Cella:: confirmacion.xhtml?";
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public void validateEmail(FacesContext context, UIComponent toValidate, Object value){
        String message = "";
        String email = (String) value;
        if (email == null || (email.equals(""))) {
            ((UIInput) toValidate).setValid(false);
            message = "email requerido";
            context.addMessage(toValidate.getClientId(context), new FacesMessage(message));
        }else if ((!email.contains("@")) || (!email.contains("."))){
            ((UIInput) toValidate).setValid(false);
            message = "email invalido";
            context.addMessage(toValidate.getClientId(context), new FacesMessage(message));   
        }
    }
    
    public String submitEmail(){
       
       
        Properties props = null;
        Session session = null;
        MimeMessage message = null;
        Address fromAddress = null;
        Address toAddress = null;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                
                return new PasswordAuthentication(username, password);
            }
        });

        message = new MimeMessage(session);
        try {
          
            message.setContent(getBody(), "text/plain");
            
            message.setSubject(getSubject());
            fromAddress = new InternetAddress(getFrom());
            message.setFrom(fromAddress);
            toAddress = new InternetAddress(getTo());
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(this.smtp, this.port, this.username, this.password);
                if (!transport.isConnected()) {
                      return "emailFalse";
                }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException me) {
            me.toString();
            return "emailFal";
        }
        return "emailOk";
    }
   
}
