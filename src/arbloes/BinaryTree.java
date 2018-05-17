/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbloes;

/**
 *
 * @author Sebastian
 */
public class BinaryTree {
    private NodeA root = null;

    public BinaryTree() {
        
    }

    public NodeA getRoot() {
        return root;
    }
    
    public void insert(int value){
        NodeA node = new NodeA(value);
        
        if(this.root == null){
            this.root = node;
        }
        else{
            NodeA temp = this.root;
            NodeA pather = null;
            while(temp != null){
                pather =temp;
                if (value < temp.getValue()){
                    temp = temp.getLeft();
                }
                else{
                    temp = temp.getRight();
                }
            }
            if (value < pather.getValue()){
                pather.setLeft(node); 
            }
            else{
                pather.setRight(node);
            }           
        }
    }
    
    public void delete (int value){
        NodeA temp = this.root;
        NodeA parent = null;
        boolean left = false;
        while (temp != null){
            if(temp.getValue() == value){
                break;
            }
            else{
                parent = temp;
                
                if (value < temp.getValue()){
                    temp = temp.getLeft();
                    left = true;
                }
                else{
                    temp = temp.getRight();
                    left = false;
                }
            }
        }
        if (temp != null){
            int counter = offspring(temp);
            
            if (counter == 0){
                if(left){
                    parent.setLeft(null);
                }
                else{
                    parent.setRight(null);
                } 
            }
            else{
                if (counter == 1){
                    if(temp.getLeft() != null){
                        if(left){
                            parent.setLeft(temp.getLeft());
                        }
                        else{
                            parent.setRight(temp.getLeft());
                        }
                    }
                    else{
                        if(left){
                            parent.setLeft(temp.getRight());
                        }
                        else{
                            parent.setRight(temp.getRight());
                        }
                    }
                }
                else{
                    NodeA less = temp.getRight();
                    NodeA less_parent = less;
                    while(less.getLeft() != null){ 
                        less_parent = less;
                        less = less.getLeft();
                    }
                    if(!less_parent.equals(less)){
                        if(less.getRight() != null){
                            less_parent.setLeft(less.getRight());
                        }
                         less.setRight(temp.getRight());
                    }                                                
                    less.setLeft(temp.getLeft());
                    if(left){
                        parent.setLeft(less);
                    }
                    else{
                        parent.setRight(less); 
                    }
                }
            }            
        }        
    }
    
    public void leftRotation(){
        NodeA parent = this.root;
        NodeA temp = parent.getRight();
        if (temp.getLeft() == null){
            temp.setLeft(parent);
        }
        else{
            NodeA less = temp.getRight();
//            NodeA less_parent = less;
            while(less.getLeft() != null){ 
//                less_parent = less;
                less = less.getLeft();
            }
            less.setLeft(temp.getLeft());            
            temp.setLeft(parent);
        }
        parent.setRight(null);
        this.root = temp;
    }
    
    public void rightRotation(){
        NodeA parent = this.root;
        NodeA temp = parent.getLeft();
        if (temp.getRight()== null){
            temp.setRight(parent);
        }
        else{
            NodeA higher = temp.getLeft();
            NodeA higher_parent = higher;
            while(higher.getRight()!= null){ 
                higher_parent = higher;
                higher = higher.getRight();
            }
            higher.setRight(temp.getRight());            
            temp.setRight(parent);
        }
        parent.setLeft(null);
        this.root = temp;
    }
    
    public NodeA search(int value){
        NodeA temp = this.root;
        while (temp != null){
            if(temp.getValue() == value){
                return temp;
            }
            else{
                if (value < temp.getValue()){
                    temp = temp.getLeft();
                }
                else{
                    temp = temp.getRight();
                }
            }
        }
        return null;
    }
    
    public int offspring(NodeA node){
        return (node.getLeft() != null) ? (node.getRight() != null ? 2 : 1) : (node.getRight() != null ? 1 : 0);
    }
    
