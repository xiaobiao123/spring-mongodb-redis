package cn.springmvc.collection;

import java.util.Arrays;

public class TestArrays {
	public static void output(int[] array) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i] + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = new int[5];
//		// 填充数组
//		Arrays.fill(array, 5);
//		System.out.println("填充数组：Arrays.fill(array, 5)：");
//		TestArrays.output(array);

//		// 将数组的第3和第4个元素赋值为8
//		Arrays.fill(array, 2, 4, 8);
//		System.out.println("将数组的第3和第4个元素赋值为8：Arrays.fill(array, 2, 4, 8)：");
//		TestArrays.output(array);

		int[] array1 = { 7, 8, 3, 2, 12, 6, 3, 5, 4 };
		// 对数组的第2个到第6个进行排序进行排序
//		Arrays.sort(array1, 2, 7);
//		System.out.println("对数组的第2个到第6个元素进行排序进行排序：Arrays.sort(array,2,7)：");
//		TestArrays.output(array1);
//
//		// 对整个数组进行排序
//		Arrays.sort(array1);
//		System.out.println("对整个数组进行排序：Arrays.sort(array1)：");
//		TestArrays.output(array1);

		// 比较数组元素是否相等
//		System.out.println("比较数组元素是否相等:Arrays.equals(array, array1):" + "\n" + Arrays.equals(array, array1));
//		int[] array2 = array1.clone();
//		System.out.println("克隆后数组元素是否相等:Arrays.equals(array1, array2):" + "\n" + Arrays.equals(array1, array2));
	}
}