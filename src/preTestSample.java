
import java.util.Arrays;
import java.util.Scanner;

public class preTestSample {
	private final double[][] salesFeeMatrix;
	private final int rows, cols, dim;
	private final double[] salesMan, salesFee;
	private final int[] minSaleManByJob;
	private final double[] minSaleValueByJob;
	private final int[] matchJobBySalesMan, matchSalesManByJob;
	private final int[] parentSalesManByCommittedJob;
	private final boolean[] committedSalesMan;

	public preTestSample(double[][] costMatrix) {
		this.dim = Math.max(costMatrix.length, costMatrix[0].length);
		this.rows = costMatrix.length;
		this.cols = costMatrix[0].length;
		this.salesFeeMatrix = new double[this.dim][this.dim];
		for (int w = 0; w < this.dim; w++) {
			if (w < costMatrix.length) {
				if (costMatrix[w].length != this.cols) {
					throw new IllegalArgumentException("Irregular cost matrix");
				}
				this.salesFeeMatrix[w] = Arrays.copyOf(costMatrix[w], this.dim);
			} else {
				this.salesFeeMatrix[w] = new double[this.dim];
			}
		}
		salesMan = new double[this.dim];
		salesFee = new double[this.dim];
		minSaleManByJob = new int[this.dim];
		minSaleValueByJob = new double[this.dim];
		committedSalesMan = new boolean[this.dim];
		parentSalesManByCommittedJob = new int[this.dim];
		matchJobBySalesMan = new int[this.dim];
		Arrays.fill(matchJobBySalesMan, -1);
		matchSalesManByJob = new int[this.dim];
		Arrays.fill(matchSalesManByJob, -1);
	}

	protected void computeInitialFeasibleSolution() {
		for (int j = 0; j < dim; j++) {
			salesFee[j] = Double.POSITIVE_INFINITY;
		}
		for (int w = 0; w < dim; w++) {
			for (int j = 0; j < dim; j++) {
				if (salesFeeMatrix[w][j] < salesFee[j]) {
					salesFee[j] = salesFeeMatrix[w][j];
				}
			}
		}
	}

	public int[] execute() {
		
		reduce();
		computeInitialFeasibleSolution();
		greedyMatch();
		int w = fetchUnmatchedSalesMan();
		while (w < dim) {
			initializePhase(w);
			executePhase();
			w = fetchUnmatchedSalesMan();
		}
		int[] result = Arrays.copyOf(matchJobBySalesMan, rows);
		for (w = 0; w < result.length; w++) {
			if (result[w] >= cols) {
				result[w] = -1;
			}
		}
		return result;
	}

	protected void executePhase() {
		while (true) {
			int minSlackWorker = -1, minSlackJob = -1;
			double minSlackValue = Double.POSITIVE_INFINITY;
			for (int j = 0; j < dim; j++) {
				if (parentSalesManByCommittedJob[j] == -1) {
					if (minSaleValueByJob[j] < minSlackValue) {
						minSlackValue = minSaleValueByJob[j];
						minSlackWorker = minSaleManByJob[j];
						minSlackJob = j;
					}
				}
			}
			if (minSlackValue > 0) {
				updateLabeling(minSlackValue);
			}
			parentSalesManByCommittedJob[minSlackJob] = minSlackWorker;
			if (matchSalesManByJob[minSlackJob] == -1) {
				
				int committedJob = minSlackJob;
				int parentWorker = parentSalesManByCommittedJob[committedJob];
				while (true) {
					int temp = matchJobBySalesMan[parentWorker];
					match(parentWorker, committedJob);
					committedJob = temp;
					if (committedJob == -1) {
						break;
					}
					parentWorker = parentSalesManByCommittedJob[committedJob];
				}
				return;
			} else {
				
				int worker = matchSalesManByJob[minSlackJob];
				committedSalesMan[worker] = true;
				for (int j = 0; j < dim; j++) {
					if (parentSalesManByCommittedJob[j] == -1) {
						double slack = salesFeeMatrix[worker][j] - salesMan[worker] - salesFee[j];
						if (minSaleValueByJob[j] > slack) {
							minSaleValueByJob[j] = slack;
							minSaleManByJob[j] = worker;
						}
					}
				}
			}
		}
	}

