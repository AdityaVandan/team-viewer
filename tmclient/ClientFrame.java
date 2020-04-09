import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.swing.Timer;
public class ClientFrame extends JFrame
{
Timer timer;
private Container c;
public  static client ct;
private MyCanvas myCanvas;
public ClientFrame()
{
super("timeViewer");
initComponent();
setApperence();
}
public void initComponent()
{
c=getContentPane();
myCanvas=new MyCanvas();
timer = new Timer(10000,new ActionListener() {
public void actionPerformed(ActionEvent ae) 
{
myCanvas.repaint();
}
});
timer.start();
}
public void setApperence()
{
//setTitle(Application.TITLE);
setDefaultCloseOperation(EXIT_ON_CLOSE);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setSize(d.width,d.height);  
setLocation(d.width/2-getWidth()/2,d.height/2-getHeight()/2);
setLayout(null);
c.add(myCanvas);
}
public static void main(String gg[])
{
new ClientFrame().setVisible(true);
}

}
/////////////

class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener,KeyListener,MouseWheelListener	
{  
public MyCanvas() 
{
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setSize(d.width,d.height); 
addMouseListener(this); 
addKeyListener(this);
addMouseWheelListener(this);
}
public void paint(Graphics g)  
{
 super.paint(g);
try
{
g.drawImage(ClientFrame.ct.getFrame(),0,0,null);
}catch(Exception ioe)
{
System.out.println("hello"+ioe);
}
}  
 public void mousePressed(MouseEvent e)
{
client.getMousePressed(e.getXOnScreen(),e.getYOnScreen(),e.getButton());
}
public void mouseReleased(MouseEvent e)
{
client.getMouseRelease(e.getXOnScreen(),e.getYOnScreen(),e.getButton());
}
public void mouseClicked(MouseEvent e)
{
//client.getMouseClicked(e.getXOnScreen(),e.getYOnScreen(),e.getButton());
}
public void mouseEntered(MouseEvent e)
{
// do if required
}
  public void mouseExited(MouseEvent e)
{
// do if required
}
public void mouseMoved(MouseEvent e)
{
//client.getMouseMoved(e.getX(),e.getY());
}
public void mouseDragged(MouseEvent e)
{
//client.getMouseDragged(e.getX(),e.getY(),e.getButton());
}
 public void mouseWheelMoved(MouseWheelEvent e)
{
client.getMouseWheel(e.getScrollAmount());
}
public void keyPressed(KeyEvent e) 
{ 
client.getKeyPressed(e.getKeyCode());
}
 public void keyReleased(KeyEvent e) 
{  
client.getKeyReleased(e.getKeyCode());
}  
public void keyTyped(KeyEvent e) 
{
client.getKeyTyped(e.getKeyCode());
}
}
