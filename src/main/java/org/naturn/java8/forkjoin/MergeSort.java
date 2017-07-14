package org.naturn.java8.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 上午8:30:15
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class MergeSort {

	private final ForkJoinPool pool;

	public MergeSort(int parallelism) {
		pool = new ForkJoinPool(parallelism);
	}

	public static class MergeSortTask extends RecursiveAction {

		private static final long serialVersionUID = 6063068141032906958L;

		private int[] array;
		private int high;
		private int low;
		private static final int THRESHOLD = 8;

		protected MergeSortTask(int[] array, int high, int low) {
			this.array = array;
			this.high = high;
			this.low = low;
		}

		@Override
		protected void compute() {
			// TODO Auto-generated method stub
			if (high - low <= THRESHOLD) {
				Arrays.sort(array);
			} else {
				int middle = low + ((high - low) >> 1);
				invokeAll(new MergeSortTask(array,low,middle),new MergeSortTask(array,middle+1,high));
				merge(middle);
			}
		}
		
		private void merge(int middle){
			if(array[middle-1]<array[middle]){
				return;
			}
			int[] copy = new int[high - low];
			System.arraycopy(array, low, copy, 0, copy.length);
			int copyLow = 0;
			int copyHigh = high - low;
			int copyMiddle = middle - low;
			for(int i= low,p = copyLow,q = copyMiddle;i<high;i++){
				if(q >= copyHigh||(p < copyMiddle && copy[p] < copy[q])){
					array[i] = copy[p++];
				}else{
					array[i] = copy[q++];
				}
			}
		}
	}
	
	public void sort(int[] array){
		ForkJoinTask<Void> job = pool.submit(new MergeSortTask(array,0,array.length));
		job.join();
	}
}
