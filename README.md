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
    head.next.next = head;
    head.next = null;
}
```

#### 反转链表前 N 个节点

```
// 将链表的前 n 个节点反转（n <= 链表长度）
ListNode reverseN(ListNode head, int n)

```



## 树

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
- 
>>>>>>> 22ca8c975f8d9ee3318ee38ea4e76014a489765e
