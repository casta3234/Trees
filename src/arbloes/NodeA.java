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
public class NodeA {
   private int value;
   private NodeA right = null;
   private NodeA left = null;
   private String g;
   
   public NodeA(int value){
       this.value = value;
   }

    NodeA(String sub_inorder) {
        this.value = 0;
        this.g = sub_inorder;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeA getRight() {
        return right;
    }

    public void setRight(NodeA right) {
        this.right = right;
    }

    public NodeA getLeft() {
        return left;
    }

    public void setLeft(NodeA left) {
        this.left = left;
    }
   
   
   
   @Override
   public String toString(){
       if (this.value != 0){
        return "value:" + this.value;   
       }
       else{
        return this.g;
       }
   }
   
   
   public NodeA Clone(){
       return new NodeA(this.value);
   }
}
