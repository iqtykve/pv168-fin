package cz.fi.muni.pv168;

import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainPaymentForm extends javax.swing.JFrame {
    private AccountManager accManager;
    private PaymentManager payManager;
    private Account accPrivate;
    private List<Payment> paymentsList;
    public MainPaymentForm() {
        initComponents();
    }
    public MainPaymentForm(AccountManager accManager, PaymentManager payManager, Account acc){
        this.accManager = accManager;
        this.payManager = payManager;
        this.accPrivate = acc;
        String sended;
        paymentsList = payManager.findPaymentsByAccount(acc);
        if(acc.getWasDeleted()){
            createPayment.setEnabled(false);
        }
        initComponents();
        
        title.setText(title.getText()+" "+acc.getGivenName()+" "+acc.getBirthName());
        DefaultTableModel model = new DefaultTableModel(); 
        model.addColumn("Sender");
        model.addColumn("Reciever"); 
        model.addColumn("Amount"); 
        model.addColumn("Message"); 
        model.addColumn("Date");
        model.addColumn("Sended"); 
        for (int i = 0; i < paymentsList.size(); i++) {
            if(paymentsList.get(i).getSended()==true){
                sended="Yes";
            }else{
                sended="No";
            }
            model.addRow(new Object[]{paymentsList.get(i).getSender().getGivenName()+ " "+paymentsList.get(i).getSender().getBirthName(),paymentsList.get(i).getReceiver().getGivenName()+ " "+paymentsList.get(i).getReceiver().getBirthName(), paymentsList.get(i).getAmount(),paymentsList.get(i).getMessage(), paymentsList.get(i).getDate(),sended});
        }
        payments.setModel(model);
        payments.setDefaultEditor(Object.class, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        payments = new javax.swing.JTable();
        title = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        createPayment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        payments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Reciever", "Amount", "Message", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        payments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(payments);
        if (payments.getColumnModel().getColumnCount() > 0) {
            payments.getColumnModel().getColumn(0).setHeaderValue("Reciever");
            payments.getColumnModel().getColumn(1).setHeaderValue("Amount");
            payments.getColumnModel().getColumn(2).setHeaderValue("Message");
            payments.getColumnModel().getColumn(3).setHeaderValue("Date");
        }

        title.setText("Main Payment");

        jButton1.setText("<-back");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        createPayment.setText("create new payment");
        createPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createPaymentMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createPayment)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createPayment)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
           ShowUserForm Suf = new ShowUserForm(accPrivate, accManager, payManager);
           this.setVisible(false);
           Suf.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void paymentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentsMouseClicked
        ShowPaymentForm Spf = new ShowPaymentForm(paymentsList.get(payments.getSelectedRow()),accManager,payManager,accPrivate);
        Spf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_paymentsMouseClicked

    private void createPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createPaymentMouseClicked
        CreatePaymentForm Cpf = new CreatePaymentForm(accManager,payManager,accPrivate);
        Cpf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_createPaymentMouseClicked

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
            java.util.logging.Logger.getLogger(MainPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPaymentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createPayment;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable payments;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
