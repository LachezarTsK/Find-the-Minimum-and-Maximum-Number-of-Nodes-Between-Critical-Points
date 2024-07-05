
#include <limits>
#include <algorithm>
using namespace std;

/*
Struct ListNode is in-built in the solution file on leetcode.com.
When running the code on the website, do not include this struct.
 */
struct ListNode {
    int val;
    ListNode* next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};

class Solution {

    static const int NOT_RECORDED = numeric_limits<int>::max();
    static const int NOT_FOUND = -1;

public:
    vector<int> nodesBetweenCriticalPoints(ListNode* head) const {
        int firstCriticalPointPosition = NOT_RECORDED;
        int lastCriticalPointPosition = NOT_RECORDED;
        int minDistanceBetweenCriticalPoints = NOT_RECORDED;
        int maxDistanceBetweenCriticalPoints = NOT_RECORDED;

        int currentPosition = 1;
        int previousNodeValue = head->val;
        ListNode* current = head->next;

        while (current != nullptr) {
            ++currentPosition;
            if (!isCriticalPoint(previousNodeValue, current)) {
                previousNodeValue = current->val;
                current = current->next;
                continue;
            }

            if (firstCriticalPointPosition == NOT_RECORDED) {
                firstCriticalPointPosition = currentPosition;
            }
            if (lastCriticalPointPosition != NOT_RECORDED) {
                minDistanceBetweenCriticalPoints = min(minDistanceBetweenCriticalPoints, currentPosition - lastCriticalPointPosition);
            }
            lastCriticalPointPosition = currentPosition;
            previousNodeValue = current->val;
            current = current->next;
        }

        if (minDistanceBetweenCriticalPoints == NOT_RECORDED) {
            return vector<int> {NOT_FOUND, NOT_FOUND};
        }
        maxDistanceBetweenCriticalPoints = lastCriticalPointPosition - firstCriticalPointPosition;

        return vector<int> {minDistanceBetweenCriticalPoints, maxDistanceBetweenCriticalPoints};
    }

private:
    bool isCriticalPoint(int previousValue, ListNode* node) const {
        if (node->next == nullptr) {
            return false;
        }
        bool isLocalMin = previousValue > node->val && node->val < node->next->val;
        bool isLocalMax = previousValue < node->val && node->val > node->next->val;
        return isLocalMin || isLocalMax;
    }
};
