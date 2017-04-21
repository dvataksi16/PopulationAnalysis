
/*
 * created by Denisa Vataksi
 */
import java.io.*; import java.util.*; 
public class PopulationAnalysis {

	public static void main(String[] args) throws IOException {
		File file = new File("ds.csv");
		
		Scanner input = new Scanner(file);
		/*
		 * reading the file 
		 */
		ArrayList<Region> allRegions = new ArrayList<Region>();
		ArrayList<Integer> years = new ArrayList<Integer>();
		ArrayList<Integer> populations = new ArrayList<Integer>();
		while(input.hasNext()){
			String regionRawData = input.nextLine();
			if(regionRawData.contains("Geographic")){
				//year arraylist
				String[] yearInfo = regionRawData.split(",");
				for(int i = 1; i < yearInfo.length; i ++){
					if(yearInfo[i] != null)
						years.add(Integer.parseInt(yearInfo[i]));	
				}continue;
			}
			String[] regionInfo = regionRawData.split(",");
			String name = regionInfo[0];
			//population for each region arraylists
			populations = new ArrayList<Integer>();
			for(int i = 1; i < regionInfo.length; i ++){
				int population = (int)Double.parseDouble(regionInfo[i]);
				populations.add(population);	
			}
			Region region = new Region(name, populations, years);
			//populating the file into large arraylist
			allRegions.add(region);
		}
		
		//separate United States, regions and states into separate arraylists/variable
		
		Region unitedStates = allRegions.get(0);
		ArrayList<Region> regions = new ArrayList<Region>();
		ArrayList<Region> states = new ArrayList<Region>();
		
		//populate the 4 regions
		for(int i = 1; i<= 4; i ++){
			regions.add(allRegions.get(i));
		}//populate the 50 states and territories
		for(int i = 5; i < allRegions.size(); i ++){
			states.add(allRegions.get(i));
		}
		
		
		input = new Scanner(System.in);
		System.out.print("First year: "); int year1 = input.nextInt();
		System.out.print("Second year: "); int year2 = input.nextInt();
		
		System.out.println();
		//setting the year  for US, regions, states
		unitedStates.year1 = year1; unitedStates.year2 = year2;
		for(Region r: regions){
			r.year1 = year1; r.year2 = year2;
		}for(Region s: states){
			s.year1 = year1; s.year2 = year2;
		}

		
		Scanner in = new Scanner(System.in);
		System.out.print("1. Bubble Sort 2. Merge Sort 3. Quick Sort 4. Improved QuickSort ");
		int choice = in.nextInt();
		
		System.out.println();
		System.out.println("The percent change of the United States between " + year1 + " and " + year2 +" is " + unitedStates.getPercentChange()+"%");
		System.out.println();
		
		if(choice == 1){
			
			System.out.println("Bubble Sort for the Percent Changes of the Regions between " + year1 + " and " + year2 + " from highest to lowest...");
			long startTime = System.nanoTime();
			regions = bubbleSort(regions, 0, regions.size()-1);
			double totalTime = (System.nanoTime() - startTime)/1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			System.out.println();print(regions);System.out.println();
			
			
			System.out.println("Bubble Sort for the Percent Changes of the States between " + year1 + " and " + year2 + " from highest to lowest...");
			startTime = System.nanoTime();
			regions = bubbleSort(states, 0, states.size()-1);
			totalTime = (System.nanoTime() - startTime)/1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			System.out.println();print(states);System.out.println();
		}
		if(choice == 2){
			//converting the array lists to arrays
			Region regionArray[] = new Region[regions.size()];              
			for(int i =0;i<regions.size();i++){
			  regionArray[i] = regions.get(i);
			}
			Region stateArray[] = new Region[states.size()];
			for(int i = 0; i < states.size(); i ++){
				stateArray[i] = states.get(i);
			}
			
			
			System.out.println("Merge Sort for the Percent Changes of the Regions between " + year1 + " and " + year2 + " from highest to lowest...");
			long startTime = System.nanoTime();
			regionArray = mergeSort(regionArray);
			double totalTime = (System.nanoTime() - startTime) /1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			//converting arrays back to arraylists
			ArrayList<Region> newRegions = new ArrayList<Region>();
			for(int i = 0; i <regionArray.length; i++){
				newRegions.add(regionArray[i]);
			}regions = newRegions;
			System.out.println();print(regions);System.out.println();
			
			System.out.println("Merge Sort for the Percent Changes of the States between " + year1 + " and " + year2 + " from highest to lowest...");
			startTime = System.nanoTime();
			stateArray = mergeSort(stateArray);
			totalTime = (System.nanoTime() - startTime) /1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			
			//converting arrays back to arraylist
			ArrayList<Region> newStates = new ArrayList<Region>();
			for (int i = 0; i < stateArray.length; i++){
				newStates.add(stateArray[i]);
			}states = newStates;
			System.out.println();print(states);System.out.println();
			
		}	
		if (choice == 3){
			System.out.println("Quick Sort for the Percent Changes of the Regions between " + year1 + " and " + year2 + " from highest to lowest...");
			long startTime = System.nanoTime();
			regions = quickSort(regions, 0, regions.size()-1);
			double totalTime = (System.nanoTime() - startTime) / 1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			System.out.println();print(regions);System.out.println();
			
			System.out.println("Quick Sort for the Percent Changes of the States between " + year1 + " and " + year2 + " from highest to lowest...");
			startTime = System.nanoTime();
			states = quickSort(states, 0, states.size()-1);
			totalTime = (System.nanoTime() - startTime) / 1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			System.out.println();print(states); System.out.println();
		}	
		if (choice == 4){
			System.out.println("Improved Quick Sort for the Percent Changes of the Regions between " + year1 + " and " + year2 + " from highest to lowest...");
			long startTime = System.nanoTime();
			regions = improvedQuickSort(regions, 0, regions.size()-1);
			double totalTime = (System.nanoTime() - startTime) /1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			System.out.println();print(regions); System.out.println();
			
			System.out.println("Improved Quick Sort for the Percent Changes of the States between " + year1 + " and " + year2 + " from highest to lowest...");
			startTime = System.nanoTime();
			states = improvedQuickSort(states, 0, states.size()-1);
			totalTime = (System.nanoTime() - startTime) /1000000.0;
			System.out.println("Elapsed time: " + totalTime + " seconds");
			System.out.println();print(states); System.out.println();
		}
		
	}	
	
