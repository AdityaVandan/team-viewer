import java.io.*;
import java.net.*;
class server
{
private ServerSocket serverSocket;
server() throws IOException
{
serverSocket=new ServerSocket(5000);
startListening();
}
public void startListening()
{ try
{
Socket client;
InputStream is;
OutputStream os;
byte requestLengthInBytes[]=new byte[4];
int requestLength;
int byteCount;
int bytesToRead;
int bytesToWrite;
byte ack[]=new byte[1];
ByteArrayOutputStream baos;
byte requestBytes[];
byte chunk[]=new byte[1024];
ByteArrayInputStream bais;
ObjectInputStream ois;
ObjectOutputStream oos;
String response;
byte responseBytes[];
byte responseLengthInBytes[];
int responseLength;
int chunkSize;
while(true)
{
System.out.println("Server is ready and is listening on port 5000......");
client=serverSocket.accept();
System.out.println("Request arrived...");
is=client.getInputStream();
byteCount=is.read(requestLengthInBytes);
requestLength=(requestLengthInBytes[0] & 0xFF) <<24 | (requestLengthInBytes[1] & 0xFF) <<16 |
(requestLengthInBytes[2] & 0xFF) <<8 | (requestLengthInBytes[3] & 0xFF);
ack[0]=79;
os=client.getOutputStream();
os.write(ack,0,1);
os.flush();
baos=new ByteArrayOutputStream();
bytesToRead=requestLength;
while(bytesToRead>0)
{
byteCount=is.read(chunk);
if(byteCount>0)
{
baos.write(chunk,0,byteCount);
}
bytesToRead-=byteCount;
} ack[0]=79;
os.write(ack,0,1);
os.flush();
requestBytes=baos.toByteArray();
bais=new ByteArrayInputStream(requestBytes);
ois=new ObjectInputStream(bais);
String requestObject=(String)ois.readObject();
System.out.println(requestObject);
File fileRoot=new File("E:/");
File files[]=fileRoot.listFiles();
baos=new ByteArrayOutputStream();
oos=new ObjectOutputStream(baos);
oos.writeObject(files);
oos.flush();
responseBytes=baos.toByteArray();
responseLength=responseBytes.length;
responseLengthInBytes=new byte[4];
responseLengthInBytes[0]=(byte)(responseLength>>24);
responseLengthInBytes[1]=(byte)(responseLength>>16);
responseLengthInBytes[2]=(byte)(responseLength>>8);
responseLengthInBytes[3]=(byte)responseLength;
os.write(responseLengthInBytes,0,4);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
bytesToWrite=responseLength;
chunkSize=1024;
int i=0;
while(bytesToWrite>0)
{ if(bytesToWrite<chunkSize) chunkSize=bytesToWrite;
os.write(responseBytes,i,chunkSize);
os.flush();
i+=chunkSize;
bytesToWrite-=chunkSize;
}
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
client.close();
}}
catch(Exception e)
{
System.out.println(e);
}
}
public static void main(String gg[])
{
 try
{
server s=new server();
}catch(IOException ioe)
{
System.out.println(ioe);
}}}