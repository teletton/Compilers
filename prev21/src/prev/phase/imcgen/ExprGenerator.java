package prev.phase.imcgen;

import java.util.*;

import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.typ.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.phase.seman.*;
import prev.phase.memory.*;

public class ExprGenerator implements AstVisitor<ImcExpr, Stack<MemFrame>> {
	public int baraj = 0; 
	public boolean treba = false;
	Vector<ImcExpr> vie;
	Vector<Long> offs;
	Vector<ImcStmt> viss;
	@Override
	public ImcExpr visit(AstTrees<? extends AstTree> trees, Stack<MemFrame> arg) {
        //System.out.println("EXPRtree=");
		if (baraj == 2) viss = new Vector<ImcStmt>();
		for (AstTree t : trees) {
        	//System.out.println("EXPRtree = " + t.getClass());
			if (t != null) {
				if (baraj == 1) {
					ImcExpr iee = t.accept(this, arg);
					if (!(t instanceof AstDecl)) {
					//System.out.println("ARGS = " + iee);
					vie.add(iee);
					}
				} else if (baraj == 2){ 
					viss.add(t.accept(new StmtGenerator(), arg));
				} else {
					t.accept(this, arg);
				}
			}
        }
		return null;
	}

	@Override
	public ImcExpr visit(AstCompDecl compDecl, Stack<MemFrame> arg) {
        //System.out.println("EXPRcompdecl");
		return null;
	}

	@Override
	public ImcExpr visit(AstFunDecl funDecl, Stack<MemFrame> arg) {
        //System.out.println("EXPRfundecl");
		if (funDecl.expr != null) {
			MemFrame mf = Memory.frames.get(funDecl);
			arg.add(mf);
			funDecl.expr.accept(this, arg);
			arg.pop();
		}
		return null;
	}

	@Override
	public ImcExpr visit(AstParDecl parDecl, Stack<MemFrame> arg) {
        //System.out.println("EXPRpardecl");
		return null;
	}

	@Override
	public ImcExpr visit(AstTypeDecl typeDecl, Stack<MemFrame> arg) {
        //System.out.println("EXPRtypedecl");
		return null;
	}

	@Override
	public ImcExpr visit(AstVarDecl varDecl, Stack<MemFrame> arg) {
        //System.out.println("EXPRvardecl" + " id = " + varDecl.id);
		return null;
	}

	// EXPRESSIONS

	@Override
	public ImcExpr visit(AstArrExpr arrExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRarrexpr");
		treba = true;
		ImcExpr ie1 = arrExpr.arr.accept(this, arg);
		treba = false;
		ImcExpr ie2 = arrExpr.idx.accept(this, arg);
		SemType st = SemAn.ofType.get(arrExpr);

		ImcBINOP ib1 = new ImcBINOP(ImcBINOP.Oper.MUL, ie2, new ImcCONST(st.size()));
		ImcBINOP ib = new ImcBINOP(ImcBINOP.Oper.ADD, ie1, ib1);
		ImcMEM im = new ImcMEM(ib);
		
		ImcGen.exprImc.put(arrExpr, im);
		return im;
	}

	@Override
	public ImcExpr visit(AstAtomExpr atomExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRatomexpr + id = "+atomExpr.id);
        SemType tip = SemAn.ofType.get(atomExpr);
        if (tip instanceof SemInt) {
			//System.out.println("VLEZE");
            int val = Integer.valueOf(atomExpr.value);
            ImcCONST iconst = new ImcCONST(val);
            ImcGen.exprImc.put(atomExpr, iconst);
            return iconst;
        } else if (tip instanceof SemChar) {
            int val = (int)atomExpr.value.charAt(1);
            ImcCONST iconst = new ImcCONST(val);
            ImcGen.exprImc.put(atomExpr, iconst);
            return iconst;
        } else if (tip instanceof SemBool) {
            int val = 0;
            if (atomExpr.value.equals("true")) 
                val = 1;
            ImcCONST iconst = new ImcCONST(val);
            ImcGen.exprImc.put(atomExpr, iconst);
            return iconst;
        } else if(atomExpr.value.equals("nil")) {
            int val = 0;
            ImcCONST iconst = new ImcCONST(val);
            ImcGen.exprImc.put(atomExpr, iconst);
            return iconst;
        } else if (tip instanceof SemPtr) {
			if (((SemPtr)tip).baseType instanceof SemChar) {
				ImcNAME in = new ImcNAME(Memory.strings.get(atomExpr).label);
				ImcGen.exprImc.put(atomExpr, in);
				return in;
			}
		} else if (tip instanceof SemVoid) {
			ImcCONST icc = new ImcCONST(0);
			ImcGen.exprImc.put(atomExpr, icc);
			return icc;

		}
		return null;
	}

