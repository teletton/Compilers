package prev.phase.regall;

import java.util.*;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;
import prev.Compiler;
import prev.phase.livean.*;

/**
 * Register allocation.
 */
class Pair implements Comparable<Pair> {
	int first;
	int second;

	public Pair(int f, int s) {
		first = f;
		second = s;
	}

	@Override
	public int compareTo(Pair p) {
		if (first > p.first) {
			return 1;
		} else if (first < p.first) {
			return -1;
		} else if (second > p.second) {
			return 1;
		} else if (second < p.second) {
			return -1;
		} else {
			return 0;
		}

	}
}

public class RegAll extends Phase {

	/** Mapping of temporary variables to registers. */
	public final HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();
	public int numreg = 8;
	Vector<Vector<Integer>> graf = new Vector<Vector<Integer>>();
	Vector<Integer> deg = new Vector<Integer>();
	PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
	Stack<Integer> ss = new Stack<Integer>();
	Vector<Integer> colour = new Vector<Integer>();
	HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();
	HashSet<Integer> all = new HashSet<Integer>();
	HashMap<MemTemp, Integer> t2i = new HashMap<MemTemp, Integer>();
	HashMap<Integer, MemTemp> i2t = new HashMap<Integer, MemTemp>();
	Vector<Integer> vis = new Vector<Integer>();
	HashSet<Integer> spill = new HashSet<Integer>();
	int numOfTem = 0;

	public RegAll() {
		super("regall");
	}

	public void initialization(Code code) {
		graf.clear();
		deg.clear();
		ss.clear();
		pq.clear();
		colour.clear();
		all.clear();
		hm.clear();
		spill.clear();
		vis.clear();
		t2i.clear();
		i2t.clear();
		all.clear();
		LiveAn livean = new LiveAn();
		livean.analysis();

		MemTemp fp = code.frame.FP;
		if (!t2i.containsKey(fp)) {
			t2i.put(fp, numOfTem);
			i2t.put(numOfTem, fp);
			numOfTem++;
		}
		for (int i = 0; i < code.instrs.size(); i++) {
			for (MemTemp j : code.instrs.get(i).uses()) {
				if (t2i.containsKey(j))
					continue;
				else {
					t2i.put(j, numOfTem);
					i2t.put(numOfTem, j);
					numOfTem++;
				}
			}

			for (MemTemp j : code.instrs.get(i).defs()) {
				if (t2i.containsKey(j))
					continue;
				else {
					t2i.put(j, numOfTem);
					i2t.put(numOfTem, j);
					numOfTem++;
				}
			}

			for (MemTemp j : code.instrs.get(i).in()) {
				if (t2i.containsKey(j))
					continue;
				else {
					t2i.put(j, numOfTem);
					i2t.put(numOfTem, j);
					numOfTem++;
				}
			}

			for (MemTemp j : code.instrs.get(i).out()) {
				if (t2i.containsKey(j))
					continue;
				else {
					t2i.put(j, numOfTem);
					i2t.put(numOfTem, j);
					numOfTem++;
				}
			}
		}

		for (int i = 0; i < numOfTem; i++) {
			deg.add(0);
			colour.add(-1);
			graf.add(new Vector<Integer>());
			vis.add(0);
		}

		for (int i = 0; i < code.instrs.size(); i++) {
			for (MemTemp m1 : code.instrs.get(i).in()) {
				for (MemTemp m2 : code.instrs.get(i).in()) {
					int x = t2i.get(m1);
					int y = t2i.get(m2);
					if (x == y)
						continue;
					if (x > y) {
						int t = x;
						x = y;
						y = t;
					}
					int hash = x * numOfTem + y;
					if (hm.containsKey(hash))
						continue;
					else {
						hm.put(x * numOfTem + y, true);
						graf.get(x).add(y);
					}
					int hash1 = y * numOfTem + x;
					if (hm.containsKey(hash1))
						continue;
					else {
						// System.out.println(y + " " + x);
						hm.put(y * numOfTem + x, true);
						graf.get(y).add(x);
					}
				}
			}
		}

		for (int i = 0; i < code.instrs.size(); i++) {
			for (MemTemp m1 : code.instrs.get(i).out()) {
				for (MemTemp m2 : code.instrs.get(i).out()) {
					int x = t2i.get(m1);
					int y = t2i.get(m2);
					if (x == y)
						continue;
					if (x > y) {
						int t = x;
						x = y;
						y = t;
					}
					int hash = x * numOfTem + y;
					if (hm.containsKey(hash))
						continue;
					else {
						// System.out.println(x + " " + y);
						hm.put(x * numOfTem + y, true);
						graf.get(x).add(y);
					}

					int hash1 = y * numOfTem + x;
					if (hm.containsKey(hash1))
						continue;
					else {
						// System.out.println(y + " " + x);
						hm.put(y * numOfTem + x, true);
						graf.get(y).add(x);
					}
				}
			}
		}

		for (int i = 0; i < numOfTem; i++) {
			deg.set(i, graf.get(i).size());
		}

		for (int i = 0; i < numOfTem; i++) {
			pq.add(new Pair(deg.get(i), i));
		}
	}

