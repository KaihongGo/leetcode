#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
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
    void layerOrder(TreeNode *root, vector<int> &tree)
    {
        queue<TreeNode *> q;
        q.push(root);
        while (!q.empty())
        {
            TreeNode *now = q.front();
            q.pop();
            tree.push_back(now->val);
            if(TreeNode)

        }

    TreeNode *mergeTrees(TreeNode *t1, TreeNode *t2)
    {
    }
};