    public void in_pre2past (String preorder, String inordet){
        BinaryTree recovery = new BinaryTree();
        recovery = recovery_subtree_inpre(inordet, preorder);
        recovery.posorder_print(recovery.root);
    }
    
    public BinaryTree recovery_subtree_inpre(String sub_inorder, String preorder){
        if(sub_inorder.length() == 0){
            return new BinaryTree();
        }
        else{ 
            BinaryTree temp = new BinaryTree();
            if(sub_inorder.length() == 1){
            temp.root = new NodeA(sub_inorder);
            }
            else{
                int[] indexes = new int[sub_inorder.length()];
                for (int i = 0; i < sub_inorder.length(); i++) {
                    indexes[i] = preorder.indexOf(sub_inorder.substring(i, i+1));
                }
                int less = Integer.MAX_VALUE;
                int index = -1;
                for (int i = 0; i < indexes.length; i++) {
                    if(indexes[i] < less){
                        index = i;
                        less = indexes[i];
                    }
                }                
                temp.root = new NodeA(sub_inorder.substring(index, index + 1));
                temp.root.setLeft(recovery_subtree_inpre(sub_inorder.substring(0, index), preorder).root);
                temp.root.setRight(recovery_subtree_inpre(sub_inorder.substring(index + 1), preorder).root);
            }
            return temp;            
        }      
    }
    public BinaryTree recovery_subtree_inpos(String sub_inorder, String posorder){
        if(sub_inorder.length() == 0){
            return new BinaryTree();
        }
        else{ 
            BinaryTree temp = new BinaryTree();
            if(sub_inorder.length() == 1){
            temp.root = new NodeA(sub_inorder);
            }
            else{
                int[] indexes = new int[sub_inorder.length()];
                for (int i = 0; i < sub_inorder.length(); i++) {
                    indexes[i] = posorder.indexOf(sub_inorder.substring(i, i+1));
                }
                int greater = Integer.MIN_VALUE;
                int index = -1;
                for (int i = 0; i < indexes.length; i++) {
                    if(indexes[i] < greater){
                        index = i;
                        greater = indexes[i];
                    }
                }                
                temp.root = new NodeA(sub_inorder.substring(index, index + 1));
                temp.root.setLeft(recovery_subtree_inpos(sub_inorder.substring(0, index), posorder).root);
                temp.root.setRight(recovery_subtree_inpos(sub_inorder.substring(index + 1), posorder).root);
            }
            return temp;            
        }      
    }
    
    public void preorder_print(NodeA node){
        if(node != null){
            System.out.println(node.toString());
            preorder_print(node.getLeft());
            preorder_print(node.getRight());
        }
    }
    
    public void posorder_print(NodeA node){
        if(node != null){            
            posorder_print(node.getLeft());
            posorder_print(node.getRight());
            System.out.println(node.toString());
        }
    }
    
    public void inorder_print(NodeA node){
        if(node != null){            
            inorder_print(node.getLeft());
            System.out.println(node.toString());
            inorder_print(node.getRight());            
        }
    }
    public static void main(String[] args) {
//------------test1--------------------------
//        BinaryTree test = new BinaryTree();
//        test.insert(52);
//        test.insert(18);
//        test.insert(74);
//        test.insert(60);
//        test.insert(87);
//        test.insert(83);
//        test.insert(100);
//        test.insert(85);
//        test.delete(74);        
//        test.preorder_print(test.root);
//        System.out.println("*******************");
//        test.inorder_print(test.root);
//------------test2---------------------------
//        BinaryTree test2 = new BinaryTree();
//        test2.insert(15);
//        test2.insert(6);
//        test2.insert(50);
//        test2.insert(4);
//        test2.insert(7);
//        test2.insert(1);
//        test2.insert(5);
//        test2.insert(14);
//        test2.insert(23);
//        test2.insert(20);
//        test2.insert(27);
//        test2.insert(71);
//        test2.insert(57);
//        test2.insert(77);
//        test2.rightRotation();
//        test2.leftRotation();
//        test2.preorder_print(test2.root);
    }
}