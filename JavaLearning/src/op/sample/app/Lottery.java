package op.sample.app;

import java.util.ArrayList;

public class Lottery {
	private ArrayList<String> result;
	private ArrayList<Integer> resultValue;
	private String[] numbers;
	private String tmp;
	private int count;
	private int size;
	private boolean[] used;
	
	public Lottery() {
		result = new ArrayList<String>();
		resultValue = new ArrayList<Integer>();
		tmp = "";
		count = 0;
		size = 0;
	}
	
	public void getCompose(String[] numbers, int cnt) {
		this.numbers = numbers;
		this.size = cnt;
		this.used = new boolean[numbers.length];
		for(int idx=0; idx<this.used.length; idx++)
			this.used[idx] = false;
		
		this.getNumString(tmp, 0);		
		for(int idx=0; idx<result.size(); idx++)
			System.out.println("Result :" + result.get(idx) );
		System.out.println("Total :" + result.size() );
	}
	
	private String getNumString(String str, int strValue) {
		if(count == size)
		{		
			if(!resultValue.contains(strValue))
			{
				resultValue.add(strValue);
				strValue = 0;
				result.add(str);
			}
			return str;
		}
		
		String tmpStr;
		int tmpValue;
		for(int idx=0; idx<numbers.length; idx++)
		{
			if(!used[idx])
			{
				tmpStr = str;
				tmpValue = strValue;
				
				tmpStr += numbers[idx];
				tmpStr += "　";
				
				tmpValue += Integer.parseInt(numbers[idx]);
				
				used[idx] = true;
				count++;
				getNumString(tmpStr, tmpValue);
				count--;
				this.used[idx] = false;
			}
		}
		return str;
	}
}
