package cz.fi.muni.pv168;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultListModel;

public class ShowPaymentForm extends javax.swing.JFrame {

    private AccountManager accManager;
    private PaymentManager payManager;
    private Payment payment;
    private Account accPrivate;
    private List<Account> accUserList;
    private int indexChooseUser=-1;
    private boolean wrongReciever = false;
    public ShowPaymentForm() {
        initComponents();
    }
    
    public ShowPaymentForm(Payment payment,AccountManager accManager,PaymentManager payManager,Account acc){
        accPrivate = acc;
        this.payment = payment;
        this.accManager = accManager;
        this.payManager = payManager;
        initComponents();
        
        chooseUserList.setVisible(false);
        sender.setEditable(false);
        
        reciever.setText(payment.getReceiver().getGivenName() + " " + payment.getReceiver().getBirthName());
        sender.setText(payment.getSender().getGivenName() + " " + payment.getSender().getBirthName());
        amount.setText(payment.getAmount().toString());
        message.setText(payment.getMessage());
        
        if(!acc.equals(payment.getSender()) && payment.getSended()==false){ //nejsi zakladatel, nemas pravo menit platbu
            wasSended();
            error.setText("You are not a creator. This payment can not be edited");
        }
        
        if(payment.getSended()==true){ //plata byla odeslana, nemoznost editace
            wasSended();
        }
        if(payment.getSended()==true){
            sended.setText("Yes");
        }else{
            sended.setText("No");
        }
    }
    private void wasSended(){
        reciever.setEditable(false);
        sender.setEditable(false);
        amount.setEditable(false);
        message.setEditable(false);
        update.setEnabled(false);
        send.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        sender = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        message = new javax.swing.JTextField();
        sended = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        update = new javax.swing.JButton();
        reciever = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        chooseUserList = new javax.swing.JList<>();
        error = new javax.swing.JLabel();
        error1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setText("Information about payment");

        jLabel2.setText("Reciever");

        jLabel3.setText("Sender");

        jLabel4.setText("Amount");

        jLabel5.setText("Message");

        jLabel6.setText("Sended");

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

        sended.setText("jLabel7");

        send.setText("send payment");
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendMouseClicked(evt);
            }
        });

        update.setText("update payment");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });

        reciever.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recieverKeyPressed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sended))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 120, Short.MAX_VALUE)
                        .addComponent(update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(error)
                            .addComponent(error1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error)
                .addGap(3, 3, 3)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(sended))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(send)
                    .addComponent(update))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        MainPaymentForm Mpf = new MainPaymentForm(accManager,payManager,accPrivate);
        Mpf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

    private void sendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMouseClicked
        payment.setSended(true);
        payManager.updatePayment(payment);
        wasSended();
        sended.setText("Yes");
    }//GEN-LAST:event_sendMouseClicked

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
        
        if(indexChooseUser>=0){ //novy reciever
            if(reciever.getText().equals(accUserList.get(indexChooseUser).getGivenName()+" "+accUserList.get(indexChooseUser).getBirthName())){ //kontrola jmena       
                BigDecimal money = new BigDecimal(amount.getText().replaceAll(",",""));
                payment.setReceiver(accUserList.get(indexChooseUser));
                payment.setAmount(money);
                payment.setMessage(message.getText());
                payManager.updatePayment(payment);
                updateOK();
            }else{
                wrongReciever = true;
            }
        }else{ //stavajici reciever
            if(reciever.getText().equals(payment.getReceiver().getGivenName()+" "+payment.getReceiver().getBirthName())){ //kontrola jmena
                BigDecimal money = new BigDecimal(amount.getText().replaceAll(",",""));
                payment.setAmount(money);
                payment.setMessage(message.getText());
                payManager.updatePayment(payment);
                updateOK();
            }else{
                wrongReciever = true;
            }
        }
        
        if(wrongReciever == true){
            reciever.setBackground(Color.red);
            error1.setText("Write name to reciever press enter and choose correct row");
            error1.setForeground(Color.red);
            error1.setVisible(true);
        }
    }//GEN-LAST:event_updateMouseClicked
    private void updateOK(){
        MainPaymentForm Mpf = new MainPaymentForm(accManager,payManager,accPrivate);
        Mpf.setVisible(true);
        this.setVisible(false);
    }
    
    private void senderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_senderMouseClicked

    }//GEN-LAST:event_senderMouseClicked

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

    private void chooseUserListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseUserListMouseClicked
        reciever.setText(accUserList.get(chooseUserList.getSelectedIndex()).getGivenName() + " " + accUserList.get(chooseUserList.getSelectedIndex()).getBirthName());
        chooseUserList.setVisible(false);
        indexChooseUser = chooseUserList.getSelectedIndex();
        error1.setVisible(false);
        reciever.setBackground(Color.white);
        wrongReciever = false;
    }//GEN-LAST:event_chooseUserListMouseClicked

 
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
            java.util.logging.Logger.getLogger(ShowPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowPaymentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JList<String> chooseUserList;
    private javax.swing.JLabel error;
    private javax.swing.JLabel error1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField message;
    private javax.swing.JTextField reciever;
    private javax.swing.JButton send;
    private javax.swing.JLabel sended;
    private javax.swing.JTextField sender;
    private javax.swing.JLabel title;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