	@Override
	public ImcExpr visit(AstBinExpr binExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRbinexpr id = " + binExpr.id);
		
		ImcExpr ief = binExpr.fstExpr.accept(this, arg);
		ImcExpr ies = binExpr.sndExpr.accept(this, arg);
		//System.out.println(ief);
		//System.out.println(ies);
		switch (binExpr.oper) {
			case OR:
				ImcBINOP ib = new ImcBINOP(ImcBINOP.Oper.OR, ief, ies);
				ImcGen.exprImc.put(binExpr, ib);
				return ib;
			case AND:
				ImcBINOP ib2 = new ImcBINOP(ImcBINOP.Oper.AND, ief, ies);
				ImcGen.exprImc.put(binExpr, ib2);
				return ib2;
			case EQU:
				ImcBINOP ib3 = new ImcBINOP(ImcBINOP.Oper.EQU, ief, ies);
				ImcGen.exprImc.put(binExpr, ib3);
				return ib3;
			case NEQ:
				ImcBINOP ib4 = new ImcBINOP(ImcBINOP.Oper.NEQ, ief, ies);
				ImcGen.exprImc.put(binExpr, ib4);
				return ib4;
			case GEQ:
				ImcBINOP ib5 = new ImcBINOP(ImcBINOP.Oper.GEQ, ief, ies);
				ImcGen.exprImc.put(binExpr, ib5);
				return ib5;
			case LEQ:
				ImcBINOP ib6 = new ImcBINOP(ImcBINOP.Oper.LEQ, ief, ies);
				ImcGen.exprImc.put(binExpr, ib6);
				return ib6;
			case GTH:
				ImcBINOP ib7 = new ImcBINOP(ImcBINOP.Oper.GTH, ief, ies);
				ImcGen.exprImc.put(binExpr, ib7);
				return ib7;
			case LTH:
				ImcBINOP ib8 = new ImcBINOP(ImcBINOP.Oper.LTH, ief, ies);
				ImcGen.exprImc.put(binExpr, ib8);
				return ib8;
			case ADD:
				ImcBINOP ib9 = new ImcBINOP(ImcBINOP.Oper.ADD, ief, ies);
				ImcGen.exprImc.put(binExpr, ib9);
				return ib9;
			case SUB:
				ImcBINOP ib10 = new ImcBINOP(ImcBINOP.Oper.SUB, ief, ies);
				ImcGen.exprImc.put(binExpr, ib10);
				return ib10;
			case MUL:
				ImcBINOP ib11 = new ImcBINOP(ImcBINOP.Oper.MUL, ief, ies);
				ImcGen.exprImc.put(binExpr, ib11);
				return ib11;
			case DIV:
				ImcBINOP ib12 = new ImcBINOP(ImcBINOP.Oper.DIV, ief, ies);
				ImcGen.exprImc.put(binExpr, ib12);
				return ib12;
			case MOD:	
				ImcBINOP ib13 = new ImcBINOP(ImcBINOP.Oper.MOD, ief, ies);
				ImcGen.exprImc.put(binExpr, ib13);
				return ib13;
			default:
				break;
		}
		return null;
	}

