
using System;

public class Solution
{
    private static readonly int NOT_RECORDED = int.MaxValue;
    private static readonly int NOT_FOUND = -1;

    public int[] NodesBetweenCriticalPoints(ListNode head)
    {
        int firstCriticalPointPosition = NOT_RECORDED;
        int lastCriticalPointPosition = NOT_RECORDED;
        int minDistanceBetweenCriticalPoints = NOT_RECORDED;
        int maxDistanceBetweenCriticalPoints = NOT_RECORDED;

        int currentPosition = 1;
        int previousNodeValue = head.val;
        ListNode current = head.next;

        while (current != null)
        {
            ++currentPosition;
            if (!IsCriticalPoint(previousNodeValue, current))
            {
                previousNodeValue = current.val;
                current = current.next;
                continue;
            }

            if (firstCriticalPointPosition == NOT_RECORDED)
            {
                firstCriticalPointPosition = currentPosition;
            }
            if (lastCriticalPointPosition != NOT_RECORDED)
            {
                minDistanceBetweenCriticalPoints = Math.Min(minDistanceBetweenCriticalPoints, currentPosition - lastCriticalPointPosition);
            }
            lastCriticalPointPosition = currentPosition;
            previousNodeValue = current.val;
            current = current.next;
        }

        if (minDistanceBetweenCriticalPoints == NOT_RECORDED)
        {
            return new int[] { NOT_FOUND, NOT_FOUND };
        }
        maxDistanceBetweenCriticalPoints = lastCriticalPointPosition - firstCriticalPointPosition;

        return new int[] { minDistanceBetweenCriticalPoints, maxDistanceBetweenCriticalPoints };
    }

    private bool IsCriticalPoint(int previousValue, ListNode node)
    {
        if (node.next == null)
        {
            return false;
        }
        bool isLocalMin = previousValue > node.val && node.val < node.next.val;
        bool isLocalMax = previousValue < node.val && node.val > node.next.val;
        return isLocalMin || isLocalMax;
    }
}

/*
Class ListNode is in-built in the solution file on leetcode.com. 
When running the code on the website, do not include this class.
 */
public class ListNode
{
    public int val;
    public ListNode next;
    public ListNode(int val = 0, ListNode next = null)
    {
        this.val = val;
        this.next = next;
    }
}