	/**
	 * prints out the arraylist of regions in order
	 * @param regions
	 */
	public static void print(ArrayList<Region> regions){
		int counter = 1;
		for(Region r: regions){
			System.out.println(counter + ". " + r.getName() + ": " + r.getPercentChange() + " %");
			counter++;
		}
	}
	/**
	 * iteratively sorts via bubble sort
	 * @param regions
	 * @param first index
	 * @param last index
	 * @return sorted regions arraylist
	 */
	public static ArrayList<Region> bubbleSort(ArrayList<Region> regions, int first, int last){
		for(int i = regions.size()-1; i >0; i--){
			for(int j = 1; j <= i; j++){
				if(regions.get(j-1).getPercentChange() < regions.get(j).getPercentChange()){
					Collections.swap(regions, j, j-1);
				}
			}
		}
		
		return regions;
	}
	/**
	 * sorts using merge sort
	 * @param arr
	 * @return sorted Region arr
	 */
	public static Region[] mergeSort(Region[] arr){
		if(arr.length <= 1){
			return arr;
		}
		int mid = arr.length / 2;
		Region[] arr1 = new Region [mid];
		Region[] arr2 = new Region [arr.length - mid];
		System.arraycopy(arr, 0, arr1, 0, arr1.length);
		System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);
		mergeSort(arr1);mergeSort(arr2);
		merge(arr1, arr2, arr);
		return arr;
	}
	
	/**
	 * Merges 2 int arrays into 1 big sorted result array
	 * @param arr1
	 * @param arr2
	 * @param result: merged array
	 */
	public static void merge(Region[] arr1, Region[] arr2, Region[] result){
		int a1 = 0;//counter for arr1 
		int a2 = 0; //counter for arr2
		int r = 0; //counter for result
		while(a1 < arr1.length && a2 < arr2.length){
			if(arr1[a1].getPercentChange() >= arr2[a2].getPercentChange()){
				result[r] = arr1[a1]; a1++; r++;
			}else{
				result[r] = arr2[a2]; a2++; r++;
			}
		}while(a1 < arr1.length){
			result[r] = arr1[a1]; a1++; r++;
		}while(a2 < arr2.length){
			result[r] = arr2[a2]; a2++; r++;
		}
	}
	/**
	 * sorts using basic quick sort
	 * @param regions
	 * @param left
	 * @param right
	 * @return sorted Region array
	 */
	public static ArrayList<Region> quickSort(ArrayList<Region> regions, int left, int right){
		//base case
		if(right <= left){
			return regions;
		}//choose a pivot --partition arrays
		int pivot = partition(regions,left,right);
		//recursive calls
		quickSort(regions, left, pivot-1);
		quickSort(regions,pivot,right);	
		return regions;
	}
	/**
	 * makes the first index the pivot
	 * @param regions
	 * @param left
	 * @param right
	 * @return pivot
	 */
	public static int partition(ArrayList<Region> regions, int left, int right){
		//make first index pivot at first
		double pivot = regions.get(left).getPercentChange();
		while(left<= right){
			while(regions.get(left).getPercentChange() > pivot){
				left ++;
			}while(regions.get(right).getPercentChange() < pivot){
				right --;
			}//swap
			if(left <= right){
				Collections.swap(regions, left, right);
				left++; right--;
			}
		}return left;
	}
	
	/**
	 * sorts using an improved version of quick sort that uses a quicker running time
	 * @param regions
	 * @param left
	 * @param right
	 * @return sorted Region array
	 */
	public static ArrayList<Region> improvedQuickSort(ArrayList<Region> regions, int left, int right){
		//base case
		if(right <= left){
			return regions;
		}//choose a pivot --partition arrays
		int pivot = medianPartition(regions,left,right);
		//recursive calls
		quickSort(regions, left, pivot-1);
		quickSort(regions,pivot,right);	
		return regions;
	}
	/**
	 * makes the median the pivot
	 * @param regions
	 * @param left
	 * @param right
	 * @return pivot
	 */
	public static int medianPartition(ArrayList<Region> regions, int left, int right){
		/*
		 * Pivot is the median index instead of the first -- Big O in the worst case scenario is now O(logN)
		 */
		
		int pivotIndex = (right -left) /2;
		double pivot = regions.get(pivotIndex).getPercentChange();
		while(left<= right){
			while(regions.get(left).getPercentChange() > pivot){
				left ++;
			}while(regions.get(right).getPercentChange() < pivot){
				right --;
			}//swap
			if(left <= right){
				Collections.swap(regions, left, right);
				left++; right--;
			}
		}return left;
	}
	
	

}