	@Override
	public ImcExpr visit(AstCallExpr callExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRcallexpr");
		if (callExpr.args != null) {

			long stevec = 8;
			
			offs = new Vector<Long>();
			vie = new Vector<ImcExpr>();
			MemFrame mf = Memory.frames.get((AstFunDecl)SemAn.declaredAt.get(callExpr));
			//System.out.println(mf.label);
			//System.out.println(mf.depth);
			if (mf.depth == 1) {
				offs.add((long)0);
				vie.add(new ImcCONST(0));
			} else {
				
				offs.add((long)0);
				int rez = mf.depth;
				//System.out.println(rez);
				//System.out.println(callExpr.name);
				//System.out.println(arg.peek().label.name);
				if (rez > 1 && mf.label.name.equals(arg.peek().label.name))  {
					vie.add(new ImcMEM(new ImcTEMP(arg.peek().FP)));
				}
				else { 
					vie.add(new ImcTEMP(arg.peek().FP));
				}
			}
			baraj = 1;
			callExpr.args.accept(this, arg);
			baraj = 0;
			for (AstExpr ae : callExpr.args) {
				offs.add(stevec);
				stevec += (SemAn.ofType.get(ae)).size();
			}
			
			ImcCALL ic = new ImcCALL(mf.label, offs, vie);
			ImcGen.exprImc.put(callExpr, ic);
			return ic;
		}
		return null;
	}

	@Override
	public ImcExpr visit(AstCastExpr castExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRcastexpr");
		SemType st = SemAn.ofType.get(castExpr);
		ImcExpr ie = castExpr.expr.accept(this, arg);
		//System.out.println("VLEZEcastexpr");
		//System.out.println(st);
		//System.out.println(ie); 
		if (st instanceof SemChar) {
			ImcBINOP ib = new ImcBINOP(ImcBINOP.Oper.MOD, ie, new ImcCONST(256));
			ImcGen.exprImc.put(castExpr, ib);
			return ib;
		} else {
			ImcGen.exprImc.put(castExpr, ie);
			return ie;
		}
	}

