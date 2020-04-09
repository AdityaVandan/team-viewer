import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;
public class client
{

// yaha par process method likna he code x,y,button initial value 0;
public static BufferedImage getFrame()
{
try
{
Socket socket=new Socket("localhost",5000);
byte ack[]=new byte[1];
ack[0]=101; // getFrame
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
byte requestLengthInBytes[]=new byte[4];
byteCount=is.read(requestLengthInBytes);
int requestLength=(requestLengthInBytes[0] & 0xFF) <<24 | (requestLengthInBytes[1] & 0xFF) <<16 |(requestLengthInBytes[2] & 0xFF) <<8 | (requestLengthInBytes[3] & 0xFF);
ack[0]=79;
os.write(ack,0,1);
os.flush();
ByteArrayOutputStream baos=new ByteArrayOutputStream();
int bytesToRead=requestLength;
byte chunk[]=new byte[1024];
while(bytesToRead>0)
{
byteCount=is.read(chunk);
if(byteCount>0)
{
baos.write(chunk,0,byteCount);
}
bytesToRead-=byteCount;
}
ack[0]=79;
os.write(ack,0,1);
os.flush();
ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
BufferedImage image=ImageIO.read(bais);
bais.close();
os.close();
is.close();
baos.close();
socket.close();
return image;
}catch(Exception e)
{
System.out.println(e+"asdfa");
}
return null;
}
public static void getMousePressed(int x,int y,int button)
{

try
{
Socket socket=new Socket("localhost",5000);
byte ack[]=new byte[1];
ack[0]=102; // getMousePressed
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+","+button+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}




}
public static void getMouseRelease(int x,int y,int button)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=103; // getMouseRelease
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+","+button+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMouseClicked(int x,int y,int button)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=104; // getMouseClicked
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+","+button+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMouseMoved(int x,int y)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=105; // getMouseMoved
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMouseWheel(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=106; // getMouseWheel
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyPressed(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=107; // getKeyPressed
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyReleased(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=108; // getKeyReleased
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyTyped(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=109; // getKeyTyped
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static File[] getFiles()
{
// yet to implement
return null;
}
public static void main(String gg[])
{
//BufferedImage image=client.getFrame();
}

}