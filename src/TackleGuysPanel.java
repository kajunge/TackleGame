
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author kristinamantha
 */
public class TackleGuysPanel extends JPanel {

    private ImageIcon tackleImage = new ImageIcon("images/tackle.png");
    private JButton[] tackleGuys;
    private Rectangle[] r1s;
    int counter;
    int changedTimer;
    Timer tim;
    int delay = 0;
    int i, j;
    private int totalTackles = 0;
    private int touchdowns = 0;
    int hit = 1;
    int xymax = 400;
    int xymin = 0;
    int[] t1X;
    int[] t1Y;
    int rbX;
    int rbY;
    RunningBackPanel rbjp;
    TopPanel tjp;
    GamePanel gjp;

    public TackleGuysPanel(RunningBackPanel importedRBP, TopPanel importedTP, GamePanel importedGP) {
        super();
        rbjp = importedRBP;
        tjp = importedTP;
        gjp = importedGP;
        counter = 0;
        r1s = new Rectangle[5];
        tackleGuys = new JButton[5];
        
        for (int j = 0; j < r1s.length; j++) {

            for (int i = 0; i < 5; i++) {
                r1s[i] = new Rectangle(getRandX(), getRandY(), 40, 60);
                tackleGuys[j] = new JButton();
                tackleGuys[j].setBounds(r1s[i]);
                tackleGuys[j].setIcon(tackleImage);
                tackleGuys[j].setBackground(Color.red);
                tackleGuys[j].setVisible(false);
                tackleGuys[j].setOpaque(false);
                tackleGuys[j].setContentAreaFilled(false);
                tackleGuys[j].setBorderPainted(false);
                this.add(tackleGuys[j]);
            }

        }
        tjp.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tackleGuys.length; i++) {
                    tackleGuys[i].setVisible(true);
                    tim.start();
                }

            }

        });        
        delay = 1000;
        tim = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (rbjp.getRunningBack().getBounds().intersects(getTackleGuys()[i].getBounds())) {
                    tim.stop();
                    rbjp.getRunningBack().setVisible(false);

                    for (int j = 0; j < getTackleGuys().length; j++) {
                        getTackleGuys()[j].setVisible(false);
                        getTackleGuys()[j].setBounds(getRandX(), getRandY(), 40, 60);
                    }

                    setTackles(getTackles() + hit);
                    tjp.getPlayButton().setEnabled(true);
                    System.out.println(getTackles() + " tackles from Tackle Panel");
                    tjp.getScore().setText("Touchdowns: " + "  " + "  Tackles: " + getTackles() + "  ");
                    if (getTackles() > 3) {
                        tjp.getPlayButton().setEnabled(false);
                        JOptionPane.showMessageDialog(null, "<html><center>Game over!</center><br>"
                                + "<center>Click OK and exit the game.</center></html>");
                    } else {
                        JOptionPane.showMessageDialog(null, "<html><center>You got tackled!</center><br>"
                                + "<center>Click OK!</center><br> "
                                + "Click play button to play again.</center></html><br>");
                    }
                }
                for (int i = 0; i < getTackleGuys().length; i++) {

                    for (int k = 0; k < getTackleGuys().length; k++) {
                        t1X = new int[5];
                        t1Y = new int[5];
                        for (int l = 0; l < getTackleGuys().length; l++) {

                            t1X[l] = getTackleGuys()[i].getX();
                            rbX = rbjp.getRunningBack().getX();
                            t1Y[l] = getTackleGuys()[i].getY();
                            rbY = rbjp.getRunningBack().getY();
                            if (t1X[l] < rbX) {
                                t1X[l] = t1X[l] + (tjp.getJs1().getValue() / 100);
                                if (t1Y[l] < rbY) {
                                    t1Y[l] = t1Y[l] + (tjp.getJs1().getValue() / 100);
                                } else {
                                    if (t1Y[l] > rbY) {
                                        t1Y[l] = t1Y[l] - (tjp.getJs1().getValue() / 100);
                                    }
                                }
                                getTackleGuys()[i].setBounds(t1X[l], t1Y[l], 40, 60);
                            } else {
                                if (t1X[l] > rbX) {
                                    t1X[l] = t1X[l] - (tjp.getJs1().getValue() / 100);
                                    if (t1Y[l] < rbY) {
                                        t1Y[l] = t1Y[l] + (tjp.getJs1().getValue() / 100);
                                    } else {
                                        if (t1Y[l] > rbY) {
                                            t1Y[l] = t1Y[l] - (tjp.getJs1().getValue() / 100);
                                        }
                                    }
                                    getTackleGuys()[i].setBounds(t1X[l], t1Y[l], 40, 60);
                                }
                            }
                        }
                    }
                }
            }
        });

        tim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (rbjp.getRunningBack().getBounds().intersects(getTackleGuys()[i].getBounds())) {
                    tim.stop();
                    rbjp.getRunningBack().setVisible(false);

                    for (int j = 0; j < getTackleGuys().length; j++) {
                        getTackleGuys()[j].setVisible(false);
                        getTackleGuys()[j].setBounds(getRandX(), getRandY(), 40, 60);
                    }

                    setTackles(getTackles() + hit);
                    tjp.getPlayButton().setEnabled(true);
                    System.out.println(getTackles() + " tackles from Tackle Panel");
                    tjp.getScore().setText("Touchdowns: " + "  " + "  Tackles: " + getTackles() + "  ");
                    if (getTackles() > 3) {
                        tjp.getPlayButton().setEnabled(false);
                        JOptionPane.showMessageDialog(null, "<html><center>Game over!</center><br>"
                                + "<center>Click OK and exit the game.</center></html>");
                    } else {
                        JOptionPane.showMessageDialog(null, "<html><center>You got tackled!</center><br>"
                                + "<center>Click OK!</center><br> "
                                + "Click play button to play again.</center></html><br>");
                    }
                }
                for (int i = 0; i < getTackleGuys().length; i++) {

                    for (int k = 0; k < getTackleGuys().length; k++) {
                        t1X = new int[5];
                        t1Y = new int[5];
                        for (int l = 0; l < getTackleGuys().length; l++) {

                            t1X[l] = getTackleGuys()[i].getX();
                            rbX = rbjp.getRunningBack().getX();
                            t1Y[l] = getTackleGuys()[i].getY();
                            rbY = rbjp.getRunningBack().getY();
                            if (t1X[l] < rbX) {
                                t1X[l] = t1X[l] + (tjp.getJs1().getValue() / 2);
                                if (t1Y[l] < rbY) {
                                    t1Y[l] = t1Y[l] + (tjp.getJs1().getValue() / 2);
                                } else {
                                    if (t1Y[l] > rbY) {
                                        t1Y[l] = t1Y[l] - (tjp.getJs1().getValue() / 2);
                                    }
                                }
                                getTackleGuys()[i].setBounds(t1X[l], t1Y[l], 40, 60);
                            } else {
                                if (t1X[l] > rbX) {
                                    t1X[l] = t1X[l] - (tjp.getJs1().getValue() / 2);
                                    if (t1Y[l] < rbY) {
                                        t1Y[l] = t1Y[l] + (tjp.getJs1().getValue() / 2);
                                    } else {
                                        if (t1Y[l] > rbY) {
                                            t1Y[l] = t1Y[l] - (tjp.getJs1().getValue() / 2);
                                        }
                                    }
                                    getTackleGuys()[i].setBounds(t1X[l], t1Y[l], 40, 60);
                                }
                            }
                        }
                    }
                }
            }

        });
    }
