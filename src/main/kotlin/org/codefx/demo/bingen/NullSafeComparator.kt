package org.codefx.demo.bingen

interface NullSafeComparator<T>: Comparator<T> {

    override fun compare(o1: T, o2: T): Int {
        if (o1 == null || o2 == null)
            throw NullPointerException()
        return compareNonNull(o1, o2)
    }

    fun compareNonNull(o1: T, o2: T): Int

}
