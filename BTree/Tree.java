import static java.lang.System.out;

public class Tree <T extends Comparable<T>> {
	
	public enum Order { PREORDER, INORDER, POSTORDER };
	Color c = new Color();
	Node<T> root;
	Node<T> current;

	public class Node<T> {
		T data;
		Node<T> left, right;
		public Node(T d, Node<T> left, Node<T> right) {
			this.data = d;
			this.left = left;
			this.right = right;
		}

		public T data() { return this.data; }
		
		@Override
		public String toString() { return this.data.toString(); }
	}

	private boolean findR(T key, Node<T> node) {
		if (key.compareTo(node.data()) == 0 && node != null) {
			// out.println(c.GREEN + "DEBUG: found it" + c.RESET);
			return true;
		} else if (key.compareTo(node.data()) < 0 && node.left != null) {
			// out.println(c.YELLOW + "DEBUG: find left" + c.RESET);
			return findR(key, node.left);
		} else if (key.compareTo(node.data()) > 0 && node.right != null) {
			// out.println(c.PURPLE + "DEBUG: find right" + c.RESET);
			return findR(key, node.right);
		} else {
			// out.println(c.RED + "DEBUG: doesnt exists" + c.RESET);
			return false;
		}
	}

	public boolean find(T key) { return ((root == null) ? false : findR(key, root)); }

	private boolean addR(T key, Node<T> node) {
		if (key.compareTo(node.data) < 0 && node.left == null) {
			// out.println(c.BLUE + "DEBUG: add left" + c.RESET);
			node.left = new Node<T>(key, null, null);
			return true;
		} else if (key.compareTo(node.data) < 0 && node.left != null) {
			// out.println(c.YELLOW + "DEBUG: go left" + c.RESET);
			return addR(key, node.left);
		} else if (key.compareTo(node.data) > 0 && node.right == null) {
			// out.println(c.BLUE + "DEBUG: add right" + c.RESET);
			node.right = new Node<T>(key, null, null);
			return true;
		} else if (key.compareTo(node.data) > 0 && node.right != null) {
			// out.println(c.PURPLE + "DEBUG: go right" + c.RESET);
			return addR(key, node.right);
		} else {
			// out.println(c.RED + "DEBUG: already exists" + c.RESET);
			return false;
		}
	}

	public boolean add(T key){ 
		if (root == null) {
			// out.println(c.BLUE + "DEBUG: set root" + c.RESET);
			this.root = new Node<T>(key, null, null);
			return true;
		} else {
			return addR(key, root);
		} 
	}

	public void traverse(Order order) {
		do_traverse(order, root);
	}

	private void do_traverse(Order order, Node<T> node) {
		if (node != null) {
			switch (order) {
				case PREORDER:
					out.print(c.PURPLE + "[" + node.toString() + "] " + c.RESET);
					do_traverse(order, node.left);
					do_traverse(order, node.right);
					break;
				case INORDER:
					do_traverse(order, node.left);
					out.print(c.YELLOW + "[" + node.toString() + "] " + c.RESET);
					do_traverse(order, node.right);
					break;
				case POSTORDER:
					do_traverse(order, node.left);
					do_traverse(order, node.right);
					out.print(c.GREEN + "[" + node.toString() + "] " + c.RESET);
					break;
			}
		}
	}
}
