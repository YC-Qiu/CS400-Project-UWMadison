//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     A class compares methods from DS_Brian and DS_My.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class compares methods from DS_Brian and DS_My.
 * @author YC Qiu
 * @version 1.0
 */
public class CompareDS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("CompareDS.main Compares work time for: DS_My and"
			+ " DS_Brian");
		

		int[] TestDataSize = {100, 1000, 10000, 100000};
		// work code to be timed for a single run
		
		
		
		for(int i=0; i < TestDataSize.length; i++) {
			DS_My MYDS = new DS_My();
			DS_Brian BsDS = new DS_Brian();
			System.out.println("Description: Comparing the insertion time.\r\n");
			///////////////////////// DS_MY insertion///////////////////////
			System.out.println("DS_My is doing work for "+TestDataSize[i]+" values");
			long startTime_My = System.nanoTime();
			
			for(int j=0; j < TestDataSize[i];j++) {
				
				MYDS.insert(j+"", "v:"+j);
				
			}
			
			long endTime_MY = System.nanoTime();
			System.out.println("It took "+(endTime_MY - startTime_My)+" ns to "
				+ "process "+TestDataSize[i]+" items");
			///////////////////////// DS_Brian insertion///////////////////////
			System.out.println("DS_Brian is doing work for "+TestDataSize[i]+
				" values");
			long startTime_B = System.nanoTime();
			
			for(int j=0; j < TestDataSize[i];j++) {
				
				BsDS.insert(j+"", "v:"+j);
				
			}
			
			long endTime_B = System.nanoTime();
			System.out.println("It took "+(endTime_B - startTime_B)+" ns to "
				+ "process "+TestDataSize[i]+" items\n\n");
			///////////////////////// DS_MY retrievals ///////////////////////
			System.out.println("Description: Comparing the retrieval time.\r\n");
			System.out.println("DS_My is doing work for "+TestDataSize[i]+" values");
			startTime_My = System.nanoTime();
			MYDS.get((TestDataSize[i]-1)/2 +"");
			endTime_MY = System.nanoTime();
			System.out.println("It took "+(endTime_MY - startTime_My)+" ns to "
				+ "process "+TestDataSize[i]+" items");
			///////////////////////// DS_Brian retrievals ///////////////////////
			System.out.println("DS_Brian is doing work for "+
				TestDataSize[i]+" values");
			startTime_B = System.nanoTime();
			BsDS.get((TestDataSize[i]-1)/2 +"");
			endTime_B = System.nanoTime();
			System.out.println("It took "+(endTime_B - startTime_B)+" ns to "
				+ "process "+TestDataSize[i]+" items\n\n");
		}
	}

}
