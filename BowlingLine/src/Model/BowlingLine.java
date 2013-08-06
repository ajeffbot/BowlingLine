package Model;

// Arlo Davidson, Ryan Compton

public class BowlingLine implements IBowlingLine {

	private int cScore;
	private int frameC;
	private Frame[] frames;

	public BowlingLine() {
		frames = new Frame[10];
		for (int i = 0; i < 10; i++) {
			frames[i] = new Frame();
		}
	}

	@Override
	public int scoreSoFar() {
		cScore = 0;
		for (int i = 0; i <= frameC; i++) {
			cScore += frames[i].frameScore;
		}
		return cScore;
	}

	@Override
	public int scoreAtFrame(int frame) {
		int c = 0;
		for (int i = 0; i < frame; i++) {
			c += frames[i].frameScore;
		}
		return c;
	}

	@Override
	public void pinsDowned(int pins) {
		if (frameC == 9) {
			if (frames[frameC - 2].isStrike) {
				frames[frameC - 2].frameScore += pins;
				frames[frameC - 2].isStrike = false;
			}
			if (frames[frameC - 1].isSpare || frames[frameC - 1].isStrike) {
				if (frames[frameC].rollCount == 1
						&& frames[frameC - 1].isStrike) {
					frames[frameC - 1].frameScore += pins;
					frames[frameC - 1].isStrike = false;
				} else {
					frames[frameC - 1].frameScore += pins;
					frames[frameC - 1].isSpare = false;
				}
			}
			if (frames[frameC].isSpare) {
				frames[frameC].rollThree = pins;
				frames[frameC].frameScore += pins;
				frames[frameC].rollCount++;
			} else if (frames[frameC].isStrike && frames[frameC].rollCount == 2) {
				frames[frameC].rollThree = pins;
				frames[frameC].frameScore += pins;
				frames[frameC].rollCount++;
			} else if (frames[frameC].isStrike && frames[frameC].rollCount == 1) {
				frames[frameC].rollTwo = pins;
				frames[frameC].frameScore += pins;
				frames[frameC].rollCount++;
			} else {
				frames[frameC].updateRoll(pins);
			}

		} else {
			frames[frameC].updateRoll(pins);
			if (frameC == 1) {
				if (frames[frameC - 1].isSpare || frames[frameC - 1].isStrike) {
					if (frames[frameC].rollTwo != 0
							&& frames[frameC - 1].isStrike) {
						frames[frameC - 1].frameScore += pins;
						frames[frameC - 1].isStrike = false;
					} else {
						frames[frameC - 1].frameScore += pins;
						frames[frameC - 1].isSpare = false;
					}
				}

			} else if (frameC > 1 && frameC < 9) {
				if (frames[frameC - 2].isStrike) {
					frames[frameC - 2].frameScore += pins;
					frames[frameC - 2].isStrike = false;
				}
				if (frames[frameC - 1].isSpare || frames[frameC - 1].isStrike) {
					if (frames[frameC].rollTwo != 0
							&& frames[frameC - 1].isStrike) {
						frames[frameC - 1].frameScore += pins;
						frames[frameC - 1].isStrike = false;
					} else {
						frames[frameC - 1].frameScore += pins;
						frames[frameC - 1].isSpare = false;
					}
				}
			}

			if (this.pinsLeftToDown() == 0) {
				frameC++;
			} else if (frames[frameC].rollCount == 2) {
				frameC++;
			}
		}

	}

	@Override
	public boolean canShowScoreFrame(int frame) {
		if (frames[frame - 1].isSpare || frames[frame - 1].isStrike)
			return false;
		else if (frames[frame - 1].rollCount < 2)
			return false;
		else
			return true;
	}

	@Override
	public String getRollsForFrame(int frame) {
		String r1 = Integer.toString(frames[frame - 1].rollOne);
		String r2 = Integer.toString(frames[frame - 1].rollTwo);
		String r3 = Integer.toString(frames[frame - 1].rollThree);
		if (frame == 10) {
			if (frames[frame - 1].rollOne == 10) {
				if (frames[frame - 1].rollTwo == 10) {
					if (frames[frame - 1].rollThree == 10) {
						return "XXX";
					} else {
						return "XX" + r3;
					}
				} else if (frames[frame - 1].rollTwo
						+ frames[frame - 1].rollThree == 10) {
					return "X" + r2 + "/";
				} else {
					return "X" + r2 + r3;
				}

			} else if (frames[frame - 1].isSpare) {
				if (frames[frame - 1].rollThree == 10) {
					return r1 + "/X";
				} else {
					return r1 + "/" + r3;
				}
			} else if (frames[frame - 1].rollCount == 1) {
				return r1 + "  ";
			} else {
				return r1 + " " + r2;
			}
		} else {
			if (frames[frame - 1].rollOne == 10) {
				return "  X";
			} else if (frames[frame - 1].rollOne + frames[frame - 1].rollTwo == 10) {
				return r1 + " /";
			} else if (frames[frame - 1].rollCount == 1) {
				return r1 + "  ";
			} else {
				return r1 + " " + r2;
			}
		}
	}

	@Override
	public int pinsLeftToDown() {
		if (frameC == 9 && frames[9].rollCount == 3) {
			return 0;
		} else if (frameC == 9 && frames[9].rollCount == 2) {
			if (frames[9].rollOne + frames[9].rollTwo < 10) {
				return 0;
			} else if (frames[9].rollTwo != 10) {
				return 10 - frames[9].rollTwo;
			} else {
				return 10;
			}
		} else if (frameC == 9 && frames[9].rollCount == 1
				&& frames[9].isStrike) {
			return 10;
		} else {
			return 10 - frames[frameC].rollOne - frames[frameC].rollTwo;
		}
	}

	public String toString() {
		String result = "";
		result = "   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 |\n";
		for (int a = 1; a <= 10; a++) {
			if (frames[a - 1].rollCount == 0) {
				result += "     |";
			} else
				result += " " + this.getRollsForFrame(a) + " |";
		}
		result += "     TOTAL\n";
		for (int b = 1; b <= 10; b++) {
			if (frames[b - 1].rollCount == 0) {
				result += "     |";
			} else if (this.scoreAtFrame(b) < 10) {
				result += "   " + this.scoreAtFrame(b) + " |";
			} else if (this.scoreAtFrame(b) < 100) {
				result += "  " + this.scoreAtFrame(b) + " |";
			} else {
				result += " " + this.scoreAtFrame(b) + " |";
			}
		}

		result += "        " + this.scoreSoFar();
		return result;
	}

	private class Frame {
		private int rollOne;
		private int rollTwo;
		private int rollThree;
		private int frameScore;
		private boolean isSpare;
		private boolean isStrike;
		private int rollCount;

		public void updateRoll(int roll) {
			if (rollCount != 0) {
				rollTwo = roll;
				rollCount++;
				if (rollOne + rollTwo == 10) {
					isSpare = true;
					frameScore = rollOne + rollTwo;
				} else {
					frameScore = rollOne + rollTwo;
				}
			} else {
				rollOne = roll;
				rollCount++;
				if (roll == 10) {
					isStrike = true;
					frameScore = roll;
				}
				frameScore = roll;
			}
		}

	}
}
