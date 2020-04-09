import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
class FileBrowser implements Runnable 
{
//private client client1;
private DefaultMutableTreeNode root,serverRoot;
private DefaultTreeModel treeModel,serverTreeModel;
private JTree tree,serverTree;
private JPanel northPanel,leftPanel,rightPanel,southPanel;
public static JPanel homePanel;
private File fileRoot,serverFileRoot;
private JScrollPane scrollPane,serverScrollPane;
private  JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
private JButton reciveButton,sendButton;
private JLabel label1,label2;
private File[] serverFiles;
public void run()
{
initComponent();
leftPanel.setLayout(new BorderLayout());
leftPanel.add(scrollPane,"Center");
rightPanel.setLayout(new BorderLayout());
rightPanel.add(serverScrollPane,"Center");
splitPane.setLeftComponent(leftPanel);
splitPane.setRightComponent(rightPanel);
splitPane.setResizeWeight(0.5);
southPanel.add(label1);
southPanel.add(label2);
northPanel.add(sendButton);
northPanel.add(reciveButton);
homePanel.setLayout(new BorderLayout());
homePanel.add(splitPane,"Center");
homePanel.add(northPanel,"North");
homePanel.add(southPanel,"South");
// for multi divers we can put it in while loop
CreateChildrenNode ccn=new CreateChildrenNode(fileRoot,root);
new Thread(ccn).start();

// for multi divers we can put it in while loop
ServerChildrenNode ccnc=new ServerChildrenNode(serverFileRoot,serverRoot,serverFiles);
new Thread(ccnc).start();


//////////////////// event programing//////////////////////////////////////////////////////////////////////////
tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
public void valueChanged(TreeSelectionEvent e) {
label1.setText(e.getPath().toString());
//System.out.println(e.getPath());
}
});
serverTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
public void valueChanged(TreeSelectionEvent e) {
label2.setText(e.getPath().toString());
//System.out.println(e.getPath());
}
});

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// yah par button se add or remove karna he tree me
}

public void initComponent()
{
label1=new JLabel();
label2=new JLabel();
reciveButton=new JButton("recive");
sendButton=new JButton("Send");
northPanel=new JPanel();
homePanel=new JPanel();
southPanel=new JPanel();
leftPanel=new JPanel();
rightPanel=new JPanel();
fileRoot=new File("D:/");
root=new DefaultMutableTreeNode(new FileNode(fileRoot));
treeModel=new DefaultTreeModel(root);
tree=new JTree(treeModel);
tree.setShowsRootHandles(true);
scrollPane=new JScrollPane(tree);

/*try
{
serverFiles=client1.getFiles();
}catch(Exception e)
{
System.out.println(e.getMessage());
}*/
serverFileRoot=new File("E:/");
serverRoot=new DefaultMutableTreeNode(new FileNode(serverFileRoot));
serverTreeModel=new DefaultTreeModel(serverRoot);
serverTree=new JTree(serverTreeModel);
serverTree.setShowsRootHandles(true);
serverScrollPane=new JScrollPane(serverTree);
serverFiles=serverFileRoot.listFiles();
}
}
class CreateChildrenNode implements Runnable
{
private File fileRoot;
private DefaultMutableTreeNode root;
CreateChildrenNode(File fileRoot,DefaultMutableTreeNode node)
{
this.fileRoot=fileRoot;
this.root=node;
}
public void run()
{
createChildren(fileRoot,root);
}
public void createChildren(File fileRoot,DefaultMutableTreeNode node)
{
File files[]=fileRoot.listFiles();
if(files==null) return;
for(File file:files)
{
DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(new FileNode(file));
node.add(childNode);
//System.out.println(file.getName());
if(file.isDirectory())
{
createChildren(file,childNode);
}
}
}
}
/////////////////////////////////////////
class ServerChildrenNode implements Runnable
{
private File fileRoot;
private DefaultMutableTreeNode root;
private File[] serverFiles;
private boolean flag=false;
ServerChildrenNode(File fileRoot,DefaultMutableTreeNode node, File[] serverFiles)
{
this.fileRoot=fileRoot;
this.root=node;
this.serverFiles=serverFiles;
}
public void run()
{
createChildren(fileRoot,root);
}
public void createChildren(File fileRoot,DefaultMutableTreeNode node)
{
File files[];
if(flag)
{
files=fileRoot.listFiles();
}
else
{
files=serverFiles;
flag=true;
}
if(files==null) return;
for(File file:files)
{
DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(new FileNode(file));
node.add(childNode);
//System.out.println(file.getName());
if(file.isDirectory())
{
createChildren(file,childNode);
}
}
}
}

////////////////////////
class FileNode	
{
private File file;
FileNode(File fileRoot)
{
this.file=fileRoot;
}
public String toString()
{
String name=file.getName();
if(name.equals(""))
{
return file.getAbsolutePath();
}
else
{
return name;
}
}
}
/////////////////////////
class MainFrame extends JFrame
{
private Container c;
private FileBrowser fileBrowser;
MainFrame()
{
super("File Transfer");
initComponent();
setApperence();
}
public void initComponent()
{
c=getContentPane();
//SwingUtilities.invokeLater(new FileBrowser());
}
public void setApperence()
{
setDefaultCloseOperation(EXIT_ON_CLOSE);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setSize(d.width,d.height);  
setLocation(d.width/2-getWidth()/2,d.height/2-getHeight()/2);
setLayout(new BorderLayout());
c.add(FileBrowser.homePanel,"Center");
//FileBrowser.splitPane.setBounds(0,0,d.width,d.height);

}
public static void main(String fgg[])
{
SwingUtilities.invokeLater(new FileBrowser());
new MainFrame().setVisible(true);
}
}
