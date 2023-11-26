package ma.projet.config;

import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.beans.User;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import ma.projet.service.UserService;


public class TestEmployeService {
    public static void main(String[] args) {
        EmployeService es = new EmployeService();
        ServiceService ss = new ServiceService();
        UserService us = new UserService();
        
        es.create(new Employe("Ouaday", "Abderrahmane", null, null));
        ss.create(new Service("Informatique"));
        us.create(new User("od", "pass"));
    }
}
