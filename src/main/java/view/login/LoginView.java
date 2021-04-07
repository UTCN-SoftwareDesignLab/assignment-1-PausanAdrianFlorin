/*
 * Created by JFormDesigner on Mon Mar 29 19:58:31 EEST 2021
 */

package view.login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Pausan Adrian
 */
public class LoginView extends JFrame {
    public LoginView() {
        initComponents();
    }

    private void buttonLoginActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void buttonCancelActionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pausan Adrian-Florin
        label1 = new JLabel();
        label3 = new JLabel();
        buttonLogin = new JButton();
        buttonCancel = new JButton();
        textUserName = new JTextField();
        textPassword = new JTextField();
        label2 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("BANK OF P\u0102U\u0218ANIA");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label3 ----
        label3.setText("Password:");

        //---- buttonLogin ----
        buttonLogin.setText("Login");
        buttonLogin.addActionListener(e -> buttonLoginActionPerformed(e));

        //---- buttonCancel ----
        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(e -> buttonCancelActionPerformed(e));

        //---- label2 ----
        label2.setText("User name:");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textUserName, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(buttonLogin, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonCancel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(textPassword))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(textUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(buttonCancel)
                        .addComponent(buttonLogin))
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pausan Adrian-Florin
    protected JLabel label1;
    protected JLabel label3;
    protected JButton buttonLogin;
    protected JButton buttonCancel;
    protected JTextField textUserName;
    protected JTextField textPassword;
    protected JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void setLoginButtonListener(ActionListener listener){
        buttonLogin.addActionListener(listener);
    }

    public String getUsername(){
        return textUserName.getText();
    }

    public String getPassword(){
        return textPassword.getText();
    }
}
