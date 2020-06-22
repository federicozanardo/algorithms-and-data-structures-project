class QuickSort
{
	public static void main(String[] args)
	{
		QuickSort quickSort = new QuickSort();
		double[] list = {0.1 , 0.35, 0.05, 0.1, 0.15, 0.05, 0.2};
		System.out.println(quickSort.useProblem(list));
	}

	public double useProblem(double[] list)
	{
		if(list.length == 1)
		{
			return list[0];
		}

		double somma = 0;
		double tmpSomma = 0;
		for(int i=0; i<list.length; i=i+1)
		{
			somma = somma + list[i];
		}
		somma = somma/2;
		sort(list, 0, list.length-1);

		for(int i=0; i<list.length; i=i+1)
		{
			tmpSomma = tmpSomma + list[i];

			if(tmpSomma >= somma)
			{
				return list[i];
			}
		}

		return 0;
	}

	/* This function takes last element as pivot, 
	places the pivot element at its correct 
	position in sorted array, and places all 
	smaller (smaller than pivot) to left of 
	pivot and all greater elements to right 
	of pivot */
	int partition(double arr[], int low, int high)
	{ 
		double pivot = arr[high];
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) 
		{ 
			// If current element is smaller than or 
			// equal to pivot 
			if (arr[j] <= pivot) 
			{ 
				i++; 

				// swap arr[i] and arr[j] 
				double temp = arr[i];
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 

		// swap arr[i+1] and arr[high] (or pivot) 
		double temp = arr[i+1];
		arr[i+1] = arr[high]; 
		arr[high] = temp; 

		return i+1; 
	} 


	/* The main function that implements QuickSort() 
	arr[] --> Array to be sorted, 
	low --> Starting index, 
	high --> Ending index */
	void sort(double arr[], int low, int high)
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high);

			// Recursively sort elements before 
			// partition and after partition 
			sort(arr, low, pi-1); 
			sort(arr, pi+1, high); 
		} 
	} 

	/* A utility function to print array of size n */
	static void printArray(int arr[])
	{ 
		int n = arr.length;
		for (int i=0; i<n; ++i)
			System.out.print(arr[i]+" "); 
		System.out.println(); 
	}
}