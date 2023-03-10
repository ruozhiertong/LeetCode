import com.sun.xml.internal.rngom.binary.visitor.ChildElementFinder;

/**
 *
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 *
 *
 *
 * @author rzet
 * @date 2023/1/6 13:59
 */
public class T208 {

    public static void main(String[] args) {
        Trie root = new Trie();

        root.insert("bedroom");
        root.insert("apple");
        root.insert("bed");
        System.out.println(root.search("apple"));
        System.out.println(root.startsWith("apple"));
        root.preTravel(root,"");


    }
}


class Trie {

    private static int CHARNUM = 26;
    private char ch;
    private Trie[] children;
    private boolean isEnd; //是否 单词的 结尾。

    public Trie() {
        children  = new Trie[CHARNUM];
        isEnd = false;
        ch = 0; //根默认的ch为0
    }

    public void insert(String word) {
        Trie ptr = this;
        for (int i = 0 ; i < word.length(); i++){
           char c = word.charAt(i);
           int index = c -'a';
           if (ptr.children[index] == null){
               ptr.children[index] = new Trie();
               ptr.children[index].ch = c;
           }
           ptr = ptr.children[index];
       }
       ptr.isEnd = true;

    }

    // 使用递归。
    public boolean search(String word) {
        if (this == null || word == null || word.length() == 0)
            return false;

        Trie ptr = this;
        if(word.length() == 1){
            char c = word.charAt(0);
            if (this.children[c-'a'] != null && this.children[c-'a'].isEnd)
                return true;
            return false;
        }else{
            char c = word.charAt(0);
            if (this.children[c-'a'] != null)
                return this.children[c-'a'].search(word.substring(1));
            else
                return false;
        }
    }

    public boolean startsWith(String prefix) {
        if (this == null || prefix == null || prefix.length() == 0)
            return false;

        Trie ptr = this;
        if(prefix.length() == 1){
            char c = prefix.charAt(0);
            if (this.children[c-'a'] != null)
                return true;

            return false;
        }else{
            char c = prefix.charAt(0);
            if (ptr.children[c-'a'] != null)
                return ptr.children[c-'a'].startsWith(prefix.substring(1));
            else
                return false;
        }
    }

    //深度优先搜索。 循环/栈。  递归。    【递归的都能化为循环，使用栈来辅助。】
    //          -- 树的前序(也属于深度优先)， 中序， 后序。（中序 后序不是深度优先。 但前中后也使用了递归，也能用栈进行循环）。
//                --  https://blog.csdn.net/CodAlun/article/details/106733606
    //广度优先搜素。 循环/队列。
    //          -- 树的层次遍历(也属于广度优先)。
    public int countPref(String pref){

        return 0;
    }

    public void midorder(Trie n){
//        if (n == null)
//            return;
//
//        Stack stack;
//        Trie ptr = n;
//        while(stack.size() != 0){
//
//            while(ptr != null){
//                stack.add(ptr);
//                ptr = ptr.left;
//            }
//            n = stack.pop();
//            print(n);
//
//            if (n.right != null){
//                ptr = n.right;
//            }
//
//
//
//        }
//
//
//        //递归
//        midorder(n.left);
//        print(n);
//        midorder(n.right);
    }


    public void preOrder(Trie n){
//        //递归。
//        print(n);
//        preOrder(n.left);
//        preOrder(n.right);
//
//        //循环。 利用栈。
//        Stack stack;
//        stack.add(n);
//        while(n.size() != 0){
//            l = stack.pop();
//            print(l);
//            if (l.right != null)
//                stack.add(l.right);
//            if (l.left !- null)
//                stack.add(l.left);
//        }
//
//
//
//


    }



    public void preTravel(Trie node, String word){
        if (node != null){
            if (node.ch != 0)
                word += node.ch;
            if (node.isEnd)
                System.out.print(word + "\t");
            for (Trie n: node.children){
                preTravel(n, word);

            }
        }
    }

}

class Trie0{

    class Node{
        private int CHARNUM = 26;
        private char ch;
        private Node[] children;
        private boolean isEnd; //是否 单词的 结尾。

        public Node() {
            children  = new Node[CHARNUM];
            isEnd = false;
            ch = 0; //根默认的ch为0
        }
    }

    private Node root; //不存数据ch 的root。

    public Trie0() {
        root = new Node();
    }

    public void insert(String word) {
        Node ptr = this.root;
        for (int i = 0 ; i < word.length(); i++){
            char c = word.charAt(i);
            int index = c -'a';
            if (ptr.children[index] == null){
                ptr.children[index] = new Node();
                ptr.children[index].ch = c;
            }
            ptr = ptr.children[index];
        }
        ptr.isEnd = true;

    }

    public boolean search(String word) {
        Node ptr = this.root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (ptr.children[c-'a'] == null)
                return false;
            ptr = ptr.children[c-'a'];
        }
        if (ptr.isEnd)
            return true;
        else
            return false;
    }

    public boolean startsWith(String prefix) {
        Node ptr = this.root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (ptr.children[c-'a'] == null)
                return false;
            ptr = ptr.children[c-'a'];
        }

        return true;
    }


}