	@Override
	public ImcExpr visit(AstNameExpr nameExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRnameexpr"+ " id = " + nameExpr.id);
		AstDecl ad = SemAn.declaredAt.get(nameExpr);
		//System.out.println(nameExpr.name);
		//System.out.println(SemAn.ofType.get(nameExpr));
		if (ad instanceof AstVarDecl) {
			MemAccess ma = Memory.accesses.get((AstVarDecl)ad);
			if (ma instanceof MemAbsAccess) {
				ImcNAME in = new ImcNAME(((MemAbsAccess)ma).label);
				ImcMEM im = new ImcMEM(in);
				ImcGen.exprImc.put(nameExpr, im);
				return im;
			} else if (ma instanceof MemRelAccess) {
				MemFrame mf = arg.peek();
				int raz = mf.depth - ((MemRelAccess)ma).depth;
				
				ImcTEMP it = new ImcTEMP(mf.FP);
				ImcMEM im1;
				if (raz > 0) {
					im1 = new ImcMEM(it);
					for (int i = 1; i < raz; i++) {
						im1 = new ImcMEM(im1);
					}
					long c = ((MemRelAccess)ma).offset;
					ImcBINOP ib;
					if (c < 0) {
						ib = new ImcBINOP(ImcBINOP.Oper.SUB, im1, new ImcCONST(-1*c));
					} else {
						ib = new ImcBINOP(ImcBINOP.Oper.ADD, im1, new ImcCONST(c));
					}
					//System.out.println(nameExpr.name + " " + nameExpr.id);
					if (SemAn.ofType.get(nameExpr) instanceof SemArr ) {
						//System.out.println("BLEZE");
						
						ImcGen.exprImc.put(nameExpr, ib);
						return ib;
					}
					ImcMEM im = new ImcMEM(ib);
					ImcGen.exprImc.put(nameExpr, im);
					return im;
				}
				
				long c = ((MemRelAccess)ma).offset;
				ImcBINOP ib;
				if (c < 0) {
					ib = new ImcBINOP(ImcBINOP.Oper.SUB, it, new ImcCONST(-1*c));
				} else {
					ib = new ImcBINOP(ImcBINOP.Oper.ADD, it, new ImcCONST(c));
				}
				//System.out.println(nameExpr.name + " " + nameExpr.id);
				if (SemAn.ofType.get(nameExpr) instanceof SemArr ) {
					//System.out.println("BLEZE");
					
					ImcGen.exprImc.put(nameExpr, ib);
					return ib;
				}
				ImcMEM im = new ImcMEM(ib);
				ImcGen.exprImc.put(nameExpr, im);
				return im;
			}
		} else if (ad instanceof AstParDecl) {
			MemAccess ma = Memory.accesses.get((AstParDecl)ad);
			if (ma instanceof MemAbsAccess) {
				ImcNAME in = new ImcNAME(((MemAbsAccess)ma).label);
				ImcMEM im = new ImcMEM(in);
				ImcGen.exprImc.put(nameExpr, im);
				return im;
			} else if (ma instanceof MemRelAccess) {
				MemFrame mf = arg.peek();
				int raz = mf.depth - ((MemRelAccess)ma).depth;
				
				ImcTEMP it = new ImcTEMP(mf.FP);
				ImcMEM im1;
				if (raz > 0) {
					im1 = new ImcMEM(it);
					for (int i = 1; i < raz; i++) {
						im1 = new ImcMEM(im1);
					}
					long c = ((MemRelAccess)ma).offset;
					ImcBINOP ib;
					if (c < 0) {
						ib = new ImcBINOP(ImcBINOP.Oper.SUB, im1, new ImcCONST(-1*c));
					} else {
						ib = new ImcBINOP(ImcBINOP.Oper.ADD, im1, new ImcCONST(c));
					}
					//System.out.println(nameExpr.name + " " + nameExpr.id);
					if (SemAn.ofType.get(nameExpr) instanceof SemArr ) {
						//System.out.println("BLEZE");
						
						ImcGen.exprImc.put(nameExpr, ib);
						return ib;
					}
					ImcMEM im = new ImcMEM(ib);
					ImcGen.exprImc.put(nameExpr, im);
					return im;
				}
				
				long c = ((MemRelAccess)ma).offset;
				ImcBINOP ib;
				if (c < 0) {
					ib = new ImcBINOP(ImcBINOP.Oper.SUB, it, new ImcCONST(-1*c));
				} else {
					ib = new ImcBINOP(ImcBINOP.Oper.ADD, it, new ImcCONST(c));
				}
				//System.out.println(nameExpr.name + " " + nameExpr.id);
				if (SemAn.ofType.get(nameExpr) instanceof SemArr ) {
					//System.out.println("BLEZE");
					
					ImcGen.exprImc.put(nameExpr, ib);
					return ib;
				}
				ImcMEM im = new ImcMEM(ib);
				ImcGen.exprImc.put(nameExpr, im);
				return im;
			}
			
		}
		return null;
	}

