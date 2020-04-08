
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kristinamantha
 */
class GamePanel extends JPanel implements KeyListener, ActionListener {

    JLabel fieldLabel, instLabel;
    ImageIcon fieldImage = new ImageIcon("images/AmFBfield.png");
    ImageIcon instImage = new ImageIcon("images/inst.png");
    int randX, randY;
    int x = 98, y = 230, velX = 0, velY = 0;
    RunningBackPanel rbp;
    TackleGuysPanel tgp;
    int pt;
    ArrayList<Point> points = new ArrayList<>();
    private int tackles = 0;
    private int touchdowns = 0;
    private Rectangle td1;
    private Rectangle td2;
    private JButton tdb1;
    private JButton tdb2;
    TopPanel tjp;

    public GamePanel(TopPanel importedTJP, RunningBackPanel importedRP, TackleGuysPanel importedTP) {
        super();
        rbp = importedRP;
        tgp = importedTP;
        tjp = importedTJP;

        this.setPreferredSize(new Dimension(1200, 475));
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();
        this.setLayout(new BorderLayout());
        tjp.getPlayButton().addActionListener(this);
//=====================================
// Adding the rectangles for the tacklers  
//=====================================        
        td1 = new Rectangle(1150, 215, 13, 61);
        td2 = new Rectangle(93, 298, 8, 478);
        tdb1 = new JButton();
        tdb1.setBounds(td1);
        tdb1.setVisible(false);
        add(tdb1);
        tdb2 = new JButton();
        tdb2.setBounds(td2);
        tdb2.setVisible(false);
        add(tdb2);
//=====================================
// Adding the runningback from RunningBackPanel     
//=====================================  
        add(rbp.getRunningBack());
//=====================================
// Adding the tackle guys from TackleGuysPanel     
//=====================================          
        for (int i = 0; i < 5; i++) {
            add(tgp.getTackleGuys()[i]);
        }
//=====================================
// Adding the field image to the background    
//=====================================          
        instLabel = new JLabel();
        instLabel.setBounds(370, 120, 473, 232);
        instLabel.setIcon(instImage);
        instLabel.setVisible(true);
        add(instLabel);
//=====================================
// Adding the field image to the background    
//=====================================          
        fieldLabel = new JLabel();
        fieldLabel.setIcon(fieldImage);
        fieldLabel.setVisible(true);
        add(fieldLabel);

    }
//=====================================
// Method to create a random X value      
//===================================== 

    public int getRandX() {
        int min = 120;
        int max = 1100;
        double randX = min + Math.random() * (max - min);
        return (int) randX;
    }
//=====================================
// Method to create a random Y value      
//===================================== 

    public int getRandY() {
        int min = 25;
        int max = 440;
        double randY = min + Math.random() * (max - min);
        return (int) randY;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int k = e.getKeyCode();
        if (k == e.VK_LEFT) {
            x = x - 10;
        }
        if (k == e.VK_RIGHT) {
            x = x + 10;
        }
        if (k == e.VK_UP) {
            y = y - 10;
        }
        if (k == e.VK_DOWN) {
            y = y + 10;
        }
//=====================================
// reading in running back start location
//===================================== 
        rbp.getRunningBack().setBounds(x, y, 80, 95);
//=====================================
// determining intersection of running back and tacklers 
//===================================== 
        for (int i = 0; i < tgp.getTackleGuys().length; i++) {
            if (tgp.getTackleGuys()[i].getBounds().intersects(rbp.getRunningBack().getBounds())) {
                tackles = (tackles + 1);
                setTackles(tackles);
            }

        }
//=====================================
// determining intersection of running back and goal line
//=====================================        
        if (rbp.getRunningBack().getBounds().intersects(getTd1())) {
            touchdowns = touchdowns + 1;
            setTouchdowns(touchdowns);

        }
        //System.out.println(getTackles() + " " + getTouchdowns());

        tjp.getScore().setText("Touchdowns: " + "  " + getTouchdowns()
                + "  Tackles: " + getTackles() + "  ");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        velX = 0;
        velY = 0;

        requestFocus();
    }

    @Override
    public void paintComponent(Graphics g) {
        // create if statements that change the image to the one that the character picked.
        super.paintComponent(g);
        requestFocusInWindow();
    }

    /**
     * @return the tackles
     */
    public int getTackles() {
        return tackles;
    }

    /**
     * @param tackles the tackles to set
     */
    public void setTackles(int tackles) {
        this.tackles = tackles;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == tjp.getPlayButton()) {
            instLabel.setVisible(false);
        }
    }

    /**
     * @return the td1
     */
    public Rectangle getTd1() {
        return td1;
    }

    /**
     * @param td1 the td1 to set
     */
    public void setTd1(Rectangle td1) {
        this.td1 = td1;
    }

    /**
     * @return the td2
     */
    public Rectangle getTd2() {
        return td2;
    }

    /**
     * @param td2 the td2 to set
     */
    public void setTd2(Rectangle td2) {
        this.td2 = td2;
    }

    /**
     * @return the tdb1
     */
    public JButton getTdb1() {
        return tdb1;
    }

    /**
     * @param tdb1 the tdb1 to set
     */
    public void setTdb1(JButton tdb1) {
        this.tdb1 = tdb1;
    }

    /**
     * @return the tdb2
     */
    public JButton getTdb2() {
        return tdb2;
    }

    /**
     * @param tdb2 the tdb2 to set
     */
    public void setTdb2(JButton tdb2) {
        this.tdb2 = tdb2;
    }

}
