import java.util.HashMap;
import java.util.Map;

public class Tree {
	public static int calcAge(PersonInfo pInfo) {
		Map<Integer, Double> dictOfSex = new HashMap<Integer, Double>();
		{
			dictOfSex.put(1, 1.0);
			dictOfSex.put(2, 1.1);
		}
		Map<Integer, Double> dictOfRegion = new HashMap<Integer, Double>();
		{
			dictOfRegion.put(1, 0.85);
			dictOfRegion.put(2, 0.95);
			dictOfRegion.put(3, 1.1);
			dictOfRegion.put(4, 0.8);
			dictOfRegion.put(5, 0.92);
			dictOfRegion.put(6, 0.95);
		}
		Map<Integer, Double> dictOfSmoking = new HashMap<Integer, Double>();
		{
			dictOfSmoking.put(1, 0.87);
			dictOfSmoking.put(2, 0.92);
			dictOfSmoking.put(3, 0.95);
			dictOfSmoking.put(4, 1.0);
		}
		Map<Integer, Double> dictOfAlcohol = new HashMap<Integer, Double>();
		{
			dictOfAlcohol.put(1, 0.71);
			dictOfAlcohol.put(2, 0.85);
			dictOfAlcohol.put(3, 0.92);
			dictOfAlcohol.put(4, 1.0);
		}
		Map<Integer, Double> dictOfSport = new HashMap<Integer, Double>();
		{
			dictOfSport.put(1, 1.15);
			dictOfSport.put(2, 1.07);
			dictOfSport.put(3, 1.0);
		}

		var normalWeight = pInfo.weight - pInfo.height + 115;
		var resultIndexWeight = 1.0;
		if (5 < normalWeight && normalWeight <= 10)
			resultIndexWeight = 0.98;
		else if (10 <= normalWeight && normalWeight < 20)
			resultIndexWeight = 0.94;
		else if (20 <= normalWeight && normalWeight < 40)
			resultIndexWeight = 0.9;
		else if (40 <= normalWeight)
			resultIndexWeight = 0.85;
		else if (-10 < normalWeight && normalWeight <= -5)
			resultIndexWeight = 0.98;
		else if (-20 < normalWeight && normalWeight <= -10)
			resultIndexWeight = 0.94;
		else if (-40 < normalWeight && normalWeight <= -20)
			resultIndexWeight = 0.9;
		else if (normalWeight <= -40)
			resultIndexWeight = 0.85;
		return (int)(85 * dictOfSex.get(pInfo.sex) * dictOfRegion.get(pInfo.region)
				* dictOfSmoking.get(pInfo.smokingRange) * dictOfAlcohol.get(pInfo.alcoholRange)
				* dictOfSport.get(pInfo.sportRange) * resultIndexWeight);
	}
}