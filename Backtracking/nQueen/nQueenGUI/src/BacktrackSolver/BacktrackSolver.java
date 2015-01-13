package BacktrackSolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BacktrackSolver {
    public static JFrame myFrame;
    public static void main(String[] args) {
        myFrame= new JFrame("nQueen with GUI");
        final int WIDTH = 693;
        final int HEIGHT = 545;
        ImageIcon icon = new ImageIcon(BacktrackSolver.class.getResource("icon.png"));

        myFrame = new JFrame("nQueen");
        myFrame.setIconImage(icon.getImage());
        myFrame.setContentPane((new myPanel(WIDTH, HEIGHT)));
        myFrame.pack();
        //yFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
    public static class myPanel extends  JPanel{
        nQueen myQueen;

        int numOfQueen=8;
        JTextField numOfQueenField;
        int[][] grid;
        int squareSize=500/numOfQueen;
        JLabel guide;
        JButton status;

        public myPanel(int width, int height){
            setLayout(null);
            setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLUE));
            setPreferredSize(new Dimension(width,height));

            grid=new int[numOfQueen+1][numOfQueen+1];

            guide =new JLabel("Enter the number of Queen => Click 'New Grid' => Click 'Solve' ",JLabel.CENTER);
            guide.setForeground(Color.BLUE);
            guide.setFont(new Font("Helvetica",Font.PLAIN,16));

            JLabel queenLabel=new JLabel("# of queens (2-29):",JLabel.LEFT);
            queenLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));

            numOfQueenField=new JTextField();
            numOfQueenField.setText(Integer.toString(numOfQueen));

            JButton resetButton=new JButton("New Grid");
            resetButton.addActionListener(new ActionHandler());
            resetButton.setBackground(Color.LIGHT_GRAY);
            resetButton.setToolTipText("Make new grid");

            JButton solveButton = new JButton("Solve");
            solveButton.addActionListener(new ActionHandler());
            solveButton.setBackground(Color.PINK);
            solveButton.setToolTipText("Solve the problem");

            status =new JButton("Time : 0.0 s");
            status.setBackground(Color.LIGHT_GRAY);
            status.setToolTipText("Time for find one solution");


            JButton exitButton=new JButton("Exit");
            exitButton.addActionListener(new ActionHandler());
            exitButton.setBackground(Color.RED);
            exitButton.setToolTipText("Exit program");



            add(guide);
            add(queenLabel);
            add(numOfQueenField);
            add(resetButton);
            add(solveButton);
            add(status);
            add(exitButton);



            guide.setBounds(0,515,500,23);
            queenLabel.setBounds(520,5,110,35);
            numOfQueenField.setBounds(650,6,25,35);
            resetButton.setBounds(520,50,160,35);
            solveButton.setBounds(520,100, 160, 35);
            status.setBounds(520,135,160,35);
            status.setEnabled(false);
            exitButton.setBounds(520, 470, 160, 35);
            fillGrid();
        }
        public void fillGrid(){
            int oldQueen=numOfQueen;
            try{
                if(!numOfQueenField.getText().isEmpty()){
                    numOfQueen=Integer.parseInt(numOfQueenField.getText());
                }
                else{
                    JOptionPane.showMessageDialog(this,"The field \"# of queens\" \n accepts only numbers between 1 and 29","Problem",JOptionPane.ERROR_MESSAGE);
                    numOfQueen=oldQueen;
                    return;
                }
            }catch (NumberFormatException err){
                JOptionPane.showMessageDialog(this,
                        "The field \"# of rows\" \naccepts only numbers between 1 and 29",
                        "Problem", JOptionPane.ERROR_MESSAGE);
                numOfQueen=oldQueen;
                return;
            }
            if(numOfQueen<2 || numOfQueen>29){
                JOptionPane.showMessageDialog(this,
                        "The field \"# of rows\" \naccepts only numbers between 1 and 29",
                        "Problem", JOptionPane.ERROR_MESSAGE);
                numOfQueen=oldQueen;
                return;
            }

            squareSize=500/numOfQueen;
            grid=new int[numOfQueen+1][numOfQueen+1];
            for(int r=1;r<=numOfQueen;r++){
                for(int c=1;c<=numOfQueen;c++){
                    grid[r][c]=0;
                }
            }
            status.setText("Time : 0.0 s");
            repaint();
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(10, 10, numOfQueen * squareSize + 1, numOfQueen * squareSize + 1);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(11,11,numOfQueen*squareSize-1,numOfQueen*squareSize-1);

            for(int r=0;r<=numOfQueen-1;r++){
                for(int c=0;c<=numOfQueen-1;c++){
                    if(grid[r][c]==0){
                        if((r%2==1 && c%2==0) || (r%2==0 && c%2==1)){
                            g.setColor(Color.BLACK);
                            g.fillRect(11+c*squareSize,11+r*squareSize,squareSize,squareSize);
                        }
                    }
                    else if(grid[r][c]==1){
                        g.setColor(Color.BLUE);
                        g.fillRect(11+c*squareSize,11+r*squareSize,squareSize,squareSize);
                    }
                }
            }
        }
        public void Solve(){
            myQueen=new nQueen(numOfQueen);
            long timeStart=System.nanoTime();
            myQueen.findSolution(1,1);
            long timeEnd=System.nanoTime();
            ArrayList<Integer> res=myQueen.getSolution();
            System.out.println(res.size());
            for(int i=0;i<res.size();i++){
                grid[i][res.get(i)]=1;
            }
            status.setText("Time : " + (double)(timeEnd-timeStart)/1000000000 + "s");
            System.out.println("Solved");
        }



        private class ActionHandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent evt){
                String cmd=evt.getActionCommand();
                if(cmd.equals("New Grid")){
                    fillGrid();
                }
                else if(cmd.equals("Solve")){
                    Solve();
                    repaint();
                }
                else if(cmd.equals("Exit")){
                    System.exit(0);
                }
            }

        }
    }
}
