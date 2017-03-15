package com.starscube.websocket;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		 String[] all={"1","20","30","-1","-5","60","0","99"};
		
		 
		 String str="´ÞÂ·Â·°®ÁºÏþÇì£¡";
		 StringBuffer sb = new StringBuffer(str);
		 sb.reverse();
		 System.out.println(sb);
	}
	public static String sort(String str) {
		String str2 = "";
		for(int i = str.length() - 1; i >= 0; i--){
			str2 += String.valueOf(str.charAt(i));
		}
		return str2;
	}

	private static void paixuhaha(String[] all) {
		int[] array = revlertString(all);
		
		for (int i : array) {
			System.out.println(i);
		}
	}
	
	public static int[] revlertString(String[] arr){
		int[] nums = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			nums[i]= Integer.parseInt(arr[i]);
		}
		 paixu(nums);
		 return nums;
	}
	
	public static void paixu(int[] all){
		for (int i = 0; i < all.length; i++) {
			for (int j = 0; j < all.length-i-1; j++) {
				if(all[j]>all[j+1]){
					int tep = all[j];
					all[j]=all[j+1];
					all[j+1]= tep;
				}
			}
		}
		
	}

	
	
	
	
	
	
	
	
	
	
}
