class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public static void inOrdertraverse(TreeNode root) {
        if (root == null)
            return;
        inOrdertraverse(root.left);
        System.out.print(root.val + " ");
        inOrdertraverse(root.right);
    }

    public static void preOrdertraverse(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrdertraverse(root.left);
        preOrdertraverse(root.right);
    }

    public static void postOrdertraverse(TreeNode root) {
        if (root == null)
            return;
        postOrdertraverse(root.left);
        postOrdertraverse(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        inOrdertraverse(root);
        preOrdertraverse(root);
        postOrdertraverse(root);
    }
}
