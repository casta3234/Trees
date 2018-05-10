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
   
   public NodeA(int value){
       this.value = value;
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
       return "value:" + this.value;
   }
   
   
   public NodeA Clone(){
       return new NodeA(this.value);
   }
}
