package IsBinaryTree;

class Node {
    int data;
    Node left;
    Node right;
}

public class Solution {

    int maxValue(Node nodo) {
        int max = nodo.data - 1;
        while(nodo != null) {
            if(nodo.data > max) {
                max = nodo.data;
            }
            if(nodo.right == null) {
                return max;
            }
            nodo = nodo.right;
        }

        return max;
    }

    int minValue(Node nodo) {
        int min = nodo.data + 1;
        while(nodo != null) {
            if(nodo.data < min) {
                min = nodo.data;
            }
            if (nodo.left == null) {
                return min;
            }
            nodo = nodo.left;
        }
        return min;
    }

    boolean checkBST(Node root) {
        if(root.left != null) {
            if(this.maxValue(root.left) >= root.data) {
                return false;
            }
        }

        if(root.right != null) {
            if(this.minValue(root.right) <= root.data) {
                return false;
            }
        }

        if(root != null) {
            if(root.right != null){
                if(!checkBST(root.right)){
                    return false;
                }
            }
            if(root.left != null) {
                if(!checkBST(root.left)) {
                    return false;
                }
            }
        }

        return true;
    }
}