	protected int fetchUnmatchedSalesMan() {
		int w;
		for (w = 0; w < dim; w++) {
			if (matchJobBySalesMan[w] == -1) {
				break;
			}
		}
		return w;
	}

	protected void greedyMatch() {
		for (int w = 0; w < dim; w++) {
			for (int j = 0; j < dim; j++) {
				if (matchJobBySalesMan[w] == -1 && matchSalesManByJob[j] == -1
						&& salesFeeMatrix[w][j] - salesMan[w] - salesFee[j] == 0) {
					match(w, j);
				}
			}
		}
	}

	protected void initializePhase(int w) {
		Arrays.fill(committedSalesMan, false);
		Arrays.fill(parentSalesManByCommittedJob, -1);
		committedSalesMan[w] = true;
		for (int j = 0; j < dim; j++) {
			minSaleValueByJob[j] = salesFeeMatrix[w][j] - salesMan[w] - salesFee[j];
			minSaleManByJob[j] = w;
		}
	}

	protected void match(int w, int j) {
		matchJobBySalesMan[w] = j;
		matchSalesManByJob[j] = w;
	}

	protected void reduce() {
		for (int w = 0; w < dim; w++) {
			double min = Double.POSITIVE_INFINITY;
			for (int j = 0; j < dim; j++) {
				if (salesFeeMatrix[w][j] < min) {
					min = salesFeeMatrix[w][j];
				}
			}
			for (int j = 0; j < dim; j++) {
				salesFeeMatrix[w][j] -= min;
			}
		}
		double[] min = new double[dim];
		for (int j = 0; j < dim; j++) {
			min[j] = Double.POSITIVE_INFINITY;
		}
		for (int w = 0; w < dim; w++) {
			for (int j = 0; j < dim; j++) {
				if (salesFeeMatrix[w][j] < min[j]) {
					min[j] = salesFeeMatrix[w][j];
				}
			}
		}
		for (int w = 0; w < dim; w++) {
			for (int j = 0; j < dim; j++) {
				salesFeeMatrix[w][j] -= min[j];
			}
		}
	}

	protected void updateLabeling(double slack) {
		for (int w = 0; w < dim; w++) {
			if (committedSalesMan[w]) {
				salesMan[w] += slack;
			}
		}
		for (int j = 0; j < dim; j++) {
			if (parentSalesManByCommittedJob[j] != -1) {
				salesFee[j] -= slack;
			} else {
				minSaleValueByJob[j] -= slack;
			}
		}
	}

/* 
 * sample : rows 8 * cols8 
 * 
82	83	69	92	90	65	75	80
77	37	49	92	35	85	55	65
11	69	5	86	125	95	90	105
23	47	98	23	45	110	95	115
72	65	84	77	90	105	46	71
64	73	120	83	100	67	45	81
83	59	67	92	49	103	54	47
99	88	37	79	67	80	74	109

 *
 * sample : row 4 * cols 4
 *
90 75 65 80
35 85 55 65
125 95 90 105
45 110 95 115
*/
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("rows : ");
		int rows = sc.nextInt();
		System.out.print("columns : ");
		int cols = sc.nextInt();
		System.out.println("영업사원 출장비 정보 입력 ");
		double[][] feeValue = new double[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				feeValue[i][j] = sc.nextDouble();
			}
		}
		
//		입력값 정보 확인 
//		System.out.println("The matrix is:");
//		for (int i = 0; i < feeValue.length; i++) {
//			for (int j = 0; j < feeValue[i].length; j++) {
//				System.out.printf("%.2f\t", feeValue[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
//		합수 소요시간 start 
		long startTime = System.nanoTime();
		preTestSample hbm = new preTestSample(feeValue);
		int[] result = hbm.execute();
//		합수 소요시간 end  
		double timeSpent = System.nanoTime() - startTime;

		double resultSum = 0;
//		최소 경비 위치정보 
//		System.out.println("SalesMan Sum Fee : " + Arrays.toString(result));
		
		System.out.println("1. 최소경비 영업사원 리스트");
		for (int i = 0; i < result.length; i++) {
			System.out.print("[" + i + "][" + result[i] + "] ");
			resultSum += feeValue[i][result[i]];
		}
		System.out.println("\n\n");
		System.out.print("2. 최소경비 영원사원 총 합계 = " + resultSum);
		System.out.println("\n\n");
		System.out.println("3. Time Spend : " + timeSpent + " ns");

		sc.close();
	}

}