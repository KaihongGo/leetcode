#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution
{
public:
    void insert(TreeNode *&root, int val)
    {
        if (root == nullptr)
        {
            root = new TreeNode(val);
            return;
        }
        if (val == root->val)
        {
            return;
        }
        else if (val < root->val)
        {
            insert(root->left, val);
        }
        else
        {
            insert(root->right, val);
        }
    }
    TreeNode *buildTree(vector<int> tree)
    {
        TreeNode *root = nullptr;
        for (auto val : tree)
        {
            insert(root, val);
        }
        return root;
    }

    void inOrder(TreeNode *root, vector<int> &in)
    {
        if (root == nullptr)
            return;
        inOrder(root->left, in);
        in.push_back(root->val);
        inOrder(root->right, in);
    }

    vector<int> merge(vector<int> a, vector<int> b)
    {
        vector<int> result(a.size() + b.size());
        int i = 0, j = 0;
        a.push_back(0x7fffffff);
        b.push_back(0x7fffffff);
        for (int k = 0; k < result.size(); k++)
        {
            if (a[i] <= b[j])
                result[k] = a[i++];
            else
                result[k] = b[j++];
        }
        return result;
    }

    vector<int> getAllElements(TreeNode *root1, TreeNode *root2)
    {
        vector<int> in1, in2;
        inOrder(root1, in1);
        inOrder(root2, in2);
        return merge(in1, in2);
    }
};

int main()
{
    vector<int> a = {2, 1, 4};
    vector<int> b = {1, 0, 3};
    sort(a.begin(), a.end());
    sort(b.begin(), b.end());
    
    Solution test;
    TreeNode *root1 = test.buildTree(a);
    TreeNode *root2 = test.buildTree(b);
    vector<int> c = test.getAllElements(root1, root2);
    for (auto e : c)
    {
        cout << e << " ";
    }
}