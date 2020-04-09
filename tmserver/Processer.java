import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.InputEvent;
import java.net.*;
public class Processer extends Thread
{
private Robot robot;
private Socket client;
public Processer()
{
try
{
robot=new Robot();
}catch(Exception e)
{
System.out.println(e);
}
}
public  void startProcesser(Socket client)
{
this.client=client;
start();
}
public void run()
{
try
{
byte functionToPerform[]=new byte[1];
byte ack[]=new byte[1];
InputStream is=client.getInputStream();
OutputStream os=client.getOutputStream();
int byteCount=is.read(functionToPerform);
ack[0]=79;
os.write(ack,0,1);
os.flush();
if(functionToPerform[0]==101)
{
getFrame(client,is,os,robot);
}
if(functionToPerform[0]==102)
{
getMousedPressed(client,is,os,robot);
}
if(functionToPerform[0]==103)
{
getMousedRelease(client,is,os,robot);
}
if(functionToPerform[0]==104)
{
getMousedClicked(client,is,os,robot);
}if(functionToPerform[0]==105)
{
getMousedMoved(client,is,os,robot);
}
if(functionToPerform[0]==106)
{
getMousedWheel(client,is,os,robot);
}
if(functionToPerform[0]==107)
{
getKeyPressed(client,is,os,robot);
}
if(functionToPerform[0]==108)
{
getKeyRelease(client,is,os,robot);
}
if(functionToPerform[0]==109)
{
getKeyTyped(client,is,os,robot);
}
client.close();
}catch(Exception e)
{
System.out.println("asdfad"+e);
}
}// run ends

public static void getFrame(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{
Rectangle rect=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
BufferedImage bufferedImage = robot.createScreenCapture(rect);
ByteArrayOutputStream baos=new ByteArrayOutputStream();
ImageIO.write(bufferedImage,"png",baos);
//byte requestBytes[]=baos.toByteArray();
ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
int requestSize=baos.size();
byte requestSizeInBytes[]=new byte[4];
requestSizeInBytes[0]=(byte)(requestSize >>24);
requestSizeInBytes[1]=(byte)(requestSize >>16);
requestSizeInBytes[2]=(byte)(requestSize >>8);
requestSizeInBytes[3]=(byte)requestSize;
os.write(requestSizeInBytes,0,4);
os.flush();
byte ack[]=new byte[1];
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
int bytesToSend=requestSize;
int chunkSize=1024;
byte chunk[]=new byte[1024];
int i=0;
while(i<bytesToSend)
{
byteCount=bais.read(chunk); 
os.write(chunk,0,byteCount);
os.flush();
i=i+byteCount;

}
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
client.close();
}catch(Exception e)
{
throw new RuntimeException("serious problem:::"+e);
}
} // getFrame ends
public static void getMousedPressed(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{
byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);
byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();

String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
button=Integer.parseInt(splits[2]);


robot.mouseMove(x,y);

if(button==1) 
{
robot.mousePress(InputEvent.BUTTON1_MASK);
}
if(button==2) 
{
robot.mousePress(InputEvent.BUTTON2_MASK);

}
if(button==3) 
{
robot.mousePress(InputEvent.BUTTON3_MASK);
}
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}


}
public static void getMousedRelease(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();

String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
button=Integer.parseInt(splits[2]);


robot.mouseMove(x,y);

if(button==1) 
{
robot.mouseRelease(InputEvent.BUTTON1_MASK);
}
if(button==2) 
{
robot.mouseRelease(InputEvent.BUTTON2_MASK);

}
if(button==3) 
{
robot.mouseRelease(InputEvent.BUTTON3_MASK);
}
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMousedClicked(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();

String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
button=Integer.parseInt(splits[2]);
robot.mouseMove(x,y);
if(button==1) 
{
robot.mousePress(InputEvent.BUTTON1_MASK);
robot.mouseRelease(InputEvent.BUTTON1_MASK);

}
if(button==2) 
{
robot.mousePress(InputEvent.BUTTON2_MASK);
robot.mouseRelease(InputEvent.BUTTON2_MASK);

}
if(button==3) 
{
robot.mousePress(InputEvent.BUTTON3_MASK);
robot.mouseRelease(InputEvent.BUTTON3_MASK);
}
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMousedMoved(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
robot.mouseMove(x,y);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMousedWheel(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.mouseWheel(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyPressed(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.keyPress(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyRelease(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.keyRelease(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyTyped(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.keyPress(x);
robot.keyRelease(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}


}// class ends
