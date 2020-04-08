import java.awt.BorderLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author kristinamantha
 */
class InitialFrame extends JFrame {

    ControlPanel mjp;

    public InitialFrame() {
        super("The Tackle-Breaking Running Back");
        LayoutSetupMAC();
//------------------------------------------------------
// Create components
        mjp = new ControlPanel();
//------------------------------------------------------
// Choose a Layout for JFrame and 
// add Jpanel to JFrame according to layout    	
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mjp, "Center");
//------------------------------------------------------
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setVisible(true);
        setResizable(false);
    }

    void LayoutSetupMAC() {
        // On some MACs it might be necessary to have the statement below 
        //for the background color of the button to appear    
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //------------------------------------------------------           
    }

}

