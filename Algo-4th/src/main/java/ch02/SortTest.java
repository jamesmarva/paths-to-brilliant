package ch02;

import ch02.sec2.MergeSort01;
import ch02.sec3.QuickSort01;

import java.util.Arrays;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.19 19:28
 */
public class SortTest {

    public static void main(String[] args) {
//        SortAction action = new MergeSort01();
        SortAction action = new QuickSort01();
        int[] i = {1, 23, 424,2 ,543, 6,56,56,55,65,6,56,55,2,2,5,23,5,235,23,623,6,6,3,45,63,456,3,456,2154,2,4,1};
        action.sort(i);
//        for (int item : i) {
//            System.out.println(i);
//        }
        System.out.println(Arrays.toString(i));

    }
}
