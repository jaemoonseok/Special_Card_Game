
public class Winner {
	public static boolean winner(String[] dealer, String[] player) {
		int specialD = 0;
		int specialP = 0;
		int d = 0;
		int p = 0;
		for (int i = 0; i < dealer.length; i++) {
			int intD = Integer.parseInt(dealer[i].substring(6));
			int intP = Integer.parseInt(player[i].substring(6));
			if (intD > 10) {
				specialD++;
			} else {
				d += intD;
			}

			if (intP > 10) {
				specialP++;
			} else {
				p += intP;
			}
		}

		if (specialD < specialP) {
			return true;
		} else if (specialD > specialP) {
			return false;
		} else {
			if (d % 10 >= p % 10) {
				return false;
			} else {
				return true;
			}

		}
	}
}
