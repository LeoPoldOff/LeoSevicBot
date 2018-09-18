import java.util.HashMap;
import java.util.Map;
import javafx.scene.chart.PieChart.Data;

public class tree {
	public static double main(int sex, Data age, int height, int weight, int region, int smoking, int drinking,
			int sport) {
		Map<Integer, Double> dictOfSex = new HashMap<Integer, Double>();
		Map<Integer, Double> dictOfRegion = new HashMap<Integer, Double>();
		Map<Integer, Double> dictOfSmoking = new HashMap<Integer, Double>();
		Map<Integer, Double> dictOfAlcohol = new HashMap<Integer, Double>();
		Map<Integer, Double> dictOfSport = new HashMap<Integer, Double>();
		dictOfSex.put(1, 1.0);
		dictOfSex.put(2, 1.1);
		dictOfRegion.put(1, 0.85);
		dictOfRegion.put(2, 0.95);
		dictOfRegion.put(3, 1.1);
		dictOfRegion.put(4, 0.8);
		dictOfRegion.put(5, 0.92);
		dictOfRegion.put(6, 0.95);
		dictOfSmoking.put(1, 0.87);
		dictOfSmoking.put(2, 0.92);
		dictOfSmoking.put(3, 0.95);
		dictOfSmoking.put(4, 1.0);
		dictOfAlcohol.put(1, 0.71);
		dictOfAlcohol.put(2, 0.85);
		dictOfAlcohol.put(3, 0.92);
		dictOfAlcohol.put(1, 1.0);
		dictOfSport.put(1, 1.15);
		dictOfSport.put(2, 1.07);
		dictOfSport.put(3, 1.0);
		var normalWeight = weight - height + 115;
		var resultIndexWeight = 0.0;
		if (5 < normalWeight && normalWeight < 10) {
			resultIndexWeight = 0.98;
		}
		if (10 < normalWeight && normalWeight < 20) {
			resultIndexWeight = 0.94;
		}
		if (20 < normalWeight && normalWeight < 40) {
			resultIndexWeight = 0.9;
		}
		if (40 < normalWeight) {
			resultIndexWeight = 0.85;
		}
		if (-10 < normalWeight && normalWeight < -5) {
			resultIndexWeight = 0.98;
		}
		if (-20 < normalWeight && normalWeight < -10) {
			resultIndexWeight = 0.94;
		}
		if (-40 < normalWeight && normalWeight < -20) {
			resultIndexWeight = 0.9;
		}
		if (normalWeight < -40) {
			resultIndexWeight = 0.85;
		}
		return 85 * dictOfSex.get(sex) * dictOfRegion.get(region) * dictOfSmoking.get(smoking)
				* dictOfAlcohol.get(drinking) * dictOfSport.get(sport) * resultIndexWeight;
	}

}