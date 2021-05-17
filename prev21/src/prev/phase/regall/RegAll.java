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
	public int numreg;
	Vector<Vector<Integer>> graf = new Vector<Vector<Integer>>();
	int[] deg = new int[2002];
	int[] col = new int[2002];
	int[] vis = new int[2002];
	int numOfTem = 0;
	int br = 0;
	// Vector<Integer> deg = new Vector<Integer>();
	PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
	Stack<Integer> ss = new Stack<Integer>();
	// Vector<Integer> colour = new Vector<Integer>();
	HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();
	HashSet<Integer> all = new HashSet<Integer>();
	HashMap<MemTemp, Integer> t2i = new HashMap<MemTemp, Integer>();
	HashMap<Integer, MemTemp> i2t = new HashMap<Integer, MemTemp>();
	// Vector<Integer> vis = new Vector<Integer>();
	HashSet<Integer> spill = new HashSet<Integer>();

	public RegAll() {
		super("regall");
	}

	public void allocate() {
		// System.out.println(numreg);

		for (Code code : AsmGen.codes) {
			boolean zavrseno = false;
			while (!zavrseno) {
				// System.out.println("VLEZE");
				graf.clear();
				ss.clear();
				pq.clear();
				all.clear();
				hm.clear();
				spill.clear();
				t2i.clear();
				i2t.clear();
				br = 0;
				numOfTem = 0;
				LiveAn livean = new LiveAn();
				livean.PartAnalysis(code);

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

				// System.out.println("VLEZE");
				// System.out.println(t2i.get(code.frame.FP));

				for (int i = 0; i < numOfTem; i++) {
					deg[i] = 0;
					col[i] = -1;
					vis[i] = 0;
					graf.add(new Vector<Integer>());
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
					deg[i] = graf.get(i).size();
					pq.add(new Pair(deg[i], i));
				}
				boolean cc = false;
				while (!cc) {
					while (!pq.isEmpty()) {
						Pair p = pq.poll();
						if (vis[p.second] == 1)
							continue;
						// if (p.first != deg.get(p.second) )
						// continue;
						if (p.first >= numreg) {
							pq.add(p);
							break;
						}
						int x = p.second;
						for (Integer y : graf.get(x)) {
							if (vis[y] == 0) {
								deg[x]--;
								deg[y]--;
								pq.add(new Pair(deg[y], y));
							}
						}
						ss.add(x);
						vis[x] = 1;
					}
					if (pq.isEmpty())
						cc = true;
					if (cc)
						break;
					int x = pq.peek().second;
					for (Integer y : graf.get(x)) {
						if (vis[y] == 0) {
							deg[x]--;
							deg[y]--;
							pq.add(new Pair(deg[y], y));
						}
					}
					ss.add(x);
					spill.add(x);
					vis[x] = 1;
				}
				int idx = t2i.get(code.frame.FP);
				// System.out.println(idx);
				col[idx] = 254;
				while (!ss.empty()) {
					int top = ss.pop();
					if (col[top] == 254)
						continue;
					int[] vis1 = new int[2002];
					for (int i = 0; i < numreg; i++) {
						vis1[i] = 0;
					}

					for (Integer x : graf.get(top)) {
						// System.out.print(x + " ");
						if (col[x] >= 0 && col[x] != 254) {
							vis1[col[x]] = 1;
						}
					}
					// System.out.println();
					int colo = -10;
					for (int i = 0; i < numreg; i++) {
						if (vis1[i] == 0) {
							colo = i;
							break;
						}
					}
					// System.out.println("BROJ + " + top + " BOJA = " + colo);
					col[top] = colo;
				}

				boolean konc = true;
				for (int i = 0; i < numOfTem; i++) {
					if (col[i] == -1 || col[i] == -10) {
						konc = false;
						break;
					}
				}
				if (konc) {

					for (int i = 0; i < numOfTem; i++) {
						tempToReg.put(i2t.get(i), col[i]);
					}
					break;
				} else {
					System.out.println("CE SPILLLA");
					for (int i = 0; i < numOfTem; i++) {
						if (col[i] == -10) {
							System.out.println("ZA SPILL: " + i + "temp = " + i2t.get(i));
							br += 8;
							MemTemp t = i2t.get(i);
							Vector<AsmInstr> newInstr = new Vector<AsmInstr>();
							for (int ii = 0; ii < code.instrs.size(); ii++) {
								if (code.instrs.get(ii) instanceof AsmLABEL) {
									newInstr.add(code.instrs.get(ii));
									continue;
								}
								AsmOPER ins = (AsmOPER) code.instrs.get(ii);
								Vector<MemTemp> u = ins.uses();
								Vector<MemTemp> d = ins.defs();
								Vector<MemLabel> jmp = ins.jumps();

								for (int j = 0; j < ins.uses().size(); j++) {
									if (ins.uses().get(j) == t) {
										MemTemp t1 = new MemTemp();
										Vector<MemTemp> u1 = new Vector<MemTemp>();
										Vector<MemTemp> d1 = new Vector<MemTemp>();
										u1.add(t1);
										d1.add(t1);
										newInstr.add(new AsmOPER(
												"ADD `d0,$254," + Long.toString(br + code.frame.locsSize + 2 * 8), null,
												d1, null));
										newInstr.add(new AsmOPER("LDO `d0,`s0,0", u1, d1, null));
										u.set(j, t1);
										break;
									}
								}
								// TUKA ZASTANAV
								boolean store = false;
								for (int j = 0; j < ins.defs().size(); j++) {
									MemTemp t2 = new MemTemp();
									if (ins.defs().get(j) == t) {
										d.set(j, t2);
										store = true;
									}
								}
								newInstr.add(new AsmOPER(ins.instr(), u, d, jmp));

								if (store) {
									MemTemp t1 = d.get(0);
									MemTemp t2 = new MemTemp();
									Vector<MemTemp> u1 = new Vector<MemTemp>();
									Vector<MemTemp> d1 = new Vector<MemTemp>();
									u1.add(t1);
									u1.add(t2);
									d1.add(t2);
									newInstr.add(new AsmOPER(
											"ADD `d0,$254," + Long.toString(br + code.frame.locsSize + 2 * 8), null, d1,
											null));
									newInstr.add(new AsmOPER("STO `s0,`s1,0", u1, null, null));
								}
							}
							code.instrs.clear();
							for (AsmInstr instr : newInstr) {
								code.instrs.add(instr);
							}
							// Replace(code, i2t.get(i));
						}

					}
					Code newCode = new Code(code.frame, code.entryLabel, code.exitLabel, code.instrs);
					code = newCode;
				}
			}

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
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
