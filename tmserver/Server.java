import java.net.*;
import java.io.*;
public class Server
{
public  int portNumber;
public Server()
{
this.portNumber=5000;
}
public void startServer()
{
startServer(this.portNumber);
}
public void startServer(int portNumber)
{
this.portNumber=portNumber;
try
{
ServerSocket serverSocket=new ServerSocket(this.portNumber);
Socket socket;
while(true)
{
System.out.println("Server is listening at portNumber"+this.portNumber);
socket=serverSocket.accept();
new Processer().startProcesser(socket);
}
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public static void main(String gg[])
{
new Server().startServer();
}
}