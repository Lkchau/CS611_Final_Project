package gui;

import account.SecuritiesAccount;

import javax.swing.*;

public class BuyStockIntegerTextFrame extends JFrame {

    private BuyStockIntegerTextPanel textPanel;

    public BuyStockIntegerTextFrame(String id, SecuritiesAccount account){
        super();

        textPanel = new BuyStockIntegerTextPanel(id, account);

        textPanel.setListener(new BuyStockListener() {
            @Override
            public void buyStock(String id, int number, SecuritiesAccount account) {
                //TODO: account.sell(id, number);

                if(true){
                    JOptionPane.showMessageDialog(new JFrame(), "Bought successfully!");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Failed to buy!", "Oops!", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        add(textPanel);

        setVisible(true);
        setSize(450,200);
    }
}