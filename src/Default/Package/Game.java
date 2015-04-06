/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Default.Package;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author Sourav
 */
public class Game extends javax.swing.JFrame {

    private static JButton butt[][] = new JButton[100][100];
    private static int Grid[][] = new int[100][100];
    private static Artificial_Intelligence AI;
    
    public static ButtonHandler buttHandler = new ButtonHandler();
    private static int n;
    private static int Player;
    
    private static int TurnCnt;
    
    
    private JLabel background;
    private ImageIcon img = new ImageIcon("image/GameBack.jpg");
    private static ImageIcon X = new ImageIcon("image/X.png");
    private static ImageIcon O = new ImageIcon("image/O.png");
    
    
    
    public Game() {
        
        super("Tic Tac Toe");
        background = new JLabel(img);
        setContentPane(background);
        
        Random rand = new Random();
        int ranNum = rand.nextInt()%1;  //it will be 2 but there's some problem when DIffculty level is 3 .. so 1
        Player= ranNum;
        
        n= Main.SizeOfBoard;
        
        if(Main.difficulty==3)
        {
            n= 7;
            LabelHeading.setText("7X7 Tic Tac Toe Five In A Line");
        }
        
        System.out.println("N Is"+n);
        initComponents();
        InitializeGrid(n);
        AI = new Artificial_Intelligence();
        
        
        setLayout(null);
        add(GridPanel);
        validate();
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        
        TurnCnt=0;
        
        //compueter Turn 
        if (Player == 1) 
        {
            int x,y;
            Point p; 
            p = AI.getOptimalMove(Grid, n, 2);
            x = p.x;
            y = p.y;
            butt[x][y].setIcon(O);
            Grid[x][y] = 2;
            if (AI.Dead_Five(Grid, x, y, 2) >= 1) 
            {
                System.out.println("Computer is the winner");
                JOptionPane.showMessageDialog(null, "Computer is the winner");
                
            }
            TurnCnt++;
            MoveLabel.setText("Moves Count : " + TurnCnt);
            LabelHeading.setText("15X15 Tic Tac Toe Five In A Line");
            Player = 0;
        }
    }
    
    
    private void InitializeGrid(int n)
    {
        GridPanel.setLayout(null);
        int SZ = 500/n;     //size of each cell
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                butt[i][j]=new JButton("");
                butt[i][j].setBounds(i*SZ, j*SZ, SZ,SZ);
                butt[i][j].setForeground(Color.gray);
                GridPanel.add(butt[i][j]);
                butt[i][j].addActionListener(buttHandler);
                Grid[i][j]=0;
            }
        }
        
        GridPanel.setVisible(true);
//        GridPanel.setBounds(50, 50, 500, 500);
    }
    
    public static class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            int x = 0, y = 0;
            for(int i=0;i<15;i++)
                for(int j=0;j<15;j++)
                    if(e.getSource()==butt[i][j])
                    {
                        if(Grid[i][j]==0)
                        {
                            x=i;
                            y=j;
                            System.out.println("Human's Skilled Move: "+i+", "+j);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Please Select an Empty Slot");
                            return;
                        }
                    }
            
            
             //Human Player
            if(Player==0)
            {
                butt[x][y].setIcon(X);                
                Grid[x][y]=1;
                
                
                if(AI.Dead_Five(Grid, x, y, 1)>=1)
                {
                    System.out.println("First Player is the winner");
                    TurnCnt++;
                    JOptionPane.showMessageDialog(null, "First Player is the winner");
                    NewGame.doClick();
                    return;
                }
                
                TurnCnt++;
                MoveLabel.setText("Moves Count : " + TurnCnt);
                LabelHeading.setText("15X15 Tic Tac Toe Five In A Line");
                Player = 1;
                
                //All Cell has been filled
                if(TurnCnt==Main.SizeOfBoard*Main.SizeOfBoard)
                {
                    JOptionPane.showMessageDialog(null, "Drawn");
//                    newGame.doClick();
                    return;
                }
                
            }
            
            
            
            
            
            //Computer Player
            if(Player==1)
            {
                Point p;
                p = AI.getOptimalMove(Grid, n, 2);
                x = p.x;
                y = p.y;
                
                butt[x][y].setIcon(O);
                Grid[x][y]=2;
                if(AI.Dead_Five(Grid, x, y, 2)>=1)
                {
                    System.out.println("Computer is the winner");
                    TurnCnt++;
                    MoveLabel.setText("Moves Count : " + TurnCnt);
                    
                    JOptionPane.showMessageDialog(null, "Computer is the winner");
//                    newGame.doClick();
                    return;
                    
                }
                TurnCnt++;
                MoveLabel.setText("Moves Count : " + TurnCnt);
                Player=0;
                //All Cell has been filled
                if(TurnCnt==Main.SizeOfBoard*Main.SizeOfBoard)
                {
                    JOptionPane.showMessageDialog(null, "Drawn");
//                    newGame.doClick();
                    return;
                }
            }
            
            
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GridPanel = new javax.swing.JPanel();
        NewGame = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        LabelHeading = new javax.swing.JLabel();
        MoveLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));

        GridPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        GridPanel.setRequestFocusEnabled(false);

        javax.swing.GroupLayout GridPanelLayout = new javax.swing.GroupLayout(GridPanel);
        GridPanel.setLayout(GridPanelLayout);
        GridPanelLayout.setHorizontalGroup(
            GridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        GridPanelLayout.setVerticalGroup(
            GridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        NewGame.setFont(new java.awt.Font("Snap ITC", 1, 20)); // NOI18N
        NewGame.setText("New Game");
        NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });

        BackButton.setFont(new java.awt.Font("Snap ITC", 1, 20)); // NOI18N
        BackButton.setText("Back To The Menu");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        LabelHeading.setFont(new java.awt.Font("Snap ITC", 0, 40)); // NOI18N
        LabelHeading.setText("0X0 Tic Tac Toe Five In A Line");

        MoveLabel.setFont(new java.awt.Font("Snap ITC", 1, 20)); // NOI18N
        MoveLabel.setText("Moves Count: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(NewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MoveLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(GridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LabelHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(NewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(MoveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameActionPerformed
        // TODO add your handling code here:
        Main.frame.dispose(); 
        Main.frame = new Game();
        Main.frame.setVisible(true);
    }//GEN-LAST:event_NewGameActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        Main.frame.dispose(); 
        Main.frame = new Menu();
        Main.frame.setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton BackButton;
    private javax.swing.JPanel GridPanel;
    public static javax.swing.JLabel LabelHeading;
    private static javax.swing.JLabel MoveLabel;
    private static javax.swing.JButton NewGame;
    // End of variables declaration//GEN-END:variables
}
