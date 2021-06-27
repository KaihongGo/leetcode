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

### LRU算法



### LFU算法

```java
class LFUCache {
    // key 到 val 的映射，我们后文称为 KV 表
    HashMap<Integer, Integer> keyToVal;
    // key 到 freq 的映射，我们后文称为 KF 表
    HashMap<Integer, Integer> keyToFreq;
    // freq 到 key 列表的映射，我们后文称为 FK 表
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 记录最小的频次
    int minFreq;
    // 记录 LFU 缓存的最大容量
    int cap;

    public LFUCache(int capacity) {}

    public int get(int key) {}

    public void put(int key, int val) {}
	
    private void increaseFreq(int key) {}
    
    private void removeMinFreqKey() {}
}
```

`LinkedHashSet`数据结构

```java
freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
freqToKeys.get(1).add(key);
```

> **思路**：利用多个相互关联的**映射**

### 最大栈

# 第零章 必读文章

## 二分搜索

### 零 二分查找框架

```java
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...;
    while(...) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...;
        } else if (nums[mid] > target) {
            right = ...;
        }
    }
    return ...;
}
```

### 一 寻找一个数（基本的二分搜索）

```java
int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length-1;
    while(left <= right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        }
    }
    return -1;
}
```

判定搜索区间`[left, right]`

### 二 寻找左侧边界的二分搜索

```java
int left_bound(int[] nums, int target) {
    if (nums.length == 0) return -1;
    int left = 0;
    int right = nums.length; // 判定搜索区间 [left, right)
    while (left < right) {	 // 依据搜索区间判定终止条件
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            right = mid;	// 能够搜索左侧的关键
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid;
        }
    }
    return left;
}
```

「左侧边界」有什么特殊含义：

比如对于有序数组 `nums = [2,3,5,7]`, `target = 1`，算法会返回 0，含义是：`nums` 中小于 1 的元素有 0 个。

再比如说 `nums = [2,3,5,7], target = 8`，算法会返回 4，含义是：`nums` 中小于 8 的元素有 4 个。

综上可以看出，函数的返回值（即 `left` 变量的值）取值区间是闭区间 `[0, nums.length]`，所以我们简单添加两行代码就能在正确的时候 return -1：

```java
while (left < right) {
    //...
}
// target 比所有数都大
if (left == nums.length) return -1;
// 类似之前算法的处理方式
return nums[left] == target ? left : -1;
```

**为什么该算法能够搜索左侧边界**？

答：关键在于对于 `nums[mid] == target` 这种情况的处理：



```
    if (nums[mid] == target)
        right = mid;
```

可见，找到 target 时不要立即返回，而是缩小「搜索区间」的上界 `right`，在区间 `[left, mid)` 中继续搜索，即不断向左收缩，达到锁定左侧边界的目的。

**统一**

```java
int left_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    // 搜索区间为 [left, right]
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            // 搜索区间变为 [mid+1, right]
            left = mid + 1;
        } else if (nums[mid] > target) {
            // 搜索区间变为 [left, mid-1]
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 收缩右侧边界
            right = mid - 1;
        }
    }
    // 检查出界情况
    if (left >= nums.length || nums[left] != target)
        return -1;
    return left;
}
```

### 三 寻找右侧边界的二分查找

```java
int right_bound(int[] nums, int target) {
    int left = 0;
    int right = nums.length;
    while (left < right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            left = mid + 1;
        } else if (nums[mid] < target) {
            left = mid+ + 1;
        } else if (nums[mid] > target) {
            right = mid;
        }
    }
    return left - 1; // 注意
}
```

**为什么最后返回** **`left - 1`** **而不像左侧边界的函数，返回** **`left`****？而且我觉得这里既然是搜索右侧边界，应该返回** **`right`** **才对**。

答：首先，while 循环的终止条件是 `left == right`，所以 `left` 和 `right` 是一样的，你非要体现右侧的特点，返回 `right - 1` 好了。

至于为什么要减一，这是搜索右侧边界的一个特殊点，关键在这个条件判断：



```
if (nums[mid] == target) {
    left = mid + 1;
    // 这样想: mid = left - 1
```

![img](https://gblobscdn.gitbook.com/assets%2F-McgKdLMgKGHf9UIOZCM%2Fsync%2Fa360ba87672e68434224884dfc1393a6039f8130.jpg?alt=media)

因为我们对 `left` 的更新必须是 `left = mid + 1`，就是说 while 循环结束时，`nums[left]` 一定不等于 `target` 了，而 `nums[left-1]` 可能是 `target`。

至于为什么 `left` 的更新必须是 `left = mid + 1`，同左侧边界搜索，就不再赘述。

**统一**

```java
int right_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 这里改成收缩左侧边界即可
            left = mid + 1;
        }
    }
    // 这里改为检查 right 越界的情况，见下图
    if (right < 0 || nums[right] != target)
        return -1;
    return right;
}
```

**第一个，最基本的二分查找算法**：

```
因为我们初始化 right = nums.length - 1所以决定了我们的「搜索区间」是 [left, right]所以决定了 while (left <= right)同时也决定了 left = mid+1 和 right = mid-1
因为我们只需找到一个 target 的索引即可所以当 nums[mid] == target 时可以立即返回
```

**第二个，寻找左侧边界的二分查找**：

```
因为我们初始化 right = nums.length所以决定了我们的「搜索区间」是 [left, right)所以决定了 while (left < right)同时也决定了 left = mid + 1 和 right = mid
因为我们需找到 target 的最左侧索引所以当 nums[mid] == target 时不要立即返回而要收紧右侧边界以锁定左侧边界
```

**第三个，寻找右侧边界的二分查找**：

```
因为我们初始化 right = nums.length所以决定了我们的「搜索区间」是 [left, right)所以决定了 while (left < right)同时也决定了 left = mid + 1 和 right = mid
因为我们需找到 target 的最右侧索引所以当 nums[mid] == target 时不要立即返回而要收紧左侧边界以锁定右侧边界
又因为收紧左侧边界时必须 left = mid + 1所以最后无论返回 left 还是 right，必须减一
```

