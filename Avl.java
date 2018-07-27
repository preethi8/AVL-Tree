package preethi;
public class Avl {
	static Node root;
	Node par;
	class Node
	{
		int key;
		Node parent;
		Node rchild;
		Node lchild;
		int height;
		Node(int d)
		{
			key=d;
			height=0;
		}
	}
	public int height(Node n)
	{
		if(n==null)
			return -1;
		else
			return n.height;
	}
	public int maxHeight(int lh,int rh)
	{
		if(lh>rh)
			return lh;
		else
			return rh;
	}
	int updateHeight(Node r)
	{
		if(r==null)
			return -1;
		else
			return 1+maxHeight(height(r.lchild),height(r.rchild));
	}

int checkBalance(Node n)
{
	if(n==null)
		return 0;
	else
		return (height(n.lchild)- height(n.rchild));
}
Node insertBST(Node r,Node n)
{
	if(r==null)
	  {
		  root=n;
	  }
	  else if(r.key>n.key)
	 {
		 if(r.lchild==null)
		 {
			 r.lchild=n;
			 n.parent=r;
		}
		 else
		 {
			 insertBST(r.lchild,n);
		 }
	  }
	 else
	 {
		 if(r.rchild==null)
		 {
			 r.rchild=n;
			 n.parent=r;
		}
		 else
		 {
			 insertBST(r.rchild,n);
		 }
	 }
	return n;
}
	Node insertAVL(Node r,int data)
{
	Node n=new Node(data);
	Node node=insertBST(r,n);
	Node par=node.parent;
	while(par!=null)
	{
		par.height=updateHeight(par);
		//System.out.println(par.height);
	 int bal=checkBalance(par);
	 if(bal>1&&par.lchild.key>node.key)
	 {
		 return balanceLeftSt(par);
		
	 }
	 if(bal>1&&par.lchild.key<node.key)
	 {
		return balanceLeftZigZag(par);
		
	 }
	 if(bal<-1&&par.rchild.key<node.key)
	 {
		 return balanceRightSt(par);
		 
	 }
	 if(bal<-1&&par.rchild.key>node.key)
	 {
		 return balanceRightZigZag(par);
	 }
	 par=par.parent;
	}
	return par;
	}
Node balanceLeftSt(Node x)
{
	Node y=x.lchild;
	Node tmp1=x.parent;
	Node t=y.rchild;
	y.rchild=x;
	x.lchild=t;
	if(t!=null)
	{
	t.parent=x;
	}
	x.parent=y;
	y.parent=tmp1;
	if(tmp1==null)
		root=y;
	else if(tmp1.key>y.key)
		tmp1.lchild=y;
	else
		tmp1.rchild=y;
	 x.height=updateHeight(x);
	 y.height=updateHeight(y);
	return y.parent;
}
Node balanceLeftZigZag(Node t1)
{
	Node t2=t1.lchild;
	Node x=balanceRightSt(t2);
	balanceLeftSt(x);
	Node y=x.lchild;
	x.height=updateHeight(x);
	if(y!=null)
	{
	y.height=updateHeight(y);
	return y;
	}
	return x;
}
Node balanceRightSt(Node x)
{
	Node y=x.rchild;
	Node tmp1=x.parent;
	Node t=y.lchild;
	y.lchild=x;
	x.rchild=t;
	if(t!=null)
	{
	t.parent=x;
	}
	x.parent=y;
	y.parent=tmp1;
	if(tmp1==null)
		root=y;
	else if(tmp1.key>y.key)
		tmp1.lchild=y;
	else
		tmp1.rchild=y;
	 x.height=updateHeight(x);
	 y.height=updateHeight(y);
	return y.parent;
}
Node balanceRightZigZag(Node t1)
{
	Node t2=t1.rchild;
	Node x=balanceLeftSt(t2);
	balanceRightSt(x);
	Node y=x.rchild;
	x.height=updateHeight(x);
	if(y!=null)
	{
	 y.height=updateHeight(y);
	 return y;
	}
	 return x;
}
void inorder(Node v)
{
	if(v==null)
		return;
	inorder(v.lchild);
	System.out.print(v.key+"-"+v.height+" ");
	inorder(v.rchild);
}

public static void main(String args[])
{
Avl tree=new Avl();
tree.insertAVL(root,40);

tree.insertAVL(root,20);
//System.out.println(root.key);
tree.insertAVL(root,10);
//System.out.println(root.key);
tree.insertAVL(root,15);
tree.insertAVL(root,13);
tree.insertAVL(root,60);
tree.insertAVL(root,80);
tree.insertAVL(root,100);
tree.insertAVL(root,90);
tree.insertAVL(root,120);
tree.insertAVL(root,85);
tree.insertAVL(root,12);
tree.insertAVL(root,14);
tree.insertAVL(root,11);
tree.insertAVL(root,4);
tree.insertAVL(root,1);
tree.insertAVL(root,3);
tree.insertAVL(root,2);
tree.inorder(root);
System.out.println("\nRoot is "+root.key);
}
}
