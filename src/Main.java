/**
 * Created by jb on 10/30/16.
 */
public class Main {
    static public void main(String[] args) {
        final JHashMap<String, Integer> tmp = new JHashMap<>(100);
        tmp.put("lol find this1", 69);

        System.out.println("Here we have: " + tmp.get("lol find this1"));
        tmp.remove("lol find this1");
        System.out.println("Here we have: " + tmp.get("lol find this1"));

        final BST<Integer> tree = new BST<>();
        tree.insert(8);
        tree.insert(-4);
        tree.insert(9);
        tree.insert(5);
        tree.insert(-9);
        tree.insert(10);
        System.out.println("height: " + tree.height());
        System.out.println("max" + tree.max());
        System.out.println("min" + tree.min());

        System.out.println("\nContents in Layer 3:");
        for (int i : tree.getLayerNodes(3)) {
            System.out.print(i + ", ");
        }
        System.out.println("");

        System.out.println(tree.preOrder().toString());
        System.out.println(tree.inOrder().toString());
        System.out.println(tree.postOrder().toString());
    }
}
