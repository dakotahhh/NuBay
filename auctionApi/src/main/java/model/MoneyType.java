package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public enum MoneyType {
	USD {
		@Override
		public String format(float value) {
			NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
			return fmt.format(value);
		}
	},
	EUR {
		@Override
		public String format(float value) {
			NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.UK);
			return fmt.format(value);
		}
	};
	
//	private final int THOUSANDTHS_PLACE = 2;
	public abstract String format(float value);
	
//	private String round(float value){
//		String beforeRound = new Float(value).toString();
//		String[] numberTokens = beforeRound.split("[.]");
//		
//		for (int i = THOUSANDTHS_PLACE; i >= 0; i--){
//			if (Integer.parseInt(new Character(numberTokens[i].charAt(i)).toString()) >= 5){
//				if (i == 0){
//					int prevPlace = Integer.parseInt(new Character(numberTokens[i].charAt(i-1)).toString());
//					prevPlace++;
//					
//					numberTokens[i-1].repl				
//				}
//			}
//		}
//		
//		return null;
//	}
}
