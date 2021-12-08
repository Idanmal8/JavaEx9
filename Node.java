
public class Node<E> {
	private E data;
	private Node<E> leftSon, rightSon;

	public Node(E data) {
		this.data = data;
		leftSon = rightSon = null;
	}
	public E getData() { return data; }
	public Node<E> getLeftSon() { return leftSon; }
	public Node<E> getRightSon() { return rightSon; }
	public void setLeftSon(Node<E> n) { 
		leftSon = n;
		}
	public void setRightSon(Node<E> n) { rightSon = n; }
}



