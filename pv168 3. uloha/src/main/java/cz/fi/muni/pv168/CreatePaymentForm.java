
package cz.fi.muni.pv168;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultListModel;

public class CreatePaymentForm extends javax.swing.JFrame {
    private AccountManager accManager;
    private PaymentManager payManager;
    private Payment payment;
    private Account accPrivate;
    private List<Account> accUserList;
    private int indexChooseUser=-1;
    private boolean wrongReciever = false;
    
    public CreatePaymentForm() {
        initComponents();
    }
    public CreatePaymentForm(AccountManager accManager,PaymentManager payManager,Account acc){
        accPrivate = acc;
        this.accManager = accManager;
        this.payManager = payManager;
        initComponents();
        
        chooseUserList.setVisible(false);
        sender.setEditable(false);
        sender.setText(acc.getGivenName()+" "+acc.getBirthName());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        create = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        reciever = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        sender = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        chooseUserList = new javax.swing.JList<>();
        amount = new javax.swing.JTextField();
        message = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        error1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Create new payment");

        jLabel3.setText("Sender");

        create.setText("create payment");
        create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createMouseClicked(evt);
            }
        });

        jLabel4.setText("Amount");

        reciever.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recieverKeyPressed(evt);
            }
        });

        jLabel5.setText("Message");

        jButton1.setText("<-back");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        sender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                senderMouseClicked(evt);
            }
        });

        chooseUserList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        chooseUserList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseUserListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(chooseUserList);

        jLabel2.setText("Reciever");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reciever)
                            .addComponent(sender)
                            .addComponent(amount)
                            .addComponent(message, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(error1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(create)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(reciever, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(sender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(error1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(create)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createMouseClicked
        BigDecimal money = new BigDecimal(amount.getText().replaceAll(",",""));
        if(indexChooseUser<0){ //nebyl zvolen prijemce
            wrongReciever = true;
        }else{
            payment = new Payment( money,  LocalDate.now(),  message.getText(),  false,  accUserList.get(indexChooseUser),  accPrivate);
            payManager.createPayment(payment);
            ShowPaymentForm Spf = new ShowPaymentForm(payment, accManager, payManager, accPrivate);
            Spf.setVisible(true);
            this.setVisible(false);
        }
        if(wrongReciever == true){
            reciever.setBackground(Color.red);
            error1.setText("Write name to reciever press enter and choose correct row");
            error1.setForeground(Color.red);
            error1.setVisible(true);
        }
    }//GEN-LAST:event_createMouseClicked

    private void recieverKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recieverKeyPressed
        int delete=-1;
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            accUserList = accManager.findAccountByNameActive(reciever.getText());
            final DefaultListModel model = new DefaultListModel();
            for (int i = 0; i < accUserList.size(); i++) {
                if(!accUserList.get(i).equals(accPrivate)){ //nemoznost posilat penize sama sobe
                    model.addElement(accUserList.get(i).getBirthName()+ " " +accUserList.get(i).getGivenName() + " - èíslo úètu: " + accUserList.get(i).getAccountNumber());
                }else{
                    delete = i;
                }
            }
            if(delete>=0){
                accUserList.remove(delete);
            }
            chooseUserList.setModel(model);
            chooseUserList.setVisible(true);
        }
    }//GEN-LAST:event_recieverKeyPressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        MainPaymentForm Mpf = new MainPaymentForm(accManager,payManager,accPrivate);
        Mpf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

    private void senderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_senderMouseClicked

    }//GEN-LAST:event_senderMouseClicked

    private void chooseUserListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseUserListMouseClicked
        reciever.setText(accUserList.get(chooseUserList.getSelectedIndex()).getGivenName() + " " + accUserList.get(chooseUserList.getSelectedIndex()).getBirthName());
        chooseUserList.setVisible(false);
        indexChooseUser = chooseUserList.getSelectedIndex();
        error1.setVisible(false);
        reciever.setBackground(Color.white);
        wrongReciever = false;
    }//GEN-LAST:event_chooseUserListMouseClicked

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
            java.util.logging.Logger.getLogger(CreatePaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatePaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatePaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatePaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatePaymentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JList<String> chooseUserList;
    private javax.swing.JButton create;
    private javax.swing.JLabel error1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField message;
    private javax.swing.JTextField reciever;
    private javax.swing.JTextField sender;
    // End of variables declaration//GEN-END:variables
}
