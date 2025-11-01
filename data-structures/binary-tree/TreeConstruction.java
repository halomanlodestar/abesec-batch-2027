import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class TreeConstruction {

    private static List<List<Integer>> bfs2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> inner = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                inner.add(node.data);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            ans.add(inner);
        }
        return ans;
    }

    private static List<Integer> leftView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    ans.add(node.data);
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return ans;
    }

    private static List<Integer> rightView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    ans.add(node.data);
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return ans;
    }

    private static void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    private static void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    private static List<Integer> boundary(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Set<TreeNode> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0 || i == size - 1) {
                    set.add(node);
                }
                if (node.left == null && node.right == null) {
                    set.add(node);
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        set.stream().forEach(node -> ans.add(node.data));
        return ans;
    }

    private static List<Integer> bfs(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.data);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        return ans;
    }

    private static boolean solveFindPathMatch(TreeNode root, int[] path, int index) {
        if (root == null || index >= path.length || root.data != path[index]) {
            return false;
        }

        if (root.left == null && root.right == null && index == path.length - 1) {
            return true;
        }

        return solveFindPathMatch(root.left, path, index + 1)
                || solveFindPathMatch(root.right, path, index + 1);
    }

    private static boolean findPathMatch(TreeNode root, int[] path) {
        if (path == null || path.length == 0) {
            return false;
        }
        return solveFindPathMatch(root, path, 0);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(7);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(6);
        root.right.right.left = new TreeNode(2);
        root.right.right.right = new TreeNode(7);
        root.right.right.left.right = new TreeNode(8);
        root.right.right.right.left = new TreeNode(10);

        // List<Integer> result = boundary(root);
        // result.stream().forEach(ele -> System.out.print(ele + " "));
        // System.out.println();
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

        System.out.println(findPathMatch(root, new int[] { 2, 8, 4, 2 }));
    }
}
