
package main

import (
    "fmt"
    "math"
)

/*
Struct ListNode is in-built in the solution file on leetcode.com.
When running the code on the website, do not include this struct.
*/
type ListNode struct {
    Val  int
    Next *ListNode
}

const NOT_RECORDED = math.MaxInt
const NOT_FOUND = -1

func nodesBetweenCriticalPoints(head *ListNode) []int {
    var firstCriticalPointPosition = NOT_RECORDED
    var lastCriticalPointPosition = NOT_RECORDED
    var minDistanceBetweenCriticalPoints = NOT_RECORDED
    var maxDistanceBetweenCriticalPoints = NOT_RECORDED

    var currentPosition = 1
    var previousNodeValue = head.Val
    var current = head.Next

    for current != nil {
        currentPosition++
        if !isCriticalPoint(previousNodeValue, current) {
            previousNodeValue = current.Val
            current = current.Next
            continue
        }

        if firstCriticalPointPosition == NOT_RECORDED {
            firstCriticalPointPosition = currentPosition
        }
        if lastCriticalPointPosition != NOT_RECORDED {
            minDistanceBetweenCriticalPoints = min(minDistanceBetweenCriticalPoints, currentPosition-lastCriticalPointPosition)
        }
        lastCriticalPointPosition = currentPosition
        previousNodeValue = current.Val
        current = current.Next
    }

    if minDistanceBetweenCriticalPoints == NOT_RECORDED {
        return []int{NOT_FOUND, NOT_FOUND}
    }
    maxDistanceBetweenCriticalPoints = lastCriticalPointPosition - firstCriticalPointPosition

    return []int{minDistanceBetweenCriticalPoints, maxDistanceBetweenCriticalPoints}
}

func isCriticalPoint(previousValue int, node *ListNode) bool {
    if node.Next == nil {
        return false
    }
    isLocalMin := previousValue > node.Val && node.Val < node.Next.Val
    isLocalMax := previousValue < node.Val && node.Val > node.Next.Val
    return isLocalMin || isLocalMax
}
