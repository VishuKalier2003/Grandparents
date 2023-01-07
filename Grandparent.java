/* Given a Root Node of the Binary Tree, find the sum of all nodes whose Grandparent nodes are even... Return the sum of these Nodes as a function call...
 * Eg 1: Nodes = [1, 2, -1, -1, 4, 3, 5, -1, -1, 7, -1, -1, 6, -1, -1,]   n = 15        Sum = 5 + 7 = 12
 * Eg 2: Nodes = [6, 7, 2, 9, -1, -1, -1, 7, 1, -1, -1, 4, -1, -1, 8, 1, -1, -1, 3, -1, 5, -1, -1]         n = 23     Sum = 2 + 7 + 1 + 3 + 5 = 18
 */
import java.util.*;
public class Grandparent      // Grandparent class defined...
{
    public class Node     // Node class defined...
    {
        public int data;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int value)
        {
            this.data = value;
            this.left = this.right = this.parent = null;
        }
    }
    public class Binary    // Binary Tree class defined...
    {
        public static int index = -1;
        public Node InsertNode(int nodes[])
        {
            index++;
            if(nodes[index] == -1)
                return null;
            Node node = new Node(nodes[index]);    // Inserting Nodes...
            node.left = InsertNode(nodes);      // Left child recursive call...
            node.right = InsertNode(nodes);     // Right child recursive call...
            return node;
        }
        public void PrintTree(Node root)
        {
            if(root == null)
                return;
            if(root.left != null)    // The left child parent is the root node...
                root.left.parent = Parent(root, root.left.data);   // Get the Parent of Left Child...
            if(root.right != null)   // The right child parent is the root node...
                root.right.parent = Parent(root, root.right.data); // Get the Parent of Right Child...
            System.out.println("Node: "+root.data);
            if(root.left != null && root.parent != null && root.left.parent != null)
            {
                System.out.println("\t"+root.left.data+" ---> "+root.left.parent.data+"\t Parent ( Present )");
                System.out.println("\t"+root.data+" ---> "+root.left.data+"\t Left ( Occupied )");
            }
            else
            {
                System.out.println("\t"+root.data+"\t\t Parent Right ( Empty )");
                System.out.println("\t"+root.data+"\t\t Left ( Empty )");
            }
            if(root.right != null && root.parent != null && root.right.parent != null)
            {
                System.out.println("\t"+root.right.data+" ---> "+root.right.parent.data+"\t Parent ( Present )");
                System.out.println("\t"+root.data+" ---> "+root.right.data+"\t Right ( Occupied )");
            }
            else
            {
                System.out.println("\t"+root.data+"\t\t Parent Right ( Empty )");
                System.out.println("\t"+root.data+"\t\t Right ( Empty )");
            }
            PrintTree(root.left);     // PreOrder recursive call...
            PrintTree(root.right);
            return;
        }
        public Node Parent(Node root, int target)  // The target is always at a lower level than the root node...
        {
            if(root == null || root.data == target)
                return null;    // If root node is itself target or null node...
            if((root.left != null && root.left.data == target) || (root.right != null && root.right.data == target))      // Finding parent Node...
                return root;
            return root;
        }
        public int EvenGrandparent(Node root)
        {
            if(root == null)
                return 0;
            int sum = 0;    // Sum variable declared... This sum variable is also stored in recursive call and all the operations are done collectively...
            if(root.parent != null)
            {
                if(root.parent.parent != null)    // If grandparent node is not null...
                {
                    if(root.parent.parent.data % 2 == 0)
                        sum = sum + root.data;    // Sum of the grandchildren nodes is stored...
                    System.out.println(sum+", ");
                }
            }
            sum = sum + EvenGrandparent(root.left);    // In each recursive call the summation is added...
            sum = sum + EvenGrandparent(root.right);   // In each recursive call the summation is added...
            return sum;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter th number of nodes including the null nodes : ");
        x = sc.nextInt();
        int tree[] = new int[x];
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the "+(i+1)+" th node value : ");
            tree[i] = sc.nextInt();
        }
        Grandparent grandparent = new Grandparent();    // Super class defined...
        Binary binary = grandparent.new Binary();     // Sub class defined...
        Node root = binary.InsertNode(tree);
        binary.PrintTree(root);
        System.out.println(binary.EvenGrandparent(root));    // Function called...
        sc.close();
    }
}
