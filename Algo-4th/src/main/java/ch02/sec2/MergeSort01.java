package ch02.sec2;

import ch02.SortAction;

/**
 *
 * @author 王涵威
 * @date 21.3.19 10:08
 */
public class MergeSort01 implements SortAction {
    
    public void mergesort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        mergesort(a, start, mid);
        mergesort(a, mid + 1, end);
        mergeCore(a, start, mid, end);
    }

    public void mergeCore(int[] a, int start, int mid, int end) {
        int[] tmpArr = new int[end - start + 1];
        int idx1 = start;
        int idx2 = mid + 1;
        int i = 0;
        while (idx1 <= mid && idx2 <= end) {
            if (a[idx1] <= a[idx2]) {
                tmpArr[i++] = a[idx1++];
            } else {
                tmpArr[i++] = a[idx2++];
            }
        }
        while (idx1 <= mid) {
            tmpArr[i++] = a[idx1++];
        }
        while (idx2 <= end) {
            tmpArr[i++] = a[idx2++];
        }
        for (int k = 0, l = tmpArr.length; k < l; k++) {
            a[start + k] = tmpArr[k];
        }
    }

    @Override
    public void sort(int[] a) {
        mergesort(a, 0, a.length - 1);
    }
}
