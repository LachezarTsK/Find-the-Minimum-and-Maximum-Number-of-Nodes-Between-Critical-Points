
/*
 Class ListNode is in-built in the solution file on leetcode.com. 
 When running the code on the website, do not include this class.
 */
class ListNode {
    val: number
    next: ListNode | null
    constructor(val?: number, next?: ListNode | null) {
        this.val = (val === undefined ? 0 : val)
        this.next = (next === undefined ? null : next)
    }
}

function nodesBetweenCriticalPoints(head: ListNode | null): number[] {
    const NOT_RECORDED = Number.MAX_SAFE_INTEGER;
    const NOT_FOUND = -1;

    let firstCriticalPointPosition = NOT_RECORDED;
    let lastCriticalPointPosition = NOT_RECORDED;
    let minDistanceBetweenCriticalPoints = NOT_RECORDED;
    let maxDistanceBetweenCriticalPoints = NOT_RECORDED;

    let currentPosition = 1;
    let previousNodeValue = head.val;
    let current = head.next;

    while (current !== null) {
        ++currentPosition;
        if (!isCriticalPoint(previousNodeValue, current)) {
            previousNodeValue = current.val;
            current = current.next;
            continue;
        }

        if (firstCriticalPointPosition === NOT_RECORDED) {
            firstCriticalPointPosition = currentPosition;
        }
        if (lastCriticalPointPosition !== NOT_RECORDED) {
            minDistanceBetweenCriticalPoints = Math.min(minDistanceBetweenCriticalPoints, currentPosition - lastCriticalPointPosition);
        }
        lastCriticalPointPosition = currentPosition;
        previousNodeValue = current.val;
        current = current.next;
    }

    if (minDistanceBetweenCriticalPoints === NOT_RECORDED) {
        return [NOT_FOUND, NOT_FOUND];
    }
    maxDistanceBetweenCriticalPoints = lastCriticalPointPosition - firstCriticalPointPosition;

    return [minDistanceBetweenCriticalPoints, maxDistanceBetweenCriticalPoints];
};

function isCriticalPoint(previousValue: number, node: ListNode): boolean {
    if (node.next === null) {
        return false;
    }
    const isLocalMin = previousValue > node.val && node.val < node.next.val;
    const isLocalMax = previousValue < node.val && node.val > node.next.val;
    return isLocalMin || isLocalMax;
}
