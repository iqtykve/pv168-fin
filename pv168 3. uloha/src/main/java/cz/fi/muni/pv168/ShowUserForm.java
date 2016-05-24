/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pv168;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Ondra
 */
public class ShowUserForm extends javax.swing.JFrame {
    private Account accPrivate;
    private AccountManager accManager;
    private PaymentManager payManager;
    /**
     * Creates new form ShowUserForm
     */
    public ShowUserForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        givenName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        birthName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        showPayment = new javax.swing.JButton();
        iD = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        active = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        sumAmount = new javax.swing.JLabel();
        deleteUser = new javax.swing.JButton();
        updateUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        accountNumber = new javax.swing.JTextField();
        error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        givenName.setText("name");
        givenName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                givenNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Given name:");

        jLabel4.setText("Birth name");

        birthName.setText("name");
        birthName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthNameActionPerformed(evt);
            }
        });

        jLabel5.setText("Account number");

        jLabel7.setText("ID");

        showPayment.setText("Show & create payments");
        showPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPaymentMouseClicked(evt);
            }
        });

        iD.setText("ID");

        jLabel9.setText("Active");

        active.setText("True");

        jLabel11.setText("Sum amount");

        sumAmount.setText("1.000,-");

        deleteUser.setText("Delete");
        deleteUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteUserMouseClicked(evt);
            }
        });
        deleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserActionPerformed(evt);
            }
        });

        updateUser.setText("Update");
        updateUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateUserMouseClicked(evt);
            }
        });

        jLabel1.setText("Information about user");

        jButton1.setText("<- back");
        jButton1.setActionCommand("<- back");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        accountNumber.setText("account number");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(error)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(givenName)
                                        .addComponent(birthName)
                                        .addComponent(accountNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                                    .addComponent(sumAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(active, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(iD, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(131, 131, 131)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(deleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(showPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(givenName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(birthName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(accountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(iD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(active))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(sumAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(error)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteUser)
                    .addComponent(updateUser)
                    .addComponent(showPayment))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void givenNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_givenNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_givenNameActionPerformed

    private void birthNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_birthNameActionPerformed

    private void deleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteUserActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

       List<Account> result = accManager.findAccountByName(accPrivate.getBirthName());
       this.setVisible(false);
       ChooseUserForm Cuf = new ChooseUserForm(result,accManager,payManager);
    }//GEN-LAST:event_jButton1MouseClicked

    private void deleteUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteUserMouseClicked
        accManager.deleteAccount(accPrivate);
        active.setText("Ne");
        deleteUser.setEnabled(false);
        updateUser.setEnabled(false);
    }//GEN-LAST:event_deleteUserMouseClicked

    private void showPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPaymentMouseClicked
        MainPaymentForm Mpf = new MainPaymentForm(accManager,payManager,accPrivate);
        this.setVisible(false);
        Mpf.setVisible(true);
    }//GEN-LAST:event_showPaymentMouseClicked

    private void updateUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateUserMouseClicked
        if(birthName.getText().length()>2 && givenName.getText().length()>2 && birthName.getText().length()>2 && accountNumber.getText().length()>5 && birthName.getText().length()>2){
            BigDecimal money = new BigDecimal(sumAmount.getText().replaceAll(",", ""));
            accPrivate.setAccountNumber(accountNumber.getText());
            accPrivate.setBirthName(birthName.getText());
            accPrivate.setGivenName(givenName.getText());
            accManager.updateAccount(accPrivate);
            error.setText("User has been edited");
        }else{
            error.setText("wrong format");
        }
    }//GEN-LAST:event_updateUserMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowUserForm().setVisible(true);
            }
        });
    }
    public ShowUserForm(Account acc, AccountManager accManager, PaymentManager payManager){
        accPrivate = acc;
        this.accManager = accManager;
        this.payManager = payManager;
        initComponents();
        jLabel1.setText("Information about user " + acc.getBirthName() + " " + acc.getGivenName());
        givenName.setText(acc.getGivenName());
        birthName.setText(acc.getBirthName());
        accountNumber.setText(acc.getAccountNumber());
        String wasDeleted;
        if(acc.getWasDeleted()==true){
            wasDeleted = "No";
            deleteUser.setEnabled(false);
            updateUser.setEnabled(false);
        }else{
            wasDeleted = "Yes";
        }
        active.setText(wasDeleted);
        iD.setText(acc.getId().toString());
        sumAmount.setText(acc.getSumAmount().toString());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNumber;
    private javax.swing.JLabel active;
    private javax.swing.JTextField birthName;
    private javax.swing.JButton deleteUser;
    private javax.swing.JLabel error;
    private javax.swing.JTextField givenName;
    private javax.swing.JLabel iD;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton showPayment;
    private javax.swing.JLabel sumAmount;
    private javax.swing.JButton updateUser;
    // End of variables declaration//GEN-END:variables
}
