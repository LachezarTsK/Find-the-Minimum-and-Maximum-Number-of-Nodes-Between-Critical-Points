
public class Solution {

    private static final int NOT_RECORDED = Integer.MAX_VALUE;
    private static final int NOT_FOUND = -1;

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstCriticalPointPosition = NOT_RECORDED;
        int lastCriticalPointPosition = NOT_RECORDED;
        int minDistanceBetweenCriticalPoints = NOT_RECORDED;
        int maxDistanceBetweenCriticalPoints = NOT_RECORDED;

        int currentPosition = 1;
        int previousNodeValue = head.val;
        ListNode current = head.next;

        while (current != null) {
            ++currentPosition;
            if (!isCriticalPoint(previousNodeValue, current)) {
                previousNodeValue = current.val;
                current = current.next;
                continue;
            }

            if (firstCriticalPointPosition == NOT_RECORDED) {
                firstCriticalPointPosition = currentPosition;
            }
            if (lastCriticalPointPosition != NOT_RECORDED) {
                minDistanceBetweenCriticalPoints = Math.min(minDistanceBetweenCriticalPoints, currentPosition - lastCriticalPointPosition);
            }
            lastCriticalPointPosition = currentPosition;
            previousNodeValue = current.val;
            current = current.next;
        }

        if (minDistanceBetweenCriticalPoints == NOT_RECORDED) {
            return new int[]{NOT_FOUND, NOT_FOUND};
        }
        maxDistanceBetweenCriticalPoints = lastCriticalPointPosition - firstCriticalPointPosition;

        return new int[]{minDistanceBetweenCriticalPoints, maxDistanceBetweenCriticalPoints};
    }

    private boolean isCriticalPoint(int previousValue, ListNode node) {
        if (node.next == null) {
            return false;
        }
        boolean isLocalMin = previousValue > node.val && node.val < node.next.val;
        boolean isLocalMax = previousValue < node.val && node.val > node.next.val;
        return isLocalMin || isLocalMax;
    }
}

/*
Class ListNode is in-built in the solution file on leetcode.com. 
When running the code on the website, do not include this class.
 */
class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
