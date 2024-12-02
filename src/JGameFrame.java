import javax.swing.JFrame;
import java.awt.*;

public class JGameFrame {
    private JFrame frame;

    public JGameFrame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("Hello World");

        GamePanel gamePanel = new GamePanel();
        gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        this.frame.add(gamePanel, BorderLayout.CENTER);
        this.frame.pack();

        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        gamePanel.startGameThread();
    }
}
