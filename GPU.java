import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GPU extends JPanel {
  final static int width = 160;
  final static int height = 144;
  BufferedImage bufferedImage;

  public GPU() {
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
    setPreferredSize(new Dimension(width, height));
    JFrame frame = new JFrame("Gameboy Emulator");
    frame.add(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    g.drawImage(bufferedImage, 0, 0, null);
  }

  public void drawPixel(int x, int y, Color color) {
    Graphics g = bufferedImage.getGraphics();
    g.setColor(color);
    g.drawLine(x, y, x, y);
    g.dispose();
    repaint();
  }
}