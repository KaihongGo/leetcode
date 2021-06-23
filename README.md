# README.md

> 本文件夹缓存刷leetcode代码，完成后进行归类，[参考](https://github.com/gouthampradhan/leetcode/tree/master/problems/src)
> 记录刷题笔记，笔记参考[labuladong的算法小抄](https://labuladong.gitbook.io/algo/)

# 第一章 手把手刷数据结构

## 手把手刷链表题目

### 递归反转链表的一部分

| 题目                                                         |      |
| ------------------------------------------------------------ | ---- |
| [92.反转链表II（中等）](https://leetcode-cn.com/problems/reverse-linked-list-ii/) |      |

#### 递归反转整个链表

```java
ListNode reverse(ListNode head) {
    if (head.next == null) return head;
    last = reverse(head.next);
    head.next.next = head; // [1] ? 不能是head.next.next.next = head;
    head.next = null;
}
// [1]: 在此之前head没变，head.next 所指元素也没变
```

思考：如何不改变`head`，原链表？

#### 反转链表前 N 个节点

```java
ListNode successor = null; // 后驱节点

// 反转以 head 为起点的 n 个节点，返回新的头结点
ListNode reverseN(ListNode head, int n) {
    if (n == 1) { 
        // 记录第 n + 1 个节点
        successor = head.next; // [1] ? successor为什么这里赋值
        return head;
    }
    // 以 head.next 为起点，需要反转前 n - 1 个节点
    ListNode last = reverseN(head.next, n - 1);
    head.next.next = head;
    // 让反转之后的 head 节点和后面的节点连起来
    head.next = successor;
    return last;
}
```

思考：如何不改变`head`，原链表？

### 注意

* 链表问题常出现**空指针异常**，主要思考**访问元素时，对象有无可能为空**



## 手把手刷二叉树

- 特例判断，输入空树

- 遍历时对于树的左右子树判空树

- 寻找数组中最大的数(maximum value)
  
    ```java
    // The array you are are passing to the Arrays.asList() must have a return type of Integer or whatever class you want to use
    // 必须转化为包装类
    Integer[] num = { 2, 4, 7, 5, 9 };
    int min = Collections.min(Arrays.asList(num));
    // Java 8 Stream
    int arr[] = {10, 324, 45, 90, 9808};
    int max = Arrays.stream(arr).max().getAsInt();
    ```

- 二叉树序列化，做树形比较；前序，后序可唯一

    ```txt
    补充（为什么中序不行）：  
    对于  
      0            0  
     /        与    \     两个子树  
    0                0  
    中序结果：#0#0#   #0#0#  
    前序结果：00###   0#0##  
    后序结果：##0#0   ###00  
    序列化时只要不是中序，前序或者后序都能通过
    ```

- [Java 到底是值传递还是引用传递？-知乎](https://www.zhihu.com/question/31203609)

- **要尽可能避免递归函数中调用其他递归函数**，如果出现这种情况，大概率是代码实现有瑕疵，可以进行优化来避免递归套递归(leetcode-1373)；**利用一个函数，将所有辅助函数做的事情做掉**

- Java比较字符串，String 用**equal**方法，不用等号

- 递归灵魂三问？
  - 1、这个函数是干嘛的？
  - 2、这个函数参数中的变量是什么的是什么？
  - 3、得到函数的递归结果，你应该干什么？

- LCA
  
  ```java
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q)
            return root;
        if (root == null)
            return null;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 递归基返回值决定一定是p或q
        if (left != null && right != null)
            return root;
        if (left == null && right == null)
            return null;
        return left == null ? right : left;
    }
  ```

- 一棵完全二叉树的两棵子树，至少有一棵是满二叉树，leetcode-222



## 手把手设计数据结构

### Union-Find 算法详解



**关键问题：如何想办法避免树的不平衡呢**？

#### 平衡性优化

我们要知道哪种情况下可能出现不平衡现象，关键在于`union`过程。**我们其实是希望，小一些的树接到大一些的树下面，这样就能避免头重脚轻，更平衡一些**。解决方法是额外使用一个`size`数组，记录每棵树包含的节点数，我们不妨称为「重量」：

#### 路径压缩

可见，调用`find`函数每次向树根遍历的同时，顺手将树高缩短了，最终所有树高都不会超过 3（`union`的时候树高可能达到 3）

![](https://gblobscdn.gitbook.com/assets%2F-McgKdLMgKGHf9UIOZCM%2Fsync%2F97c5b72a08b64233fd56aea9e90f14ece6b50498.gif?alt=media)

```java
class UF {
    // 连通分量个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录树的“重量”
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    private int find(int x) {
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }
}
```

### Union-Find 算法应用

#### 一、DFS的替代方案

> [130. Surrounded Regions](https://leetcode-cn.com/problems/surrounded-regions/)

**主要思路是适时增加虚拟节点，想办法让元素「分门别类」，建立动态连通关系**。

> 开头第一句，特例判断返回 `return` 

* 方向数组 d 是上下左右搜索的常用手法：`  int[][] d = new int[][]{{1,0}, {0,1}, {0,-1}, {-1,0}};`

    
