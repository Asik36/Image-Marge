import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;




public class suf {
	private static int userinput;
	private static String naem;

	public static void main(String[] args) throws IOException {
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		System.out.println("file name:");
		String fileName = scan.next();
		System.out.println("name your child");
		
		naem = scan.next();


		
		System.out.println("1. suffle image");
		System.out.println("2. change colors");
		System.out.println("3. switch image colors");
		int userInput = scan.nextInt();
		if (userInput == 1) {
			// randomies pixels in array
			
			File file = new File("src/"+ fileName + ".png");
			BufferedImage image = ImageIO.read(file);
			int[][] arr = new int[image.getWidth()][image.getHeight()];

			// copy image pixels into array
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					arr[x][y] = image.getRGB(x, y);
				}
			}
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					int index_x = rand.nextInt(image.getWidth());
					int index_y = rand.nextInt(image.getHeight());
					int temp = arr[index_x][index_y];
					arr[index_x][index_y] = arr[x][y];
					arr[x][y] = temp;
					
					
				}
				makeImageWithPixel(image.getWidth(), image.getHeight(), arr,null);
				
			}
		} else if (userInput == 2) {
			File file = new File("src/"+ fileName + ".png");
			BufferedImage image = ImageIO.read(file);
			int[][] arr = new int[image.getWidth()][image.getHeight()];

			// copy image pixels into array
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					arr[x][y] = image.getRGB(x, y);
				}
			}
			int[] arrgb = new int[3];
			int[] variations = new int[3];

			for (int i1 = 0; i1 < arrgb.length; i1++) {
				for (int i2 = 0; i2 < arrgb.length; i2++) {
					for (int i3 = 0; i3 < arrgb.length; i3++) {
						for (int y = 0; y < image.getHeight(); y++) {
							for (int x = 0; x < image.getWidth(); x++) {

								int pix = image.getRGB(x, y);

								int blue = pix & 0xff;
								int green = (pix & 0xff00) >> 8;
								int red = (pix & 0xff0000) >> 16;
								arrgb[0] = blue;
								arrgb[1] = green;
								arrgb[2] = red;
								int alpha = (pix & 0xff000000) >> 24;

								int newPix = (arrgb[i1]) + (arrgb[i2] << 8) + (arrgb[i3] << 16) + (alpha << 24);
								arr[x][y] = newPix;

							}
						}
						variations[0] = i1;
						variations[1] = i2;
						variations[2] = i3;
						makeImageWithPixel(image.getWidth(), image.getHeight(), arr, variations);

					}
				}
			}
		} else if (userInput == 3) {
			File file = new File("src/"+ fileName + ".png");
			BufferedImage image = ImageIO.read(file);
			System.out.println("file 2 name:");
			String fileName2 = scan.next();
			File file2 = new File("src/"+ fileName2 + ".png");
			BufferedImage image2 = ImageIO.read(file2);
			
			int height2 = image2.getHeight();
			int width2 = image2.getWidth();
			MyPix[] sourceArr = new MyPix[image.getHeight() * image.getWidth()];
			MyPix[] originalArr = new MyPix[height2 * width2];
			int count = 0;

			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++, count++) {
					sourceArr[count] = new MyPix(image.getRGB(x, y), new Point(x,y));
					
				}
			}
			for(int i = 0; i < sourceArr.length; i++) {
			System.out.println(sourceArr[i]);
			}
			
			Arrays.sort(sourceArr,new Comparator<MyPix>(){

				@Override
				public int compare(MyPix mp1, MyPix mp2) {
					return (mp1.pixel==mp2.pixel ? 0 :( mp1.pixel>mp2.pixel ? 1: -1));
				}
				
			}) ;
			for(int i = 0; i < sourceArr.length; i++) {
				System.out.println(sourceArr[i]);
				}

			count = 0;
			
			for (int y = 0; y < height2; y++) {
				for (int x = 0; x < width2; x++, count++) {
					originalArr[count] = new MyPix(image2.getRGB(x, y), new Point(x,y));
					
				}
			}
			

			Arrays.sort(originalArr,new Comparator<MyPix>(){

				@Override
				public int compare(MyPix mp1, MyPix mp2) {
					return (mp1.pixel==mp2.pixel ? 0 :( mp1.pixel>mp2.pixel ? 1: -1));
				}
				
			}) ;

			int n = 0;
			double k = (double)sourceArr.length/(double)originalArr.length;
			for (int i = 0; i < originalArr.length && n < sourceArr.length-1; i++) {
				n = (int)Math.round(i*k);
				System.out.println(n+"==>"+i+" "+sourceArr[n].pixel+"--->"+originalArr[i].pixel);
				
				originalArr[i].pixel = sourceArr[n].pixel;
				
			}	
			for(int i = 0; i < originalArr.length; i++) {
				System.out.println(originalArr[i]);
				}
			
			makeImageWithPixelMyPix(originalArr , width2 , height2);
			
		}
	}
	private static Stack[] mergeArrSt(Stack[] st1 ,Stack[] st2 ) { 
		Stack[] arrFin = new Stack[st2.length];
		for(int i = 0 ;i<arrFin.length;i++)
		{
			arrFin[i] = new Stack();
		}
		for (int i = 0; i < st2.length; i++) {
			//System.out.println(st1[i]);

			
			if(st1[i].getSize() < st2[i].getSize()) {
				System.out.println("stuck1 has " + st1[i].getSize()+ " places, while it need " + st2[i].getSize());
				for (int j = 1; j < i && st1[i].getSize() != st2[i].getSize(); j++) {
					System.out.println("going back to" + (i-j));
					if(st1[i-j] != null) {
						System.out.println("found a pixel at" + (i-j));
						while(st1[i - j] != null) {
						MyPix temp = st1[i-j].top().getInfo();
						st1[i-j].pop();
						st1[i].push(temp); 
						}
						System.out.println("pixel pushed successfully, " + (st2[i].getSize() - st1[i].getSize()) + "left" );
					}
				
					if(i - j == 0) {
						System.out.println("unable to go back, going forward");
						for (int j2 = 1; j2 < arrFin.length && st1[i].getSize() != st2[i].getSize(); j2++){
							System.out.println("found a pixel at" + (i+j));
							while(st1[i - j] != null) {
								MyPix temp = st1[i+j2].top().getInfo();
								st1[i+j2].pop();
								st1[i].push(temp); 
								}
							System.out.println("pixel pushed successfully, " + (st2[i].getSize() - st1[i].getSize()) + "left" );
						}
					}
						
				}
			}
			//crating new stuck
			for (int j = 0; j < st2[i].getSize(); j++) {
				MyPix temp = new MyPix(st1[i].top().getInfo().pixel , st2[i].top().getInfo().location);
				st1[i].pop();
				st2[i].pop();
				arrFin[i].push(temp);
				System.out.println("stuck " + i + " on the "+ j + " pixel ");
			}
			System.out.println("stuck is full... strating a new one");
		}
		System.out.println("resorting is done!");
		return arrFin;
		
		
	}
	private static void arrSort(MyPix[] arr) {
		int counter = 0;
		int pos;
		MyPix temp;
		for (int i = 0; i < arr.length; i++) {
			pos = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].pixel < arr[pos].pixel) {
					pos = j;
					System.out.println("counting! - "+counter++);
				}
			}
			temp = arr[pos];
			arr[pos] = arr[i];
			arr[i] = temp;
		}
	}
	private static void makeImageWithPixelMyPix(MyPix arr[] , int width , int height) {
		System.out.println(width + " " + height);
		BufferedImage imageOut = null;
		imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].toString());
			
			MyPix toDraw = arr[i];
			imageOut.setRGB(toDraw.location.x,toDraw.location.y, toDraw.pixel);
			 
			
		}
		File newFile = new File("src/INGRR/" + naem + ".png");

		try {
			ImageIO.write(imageOut, "png", newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void makeImageWithPixel(int width, int height, int[][] arr, int[] variations) {
		// r g b
		// 2 1 2
		BufferedImage imageOut = null;
		imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// crating new image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				imageOut.setRGB(x, y, arr[x][y]);
			}
		}
		
		try {
			if(variations != null) {
			String serial = variations[0] == 0 ? "r" : variations[0] == 1 ? "g" : "b";
			String con = variations[1] == 0 ? "r" : variations[1] == 1 ? "g" : "b";
			serial = serial + con;
			con = variations[2] == 0 ? "r" : variations[2] == 1 ? "g" : "b";
			serial = serial + con;
			System.out.println(serial);
			
			File newFile = new File("src/packthingy/" + naem + "(" + serial + ").png");
			
			ImageIO.write(imageOut, "png", newFile);
			} else {
				File newFile = new File("src/packthingy/" + naem + ".png");
				
				ImageIO.write(imageOut, "png", newFile);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
		System.out.println("done");
	}

	static int partition(MyPix array[], int low, int high) {
	    
	    // choose the rightmost element as pivot
	    int pivot = array[high].pixel;
	    
	    // pointer for greater element
	    int i = (low - 1);

	    // traverse through all elements
	    // compare each element with pivot
	    for (int j = low; j < high; j++) {
	      if (array[j].pixel <= pivot) {

	        // if element smaller than pivot is found
	        // swap it with the greatr element pointed by i
	        i++;

	        // swapping element at i with element at j
	        MyPix temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	      }

	    }

	    // swapt the pivot element with the greater element specified by i
	    MyPix temp = array[i + 1];
	    array[i + 1] = array[high];
	    array[high] = temp;

	    // return the position from where partition is done
	    return (i + 1);
	  }
	
	static void quickSort(MyPix[] array) {
			quickSort(array, 0, array.length-1);
		}
	  static void quickSort(MyPix[] array, int low, int high) {
	    if (low < high) {
	    	
	      // find pivot element such that
	      // elements smaller than pivot are on the left
	      // elements greater than pivot are on the right
	      int pi = partition(array, low, high);
	      
	      // recursive call on the left of pivot
	      quickSort(array, low, pi - 1);

	      // recursive call on the right of pivot
	      quickSort(array, pi + 1, high);
	    }
	  }
	  static void merge(MyPix arr[], int p, int q, int r) {


		    // Create L ← A[p..q] and M ← A[q+1..r]
		    int n1 = q - p + 1;
		    int n2 = r - q;

		    int L[] = new int[n1];
		    int M[] = new int[n2];

		    for (int i = 0; i < n1; i++)
		      L[i] = arr[p + i].pixel;
		    for (int j = 0; j < n2; j++)
		      M[j] = arr[q + 1 + j].pixel;

		    // Maintain current index of sub-arrays and main array
		    int i, j, k;
		    i = 0;
		    j = 0;
		    k = p;
		    //0 1 4 6 
		    //1 3 5 6
		    // Until we reach either end of either L or M, pick larger among
		    // elements L and M and place them in the correct position at A[p..r]
		    while (i < n1 && j < n2) {
		      if (L[i] <= M[j]) {
		        arr[k].pixel = L[i];
		        i++;
		      } else {
		        arr[k].pixel = M[j];
		        j++;
		      }
		      k++;
		    }

		    // When we run out of elements in either L or M,
		    // pick up the remaining elements and put in A[p..r]
		    while (i < n1) {
		      arr[k].pixel = L[i];
		      i++;
		      k++;
		    }

		    while (j < n2) {
		      arr[k].pixel = M[j];
		      j++;
		      k++;
		    }
		  }

		  // Divide the array into two subarrays, sort them and merge them
	  static void mergeSort(MyPix arr[], int l, int r) {

		    if (l < r) {
		    	System.out.println("called");
		      // m is the point where the array is divided into two subarrays
		      int m = (l + r) / 2;

		      mergeSort(arr, l, m);
		      mergeSort(arr, m + 1, r);

		      // Merge the sorted subarrays
		      merge(arr, l, m, r);
		    }
		  }
	  static void printArray(int arr[]) {

		    int n = arr.length;
		    for (int i = 0; i < n; ++i)
		      System.out.print(arr[i] + " ");
		    System.out.println();
		  }
	  public static int[][] removeDuplicateElements(int arr[]) {


		int temp[][] = new int[arr.length][2];
		int count;
		int step = -1;
		for (int i = 0; i < temp.length; i++) {
			if (arr[i] == step) {
				temp[i][0] = -1;
				temp[i][1] = 0;
			} else {
				count = 0;
				for (int i2 = i; i2 < arr.length && arr[i2] == arr[i]; i2++, count++)
					;
				
				temp[i][0] = arr[i];
				temp[i][1] = count;
				step = arr[i];
			}

		}
		int j = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i][0] == -1) {
				j++;
			}
		}
		int[][] arrFin = new int[arr.length - j][2];
		int countemp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (temp[i][0] != -1) {

				arrFin[countemp][0] = temp[i][0];
				arrFin[countemp][1] = temp[i][1];
				countemp++;
			}
		}
		return arrFin;
	}
	  public static Stack[] removeDuplicateElementsMyPix(MyPix arr[]) {

		  int num = countDub(arr);
		  	  
		  Stack[] temp = new Stack[num];
		  
		  int count = 0;
		  for (int i = 0; i < temp.length; i++) {
			  
			  temp[i] = new Stack();  
			  while(arr[count+1] != null && arr[count].pixel == arr[count + 1].pixel ) {
				  temp[i].push(arr[count]);
				  count++;
			  }
			  if(arr[count+1] == null)
			  {
				  temp[i].push(arr[count]);
				  break;
			  }
			  temp[i].push(arr[count++]);
			  //temp[i].print();
			  
			  // 1 2 3 4 5 6 6 6 6 7 7

			
		  }
			  return temp;
		}
	  private static int countDub(MyPix[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			if(arr[i].pixel != arr[i + 1].pixel) {
				count++;
		}
	}
		return count; 
	  }
}
