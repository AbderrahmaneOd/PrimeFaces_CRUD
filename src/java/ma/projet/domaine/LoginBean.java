/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ma.projet.beans.Employe;
import ma.projet.service.EmployeService;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author hp
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    private EmployeService employeService;
    private String username;
    private String password;
    private boolean showInvalidCredentials = false;

    public void checkCredentials() {
        if (!isValidCredentials()) {
            showInvalidCredentials = true;
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid email or password"));
        } else {
            showInvalidCredentials = false;
            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/faces/web/service/serviceForm.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidCredentials() {
        List<Employe> employes;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes = session.createQuery("from Employe where username = :username AND password = :password")
            .setParameter("username", username)
            .setParameter("password", password)
            .list();
        session.getTransaction().commit();
        session.close();

        return !employes.isEmpty();
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

    public boolean isShowInvalidCredentials() {
        return showInvalidCredentials;
    }

    public void setShowInvalidCredentials(boolean showInvalidCredentials) {
        this.showInvalidCredentials = showInvalidCredentials;
    }
}
