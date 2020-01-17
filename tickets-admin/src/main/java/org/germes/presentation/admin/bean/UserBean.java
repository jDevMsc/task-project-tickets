package org.germes.presentation.admin.bean;

/**
 * user authentication data
 */
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class UserBean implements Serializable {

    private String username;
    private String password;

    public void login() {
        /**
         * Stores state and performs security operations for the current user
         */
        Subject subject = SecurityUtils.getSubject();
        /**
         * Stores everything that the user entered
         */
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword());

        try {
            subject.login(token);

            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

        } catch (UnknownAccountException ex) {
            facesError("Unknown account");
            System.out.println(ex.getMessage() + " " + ex);
        } catch (IncorrectCredentialsException ex) {
            facesError("Wrong password");
            System.out.println(ex.getMessage() + " " + ex);
        } catch (LockedAccountException ex) {
            facesError("Locked account");
            System.out.println(ex.getMessage() + " " + ex);
        } catch (AuthenticationException | IOException ex) {
            facesError("Unknown error: " + ex.getMessage());
            System.out.println(ex.getMessage() + " " + ex);
        } finally {
            token.clear();
        }
    }

    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
}