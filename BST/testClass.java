import java.util.ArrayList;
import java.util.List;

public class testClass<T extends Comparable<T>> extends BinarySearchTree<T>{
    
    public List<T> inorder(){
        List<T> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }
    private void inorder(BinaryNode<T> n, List<T> res){
        if(n==null) return;
        inorder(n.left, res);
        res.add(n.data);
        inorder(n.right, res);
    }

    public List<T> preorder(){
        List<T> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }
    private void preorder(BinaryNode<T> n, List<T> res){
        if(n==null) return;
        res.add(n.data);
        preorder(n.left, res);
        preorder(n.right, res);
    }

    public List<T> postorder(){
        List<T> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }
    private void postorder(BinaryNode<T> n, List<T> res){
        if(n==null) return;
        postorder(n.left, res);
        postorder(n.right, res);
        res.add(n.data);
    }
    public static void main(String[] args){ //main method to confirm our tests
        
        BinarySearchTree<String> mines = new BinarySearchTree<>();
        testClass<String> tc = new testClass<>();
        tc.insert("bobby"); tc.insert("bobby"); tc.insert("tung"); tc.insert("tung"); tc.insert("tung");
        tc.insert("sahur"); tc.insert("Ben Z."); tc.insert("fortnite"); tc.insert("return!");
        System.out.println(tc.inorder());

    }
      
}
