//idan malka 315655647

public class BTree<E> extends Node<E> {
	public BTree(E data) {
		super(data);
	}
	protected Node<E> root;
	private int size1 = 0;
	public int count = 0;
	/**
	 * this method returns the depth of the tree
	 * @param root that is send every time as the next in line 
	 * @return a number that represent the depth 
	 * **/
	private int maxDepth(Node<E> root)
	{
		if(root == null) {
			return -1;
		}
		return Math.max(maxDepth(root.getLeftSon()) + 1 , maxDepth(root.getRightSon()) + 1);
	}
	
	public int howManyLeaves(Node<E> root) {
		if (root == null)
			return 0; 
		if (root.getLeftSon() == null && root.getRightSon() == null)   
			return 1;    
		return howManyLeaves(root.getLeftSon()) + howManyLeaves(root.getRightSon());  
	}

	public boolean isPerfect(Node<E> root) {
		int s = 0;
		if(root == null) {
			return false;
		}
		s = (maxDepth(root));

		if(howManyLeaves(root) == Math.pow(2.0, s)) {
			return true;
		}
		return false;
	}
	/**
	 * this is the main method that checks the height / depth of the tree 
	 * @return the method maxDepth with result
	 * **/
	public int height() {
		return maxDepth(root);
	}
	/**
	 * a private method that adds nodes according to the next in line .
	 * @param d as a new node that is inserted , root as the current node check
	 * @return an added node at the right place 
	 * **/
	private void addHelper(E d , Node<E> root) {
		if(root.getLeftSon() == null) {
			root.setLeftSon(new Node<E>(d));
		}
		else if(root.getRightSon() == null) {
			root.setRightSon(new Node<E>(d));
		}
		else if(maxDepth(root.getLeftSon()) - maxDepth(root.getRightSon()) <= 1) {
			addHelper(d , root.getLeftSon());
		}
		else {
			addHelper(d , root.getRightSon());
		}
	}
	/**
	 * this is the main method of add that send to addHelper to add nodes according to the terms
	 * @return the private method addHelper with a new node 
	 * 
	 * **/
	public void add(E data) {
		if(root == null) {
			root = new Node<E>(data);
			return;
		}
		addHelper(data , root);
	}
	/**
	 * a private method that printing the pre order of the tree
	 * @param root as the curr root , preOrderString as the string that is generated. 
	 * @return preOrderString that is the pre order in a string. 
	 * **/
	private String preOrder(Node<E> root , String preOrderString ) {
		if(root != null) {
			return preOrderString += root.getData() + "\t" + preOrder(root.getLeftSon() ,preOrderString ) + preOrder(root.getRightSon() , preOrderString);
		}
		return preOrderString;

	}
	/**
	 * this is the main method that send the nodes to preOrder 
	 * @return the pre order .**/
	public String pre() {
		return preOrder(root , "");
	}
	/**
	 * this method is a private method that returns a string of in order of the tree
	 * @param inOrderString as the string returned  and the curr node
	 * @return the String inOrderString
	 * **/
	private String inOrder(Node<E> root , String inOrderString) {
		if(root != null) {
			return inOrderString += inOrder(root.getLeftSon() ,inOrderString ) + root.getData() + "\t" + inOrder(root.getRightSon() , inOrderString);
		}
		return inOrderString;
	}
	/**
	 * this is the main method of in order 
	 * @return the string of the in order by the method inOrder .
	 * **/
	public String in() {
		return inOrder(root , "");	
	}
	/**
	 * a private method that printing the pre order of the tree
	 * @param root as the curr root , preOrderString as the string that is generated. 
	 * @return preOrderString that is the pre order in a string. 
	 * **/
	private String postOrder(Node<E> root , String proOrderString) {
		if(root != null) {
			return proOrderString += postOrder(root.getLeftSon() ,proOrderString ) + postOrder(root.getRightSon() , proOrderString) + root.getData() + "\t";
		}
		return proOrderString;
	}
	/**
	 * this is the main method of pro order 
	 * @return the string that is generated in pro Order 
	 * **/
	public String post() {
		return postOrder(root , "");
	}
	/**
	 * this method returns the number of nodes in the tree 
	 * @return the private method that is counting the nodes .
	 * **/
	public int size() {
		return numOfCrossWays(root);
	}
	/**
	 * this method is a private method that count the number of nodes in the tree 
	 * @param root as the node checked 
	 * @return the size of the tree
	 * **/

	private int numOfCrossWays(Node<E> root) {
		if(root == null) {
			return 0;
		}
		size1 ++;
		return 1 + numOfCrossWays(root.getLeftSon())+ numOfCrossWays(root.getRightSon());
	}

	/**
	 * this method checkes if there is a path to a leaf in the tree according to the terms(length)
	 * @param length as the length that is desired to check if is a path 
	 * @return the String of the path with the values of the nodes 
	 * **/
	public String path(int length) {
		if( maxDepth(root)<length) {
			return "";
		}
		if(length==1 && root!=null&& root.getLeftSon()==null && root.getRightSon()==null) {
			return root.getData()+"";
		}
		return stringPath(root,length,"");

	}
	/**
	 * this private method is sending a root a length and a counter to the private method pathFinder and according to the boolean that is returns it is adding to the string the value of the wanted node
	 * @param root as the curr node , length , and a certing string 
	 * @return the string of the path to the main method 
	 * **/
	private String stringPath(Node<E> root,int length ,String pathString) {
		if(length > 1 && pathFinder2(root,length,1)) {
			pathString += root.getData() + ", " ; 
		}
		else if(length == 1 && pathFinder2(root,length,1)) {
			pathString += root.getData() ; 
		}
		length--;
		if(length==0) {
			return pathString;
		}
		if(pathFinder2(root.getLeftSon() , length , 1)) {
			return stringPath(root.getLeftSon() , length , pathString);

		}else if (pathFinder2(root.getRightSon() , length  , 1)) {
			return stringPath(root.getRightSon() , length , pathString);
		}
		else {
			return "";
		}

	}
	/**
	 * this is a private method that is backtracking to check if there is path in the tree that is matching to the length 
	 * @param rootChecker that is checking if the node is a part of the path , the given length , and a counter to know that we are not exception , left as the left path check , right as the right path check.
	 * @return true or false according to the path
	 * **/
	private boolean pathFinder2(Node<E> rootChecker, int length , int counter ) {
		if(rootChecker==null) {
			return false;
		}
		if(rootChecker!=null && rootChecker.getLeftSon()==null && rootChecker.getRightSon()==null) {
			if(length==counter) {
				return true;
			}
			else {
				return false;
			}
		}
		boolean left= pathFinder2(rootChecker.getLeftSon(),length ,counter + 1);
		boolean right=pathFinder2(rootChecker.getRightSon(), length,counter + 1);
		return left||right;

	}
}





