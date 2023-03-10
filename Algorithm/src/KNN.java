import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author rzet
 * @date 2022/7/13 19:01
 */
public class KNN {

    public static void main(String[] args) {
        int [][] list = new int[][]{{3, 2}, {7, 3}, {4, 6}, {5, 7}, {8, 9}, {11, 5}, {12, 8}, {13, 1}, {14, 4}, {14, 10}};

        int query[] = {4, 5};
        KDTree kdTree = new KDTree(list);
        kdTree.printKDTree(kdTree.root);
        System.out.println();

        List<KDNode> knnNodes = kdTree.KNN(query, 3);
        for (KDNode node : knnNodes){
            node.printKDNode();
        }
    }
}


class KDTree{
    public KDNode root;

    public KDTree(int[][] val){
        this.root = createKDTree(val, 0);
    }

    public KDNode createKDTree(int[][] val, int splitByD){

        int len = val.length;
        int dimension = val[0].length;
        Arrays.stream(val).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[splitByD] - o2[splitByD];
            }
        });
//        sortBySplit(val, splitByD);

        int midIdx = len/2;
        int[] midVal = val[midIdx];
        KDNode kdNode = new KDNode(midVal, dimension,splitByD);
        if (len == 1){
            kdNode.leftChild = null;
            kdNode.rightChild = null;
            return kdNode;
        }

        KDNode leftChild, rightChild;

        int[][] leftPart = new int[midIdx][dimension];
        System.arraycopy(val,0,leftPart,0,midIdx);
        leftChild = createKDTree(leftPart, (splitByD + 1)%dimension);


        if (midIdx + 1 == len){
            rightChild = null;
        }else{
            int[][] rightPart = new int[len - midIdx - 1][dimension];
            System.arraycopy(val,midIdx + 1,rightPart,0,len - midIdx - 1);
            rightChild = createKDTree(rightPart, (splitByD + 1)%dimension);
        }



        kdNode.leftChild = leftChild;
        kdNode.rightChild = rightChild;

        return kdNode;

    }

    public void sortBySplit(int [][]val, int split){
        //选择排序
        for (int i = 0 ; i < val.length; i++){
            for (int j = i + 1; j < val.length; j++){
                if (val[i][split] > val[j][split]){
                    int[] tmp = val[i];
                    val[i] = val[j];
                    val[j] = tmp;
                }
            }
        }
        return;
    }

    public double computerDist(int[] a, int[] b){
        double sum = 0;
        for (int i = 0; i < a.length; i++){
            sum += (a[i] - b[i])*(a[i] - b[i]); //Math.pow(a[i]-b[i], 2);
        }
        return Math.sqrt(sum);
    }
    public void printKDTree(KDNode node){
        if (node == null)
            return;

        printKDTree(node.leftChild);

        node.printKDNode();

        printKDTree(node.rightChild);

    }

    //寻找 query点 最邻近的k个点。
    public List<KDNode> KNN(int[] query, int k){
        KDNode tmp = this.root;

        Stack<KDNode> nodeStack = new Stack<KDNode>();

        List<KDNode> knnNodes = new LinkedList<KDNode>();
        List<Double> knnDis = new LinkedList<Double>();


        //先找到最近的叶子节点，
        while (tmp != null){
            nodeStack.push(tmp);
            tmp.visited = true;

            if (query[tmp.splitByD] < tmp.val[tmp.splitByD])
                tmp = tmp.leftChild;
            else
                tmp = tmp.rightChild;

        }


        final Double[] maxDis = {Double.MAX_VALUE};
        while (!nodeStack.isEmpty()){
            KDNode node = nodeStack.pop();
//            Optional<Double> res = knnDis.stream().max(Double::compare);
//            if (!res.isPresent())
//                maxDis = Double.MAX_VALUE;
//            else
//                maxDis = res.get();
//
//
            //神奇！
            //在匿名内部类中要更改变量，要设置成final。
            //但是又想更改边狼值怎么办？ 1.使用数组形式。 2.使用AtomicReference<Double> maxDis
            knnDis.stream().max(Double::compare).ifPresent(p-> maxDis[0] = p.doubleValue());
//            AtomicReference<Double> mas = new AtomicReference<>((double) 0);
//            knnDis.stream().max(Double::compare).ifPresent(p-> mas.set(p.doubleValue()));


            double curDis = computerDist(query, node.val);

            if(knnNodes.size() < k){
                knnNodes.add(node);
                knnDis.add(curDis);
            }else if (maxDis[0] > curDis){
                int idx = knnDis.indexOf(maxDis[0]);
                knnNodes.remove(idx);
                knnDis.remove(idx);
                knnNodes.add(node);
                knnDis.add(curDis);
            }


            if (knnNodes.size() < k || Math.abs(query[node.splitByD] - node.val[node.splitByD]) < maxDis[0]){
                if (node.leftChild != null && node.leftChild.visited == false){
                    nodeStack.push(node.leftChild);
                    node.leftChild.visited = true;
                }

                if (node.rightChild != null && node.rightChild.visited == false){
                    nodeStack.push(node.rightChild);
                    node.rightChild.visited = true;
                }
            }else {
                //剪枝操作。
                if(query[node.splitByD] < node.val[node.splitByD]){
                    if (node.leftChild != null && node.leftChild.visited == false){
                        nodeStack.push(node.leftChild);
                        node.leftChild.visited = true;
                    }
                }else {
                    if (node.rightChild != null && node.rightChild.visited == false){
                        nodeStack.push(node.rightChild);
                        node.rightChild.visited = true;
                    }
                }
            }
        }

        return knnNodes;
    }

}


class KDNode{
    public int []val;
    public int dimension; //维度。
    public KDNode leftChild;
    public KDNode rightChild;
    public int splitByD; //以哪维做比较分割。
    public boolean visited;
    public KDNode(int[]val, int dimension, int splitByD){
        this.dimension = val.length;
        this.val = new int[this.dimension];
        System.arraycopy(val,0,this.val,0 , dimension);
        this.leftChild = null;
        this.rightChild = null;
        this.splitByD = splitByD;
        this.visited = false;
    }
    public void printKDNode(){
        for (int i = 0 ; i < dimension; i++){
            if (i == 0)
                System.out.print("(" + val[i] + ",");
            else if(i == dimension - 1)
                System.out.print(val[i] + ")");
            else
                System.out.println(val[i] + ",");
        }
    }
}
