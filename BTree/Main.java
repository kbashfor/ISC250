import static java.lang.System.out;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Color c = new Color();
		Scanner sc = new Scanner(System.in);
		String command;
		Tree<String> tree = new Tree<String>();
		String prompt = c.CYAN + "\nEnter a command (i, in, pre, post, find, or q):" + c.RESET;

		for(;;) {
			out.println(prompt);
			command = sc.next();
			switch (command) {
				case "i":
					out.println((tree.add(sc.next())) ? c.GREEN + "\nNew Node Added" + c.RESET : c.RED + "\nCannot Add Node" + c.RESET);
					break;

				case "in":
					out.println("\nTree in post-order\n==================");
					tree.traverse(Tree.Order.INORDER);
					out.println();
					break;

				case "pre":
					out.println("\nTree in pre-order\n==================");
					tree.traverse(Tree.Order.PREORDER);
					out.println();
					break;

				case "post":
					out.println("\nTree in post-order\n==================");
					tree.traverse(Tree.Order.POSTORDER);
					out.println();
					break;

				case "find":
					out.println((tree.find(sc.next())) ? c.GREEN + "\nFound it" + c.RESET : c.RED + "\nCannot find it" + c.RESET);
					break;

				case "man":
					out.println("\ni: insert [input]");
					out.println("in: print in-order");
					out.println("pre: print pre-order");
					out.println("post: print post-order");
					out.println("find: find [input]");
					out.println("q: quit");
					break;

				case "q":
					out.println("\nExiting..."); 
					System.exit(0);
			}
		}
	}
}