//=====================================
// Method to create a random X value      
//===================================== 

    public int getRandX() {
        int min = 650;
        int max = 1050;
        double randX = min + Math.random() * (max - min);
        return (int) randX;
    }
//=====================================
// Method to create a random Y value      
//===================================== 

    public int getRandY() {
        int min = 0;
        int max = 400;
        double randY = min + Math.random() * (max - min);
        return (int) randY;
    }

    /**
     * @return the tackleGuys
     */
    public JButton[] getTackleGuys() {
        return tackleGuys;
    }

    /**
     * @param tackleGuys the tackleGuys to set
     */
    public void setTackleGuys(JButton[] tackleGuys) {
        this.setTackleGuys(tackleGuys);
    }

    /**
     * @return the r1s
     */
    public Rectangle[] getR1s() {
        return r1s;
    }

    /**
     * @param r1s the r1s to set
     */
    public void setR1s(Rectangle[] r1s) {
        this.r1s = r1s;
    }

    /**
     * @return the totalTackles
     */
    public int getTackles() {
        return totalTackles;
    }

    /**
     * @param totalTackles the totalTackles to set
     */
    public void setTackles(int totalTackles) {
        this.totalTackles = totalTackles;
    }

    /**
     * @return the touchdowns
     */
    public int getTouchdowns() {
        return touchdowns;
    }

    /**
     * @param touchdowns the touchdowns to set
     */
    public void setTouchdowns(int touchdowns) {
        this.touchdowns = touchdowns;
    }
}

