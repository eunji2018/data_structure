/**
 * @Author eunji
 */
package demo;

//根据遍历序列构造二叉树
//遍历序列默认合法
public class TraverseSequence {

    private static int [] preorder;
    private static int [] inorder;
    private static int [] postorder;

    public static void main(String[] args) {
        int [] pre = {1,4,2,7,3,5,6,8};
        int [] in = {4,7,2,1,5,3,8,6};
        int [] post = {7,2,4,5,8,6,3,1};
        preorder = pre;
        inorder = in;
        postorder = post;
        TreeNode root;
        System.out.println("根据前序，中序构造");
        root = preIn(0, preorder.length-1, 0, inorder.length-1);
        postTraverse(root);//7,2,4,5,8,6,3,1

        System.out.println();
        System.out.println("根据中序，后序构造");
        root = postIn(0, inorder.length-1, 0, postorder.length-1);
        preTraverse(root);//1,4,2,7,3,5,6,8
    }

    private static class TreeNode {

        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    //根据前序，中序遍历构造二叉树
    private static TreeNode preIn(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight)
            return null;
        TreeNode root = new TreeNode(preorder[preLeft]);
        int index = inLeft;
        while (index <= inRight) {
            if (preorder[preLeft] == inorder[index])
                break;
            index++;
        }
        root.left = preIn(preLeft+1, preLeft+index-inLeft, inLeft, index-1);
        root.right = preIn(preLeft+index-inLeft+1, preRight, index+1, inRight);
        return root;
    }

    //根据中序，后序遍历构造二叉树
    private static TreeNode postIn(int inLeft, int inRight, int postLeft, int postRight) {
        if (postLeft > postRight)
            return null;
        TreeNode root = new TreeNode(postorder[postRight]);
        int index = inLeft;
        while (index <= inRight) {
            if (postorder[postRight] == inorder[index])
                break;
            index++;
        }
        root.left = postIn(inLeft, index-1, postLeft, postLeft+index-inLeft-1);
        root.right = postIn(index+1, inRight, postLeft+index-inLeft, postRight-1);
        return root;
    }

    private static void preTraverse(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preTraverse(node.left);
            preTraverse(node.right);
        }
    }

    private static void postTraverse(TreeNode node) {
        if (node != null) {
            postTraverse(node.left);
            postTraverse(node.right);
            System.out.print(node.value + " ");
        }
    }
}
