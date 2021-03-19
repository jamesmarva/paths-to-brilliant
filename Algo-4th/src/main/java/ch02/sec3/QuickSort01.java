package ch02.sec3;

import ch02.SortAction;

/**
 *
 *
 * 最基础的快排
 * @author 王涵威
 * @date 21.3.19 19:57
 */
public class QuickSort01 implements SortAction {


    @Override
    public void sort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public void quickSort(int[] a, int s, int e) {
        if (s >= e) {
            return;
        }

        int idx = partition(a, s, e);
        quickSort(a, s, idx - 1);
        quickSort(a, idx + 1, e);
    }

    public int partition(int[] a, int start, int end) {
        int pivotVal = a[start];
        int left = start;
        int right = end + 1;
        while (true) {
            while (a[++left] <= pivotVal) {
                if (left == end) {
                    break;
                }
            }
            while (a[--right] > pivotVal) {
                if (right == start) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            swap(a, left, right);
        }
        swap(a, start, right);
        return right;
    }

    public static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }





}