	@Override
	public ImcExpr visit(AstPfxExpr pfxExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRpfxxepr");
		if (pfxExpr.expr != null) {
			ImcExpr ie = pfxExpr.expr.accept(this, arg);
			switch (pfxExpr.oper) {
				case PTR: 
					if (treba == true) {
					ImcMEM im = new ImcMEM(ie); 
					ImcGen.exprImc.put(pfxExpr, im);
					return im;
					}
					ImcGen.exprImc.put(pfxExpr, ie);
					return ie;
				case ADD: 
					ImcExpr ie7 = pfxExpr.expr.accept(this, arg); 
					ImcUNOP iu = new ImcUNOP(ImcUNOP.Oper.ADD, ie7);
					ImcGen.exprImc.put(pfxExpr, iu);
					return iu;
				case SUB:
					ImcExpr ie2 = pfxExpr.expr.accept(this, arg);
					ImcUNOP iu1 = new ImcUNOP(ImcUNOP.Oper.NEG, ie2);
					ImcGen.exprImc.put(pfxExpr, iu1);
					return iu1;
				case NOT:
					ImcExpr ie3 = pfxExpr.expr.accept(this, arg);
					ImcUNOP iu2 = new ImcUNOP(ImcUNOP.Oper.NOT, ie3);
					ImcGen.exprImc.put(pfxExpr, iu2);
					return iu2;
				case DEL:  
					ImcExpr ie4 = pfxExpr.expr.accept(this, arg);
					Vector<ImcExpr> argss = new Vector<ImcExpr>();
					Vector<Long> offsss = new Vector<Long>();
					offsss.add((long)0);
					offsss.add((long)8);
					argss.add(new ImcCONST(0));
					argss.add(ie4);
					ImcCALL ic = new ImcCALL(new MemLabel("del"), offsss, argss);
					ImcGen.exprImc.put(pfxExpr, ic);
					return ic;
				case NEW:  
				ImcExpr ie5 = pfxExpr.expr.accept(this, arg);
					Vector<ImcExpr> argss1 = new Vector<ImcExpr>();
					Vector<Long> offsss1 = new Vector<Long>();
					offsss1.add((long)0);
					offsss1.add((long)8);
					argss1.add(new ImcCONST(0));
					argss1.add(ie5);
					ImcCALL ic1 = new ImcCALL(new MemLabel("new"), offsss1, argss1);
					ImcGen.exprImc.put(pfxExpr, ic1);
					return ic1;
				default:
				
					return ie;
			}
		}
		return null;
	}

	@Override
	public ImcExpr visit(AstRecExpr recExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRrecexpr");
		ImcExpr ie = recExpr.rec.accept(this, arg);
		recExpr.comp.accept(this, arg);
		AstDecl ad = SemAn.declaredAt.get(((AstNameExpr)recExpr.rec));
		AstType at = ((AstVarDecl)ad).type;
		long brojac = 0;
		for (AstCompDecl at1 : ((AstRecType)at).comps) {
			if (at1.name.equals(recExpr.comp.name)) break;
			//System.out.println(SemAn.ofType.get(at1));
			//System.out.println(at1);
			brojac += SemAn.isType.get(at1.type).size();
		}
		ImcBINOP ib = new ImcBINOP(ImcBINOP.Oper.ADD, ie, new ImcCONST(brojac));
		ImcMEM im = new ImcMEM(ib);
		ImcGen.exprImc.put(recExpr, im);
		return im;
	}

	@Override
	public ImcExpr visit(AstSfxExpr sfxExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRsfxexpr");
			ImcExpr ie = sfxExpr.expr.accept(this, arg);
			if (treba == true) {
				ImcGen.exprImc.put(sfxExpr, ie);
			return ie;
			}
			ImcMEM im = new ImcMEM(ie);
			ImcGen.exprImc.put(sfxExpr, im);
			return im;
		
	}

	@Override
	public ImcExpr visit(AstStmtExpr stmtExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRstmtexpr");
		if (stmtExpr.stmts != null) {
			//System.out.println("AJDE");
			baraj = 2;
			ImcExpr ie = stmtExpr.stmts.accept(this, arg);
			ImcExpr ie1 = new ImcCONST(0);
			ImcStmt ist = viss.get(viss.size() - 1);
				if (ist instanceof ImcESTMT && !(((ImcESTMT)ist).expr instanceof ImcCALL)) {
					ie1 = ((ImcESTMT)ist).expr;
				}
			
			baraj = 0;
			ImcSTMTS is = new ImcSTMTS(viss);
			ImcSEXPR ise = new ImcSEXPR(is, ie1);
			ImcGen.exprImc.put(stmtExpr, ise);
			return ise;
		}
		return null;
	}

	@Override
	public ImcExpr visit(AstWhereExpr whereExpr, Stack<MemFrame> arg) {
        //System.out.println("EXPRwhereexpr");
		if (whereExpr.decls != null)
			whereExpr.decls.accept(this, arg);
		if (whereExpr.expr != null) {
			ImcExpr ie = whereExpr.expr.accept(this, arg);
			ImcGen.exprImc.put(whereExpr, ie);
			return ie;
		}
		
		return null;
	}
}
