package controller;

import model.User;
import model.validation.Notification;
import service.authentication.AuthenticationService;
import view.login.LoginView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;

    private EmployeeController employeeController;
    private AdminController adminController;

    public LoginController(LoginView loginView,EmployeeController employeeController, AdminController adminController, AuthenticationService authenticationService){
        this.loginView = loginView;
        this.authenticationService = authenticationService;

        this.employeeController = employeeController;
        this.adminController = adminController;

        loginView.setLoginButtonListener(new LoginButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
            }
            else {
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
            }
        }
    }
}
