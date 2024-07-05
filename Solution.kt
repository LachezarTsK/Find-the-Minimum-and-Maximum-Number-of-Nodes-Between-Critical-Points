
import kotlin.math.min

/*
Class ListNode is in-built in the solution file on leetcode.com.
When running the code on the website, do not include this class.
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {

    private companion object {
        const val NOT_RECORDED = Int.MAX_VALUE
        const val NOT_FOUND = -1
    }

    fun nodesBetweenCriticalPoints(head: ListNode?): IntArray {
        var firstCriticalPointPosition = NOT_RECORDED
        var lastCriticalPointPosition = NOT_RECORDED
        var minDistanceBetweenCriticalPoints = NOT_RECORDED
        var maxDistanceBetweenCriticalPoints = NOT_RECORDED

        var currentPosition = 1
        var previousNodeValue = head!!.`val`
        var current = head.next

        while (current != null) {
            ++currentPosition
            if (!isCriticalPoint(previousNodeValue, current)) {
                previousNodeValue = current.`val`
                current = current.next
                continue
            }

            if (firstCriticalPointPosition == NOT_RECORDED) {
                firstCriticalPointPosition = currentPosition
            }
            if (lastCriticalPointPosition != NOT_RECORDED) {
                minDistanceBetweenCriticalPoints = min(minDistanceBetweenCriticalPoints, currentPosition - lastCriticalPointPosition)
            }
            lastCriticalPointPosition = currentPosition
            previousNodeValue = current.`val`
            current = current.next
        }

        if (minDistanceBetweenCriticalPoints == NOT_RECORDED) {
            return intArrayOf(NOT_FOUND, NOT_FOUND)
        }
        maxDistanceBetweenCriticalPoints = lastCriticalPointPosition - firstCriticalPointPosition

        return intArrayOf(minDistanceBetweenCriticalPoints, maxDistanceBetweenCriticalPoints)
    }

    private fun isCriticalPoint(previousValue: Int, node: ListNode): Boolean {
        if (node.next == null) {
            return false
        }
        val isLocalMin = previousValue > node.`val` && node.`val` < node.next!!.`val`
        val isLocalMax = previousValue < node.`val` && node.`val` > node.next!!.`val`
        return isLocalMin || isLocalMax
    }
}
