package Stack;

import java.util.LinkedList;
import java.util.Queue;

public class TreeView {


    static class TreeNode {
        String  val;
        TreeNode left;
        TreeNode right;

        public TreeNode(String val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

   static class Tree {
        private TreeNode root;

        public Tree() {
            root = null;
        }

        public void add(String val) {
            root = addNode(root, val);
        }

        private TreeNode addNode(TreeNode current, String val) {
            if (current == null) {
                return new TreeNode(val);
            }

            // Perform level order traversal to find the appropriate position to insert the new node
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(current);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node.left == null) {
                    node.left = new TreeNode(val);
                    return current;
                } else {
                    queue.add(node.left);
                }

                if (node.right == null) {
                    node.right = new TreeNode(val);
                    return current;
                } else {
                    queue.add(node.right);
                }
            }

            return current;
        }

        public void delete(String val) {
            root = deleteNode(root, val);
        }

        private TreeNode deleteNode(TreeNode current, String val) {
            if (current == null) {
                return null;
            }

            if (current.val == val) {
                // Node to be deleted found

                // Case 1: Node is a leaf node
                if (current.left == null && current.right == null) {
                    return null;
                }

                // Case 2: Node has only one child
                if (current.left == null) {
                    return current.right;
                }
                if (current.right == null) {
                    return current.left;
                }

                // Case 3: Node has two children
                TreeNode minValueNode = findMinValueNode(current.right);
                current.val = minValueNode.val;
                current.right = deleteNode(current.right, minValueNode.val);
                return current;
            }

            // Traverse the tree to find the node to be deleted
            current.left = deleteNode(current.left, val);
            current.right = deleteNode(current.right, val);
            return current;
        }

        private TreeNode findMinValueNode(TreeNode current) {
            TreeNode minNode = current;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            return minNode;
        }

        public void printTreeView() {
            if (root == null) {
                System.out.println("Tree is empty.");
                return;
            }

            printTreeView(root, "");
        }

        private void printTreeView(TreeNode current, String indent) {
            if (current == null) {
                return;
            }

            System.out.println(indent + "*--->" + current.val);
            indent += "     ";

            printTreeView(current.left, indent);
            printTreeView(current.right, indent);
        }
    }


        public static void main(String[] args) {
            Tree tree = new Tree();

            tree.add("Root");
            tree.add("Employee Id2");
            tree.add("Employee Id3");
            tree.add("Employee Id4");
            tree.add("Employee Id5");
            tree.add("Employee Id6");
            tree.add("Employee Id7");

            System.out.println("Tree View:");
            tree.printTreeView();

            tree.delete("Id5");

            System.out.println("\nTree View after deleting node Employee Id5:");
            tree.printTreeView();
        }
    }


