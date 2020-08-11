//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     A class compares insert() of my RBT and BST.
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class CompareDS {
	public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException {
		
		int[] TestDataSize = {10, 100, 1000};
		System.out.println("Description: Comparing the insertion time.\r\n");
		for(int i=0; i < TestDataSize.length; i++) {

				BST<Integer, String> myBST = new BST<Integer, String>();
				RBT<Integer, String> myRBT = new RBT<Integer, String>();
				///////////////////////// DS_MY insertion///////////////////////

				System.out.println("My BST is doing work for "+TestDataSize[i]+" values");
				long startTime_My = System.nanoTime();
				
				for(int j=0; j < TestDataSize[i];j++) {
					
					myBST.insert(j, ""+j);
					
				}
				
				long endTime_MY = System.nanoTime();
				System.out.println("It took "+(endTime_MY - startTime_My)+" ns to "
					+ "process "+TestDataSize[i]+" pairs");
				///////////////////////// RBT insertion///////////////////////
				System.out.println("My RBT is doing work for "+TestDataSize[i]+
						" values");
					long startTime_B = System.nanoTime();
					
					for(int j=0; j < TestDataSize[i];j++) {
						
						myRBT.insert(j, ""+j);
						
					}
					
					long endTime_B = System.nanoTime();
					System.out.println("It took "+(endTime_B - startTime_B)+" ns to "
						+ "process "+TestDataSize[i]+" pairs\n\n");
			}
		}
			 
		}

