class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class ConstructBST {

    public static TreeNode insertNode(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else {
            root.right = insertNode(root.right, val);
        }
        return root;
    }

    private static void inOrder(TreeNode root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        int[] arr = new int[] { 12, 5, 8, 19, 20, 15 };
        for (int node : arr) {
            root = insertNode(root, node);
        }

        inOrder(root);

    }
}