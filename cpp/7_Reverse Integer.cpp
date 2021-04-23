#include <iostream>
#include <cstdio>
#include <string>
#include <vector>

using namespace std;

int reverse(int x)
{
    bool isNegative = false;
    if (x < 0)
    {
        x = -x;
        isNegative = true;
    }
    long long ans = 0;
    vector<int> vi;
    while (x != 0)
    {
        vi.push_back(x % 10);
        x /= 10;
    }
    for (int i = 0; i < vi.size(); i++)
    {
        ans = vi[i] + ans * 10;
    }
    if ((isNegative && ans > (1 << 31)) || ((!isNegative) && ans > (1 >> 31 -1) ))
    {
        return 0;
    }
    ans = isNegative ? -ans : ans;
    return ans;
}

int main()
{
    int x;
    while (cin >> x)
    {
        cout << reverse(x) << endl;
    }
}