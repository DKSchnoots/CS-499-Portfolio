package contactservice;

class TreeNode {
    Contact contact;
    TreeNode left;
    TreeNode right;

    TreeNode(Contact contact) {
        this.contact = contact;
        this.left = null;
        this.right = null;
    }
}