	public void Colour() {
		while (!ss.empty()) {
			int top = ss.pop();
			Vector<Boolean> visCol = new Vector<Boolean>();
			for (int i = 0; i < numreg; i++) {
				visCol.add(false);
			}

			for (Integer x : graf.get(top)) {
				// System.out.print(x + " ");
				if (colour.get(x) >= 0) {
					// System.out.println(x + " IMA BOJA " + colour.get(x));
					visCol.set(colour.get(x), true);
				}
			}
			// System.out.println();
			int colo = -10;
			for (int i = 0; i < numreg; i++) {
				if (!visCol.get(i)) {
					colo = i;
					break;
				}
			}
			// System.out.println("BROJ + " + top + " BOJA = " + colo);
			colour.set(top, colo);
		}
	}

	public void allocateCode(Code code) {
		System.out.println("VLEZE");
		initialization(code);
		boolean isSimplyfied = false;
		while (!isSimplyfied) {
			while (!pq.isEmpty()) {
				Pair p = pq.poll();
				if (p.first != deg.get(p.second) || vis.get(p.second) == 1)
					continue;
				if (p.first >= numreg) {
					pq.add(p);
					break;
				}
				int x = p.second;
				for (Integer y : graf.get(x)) {
					if (vis.get(y) == 0) {
						deg.set(x, deg.get(x) - 1);
						deg.set(y, deg.get(y) - 1);
						pq.add(new Pair(deg.get(y), y));
					}
				}
				ss.add(x);
				vis.set(x, 1);
			}
			if (pq.isEmpty())
				isSimplyfied = true;
			if (isSimplyfied)
				break;
			int x = pq.peek().second;
			for (Integer y : graf.get(x)) {
				if (vis.get(y) == 0) {
					deg.set(x, deg.get(x) - 1);
					deg.set(y, deg.get(y) - 1);
					pq.add(new Pair(deg.get(y), y));
				}
			}
			ss.add(x);
			spill.add(x);
			vis.set(x, 1);
		}
		boolean ok = true;
		for (int i = 0; i < numOfTem; i++) {
			if (deg.get(i) != 0) {
				ok = false;
				// System.out.println("THERE IS SOME PROBLEM");
			}

			if (vis.get(i) == 0) {
				ok = false;
			}
		}

		if (!ok) {
			// System.out.println("THERE IS SOME PROBLEM");
		}

		Colour();

		boolean finished = true;
		for (int i = 0; i < numOfTem; i++) {
			if (colour.get(i) < 0) {
				finished = false;
			}
		}
		if (finished) {
			for (int i = 0; i < numOfTem; i++) {
				for (Integer x : graf.get(i)) {
					if (colour.get(i) == colour.get(x)) {
						System.out.println("SAME COLOURS");
					}
				}
			}
			colour.set(t2i.get(code.frame.FP), 254);
			for (int i = 0; i < numOfTem; i++) {
				tempToReg.put(i2t.get(i), colour.get(i));
			}
		}
	}

	public void allocate() {
		for (int i = 0; i < AsmGen.codes.size(); i++) {
			// System.out.println("VLEZE" + i);
			allocateCode(AsmGen.codes.get(i));
		}
	}

	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString(tempToReg));
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}