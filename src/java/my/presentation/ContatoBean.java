/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import Util.ServiceUtil;
import boundary.AbstractFacade;
import boundary.DetalheFacade;
import boundary.EspecieFacade;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Kamilla
 */
@ManagedBean(name="contatoBean")
@ViewScoped
public class ContatoBean{

    private String userName = "florasergipe.noreply@gmail.com";
    private String password = "5a4SRVy7Yk";
    private String assunto;
    private String mensagem;
    private String destinatario;
    private String remetente;
    private String emailRemetente;
    
    
    
    public ContatoBean() {
        
        
        
    }
      
   
    
           
    
    
    
    
     public void enviar() throws NoSuchProviderException, MessagingException{
    
        Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("florasergipe@gmail.com"));
			message.setSubject("Mensagem enviada pelo formulário de contato.");
			message.setText("Enviado por: "
				+ remetente + " - " + emailRemetente + "\n\n" + mensagem + "\n\n ATENÇÃO, RESPOSTAS DEVEM SER ESCRITAS COM O ENDEREÇO FORNECIDO PELA MENSAGEM, SE RESPONDEREM PELA FUNÇÂO DO GMAIL ELA NÃO IRÁ PARA O REMETENTE.");
 
			Transport.send(message);
                        ServiceUtil.addInfoMessage("Obrigado!","Mensagem enviada com sucesso!");
			
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
                        
		}    
}

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

     
    
}
    
    

