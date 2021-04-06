package prev.phase.seman;

import java.util.*;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.typ.*;

/**
 * Type resolver.
 * 
 * Type resolver computes the values of {@link SemAn#declaresType},
 * {@link SemAn#isType}, and {@link SemAn#ofType}.
 */
public class TypeResolver extends AstFullVisitor<SemType, Object> {
    public static long vrednost = 0;
    Vector<SemType> vek;
    public static boolean vred = false;
    @Override
    public SemType visit(AstTrees<? extends AstTree> trees, Object arg) {
        //System.out.println("id = "+trees.id()+" class = "+trees.getClass()+" arg = "+arg);
        
        SemType nisto = null;

        for (AstTree t : trees) {
            //System.out.println("id = "+t.id()+" class = "+t.getClass()+" arg = "+arg);
            
            SemType tt = t.accept(this, (int)arg);
            SemType tt2 = SemAn.ofType.get(t);
            if ((int)arg == 33) {
                if (!(tt instanceof SemInt) && !(tt instanceof SemChar) && !(tt instanceof SemBool) && !(tt instanceof SemPtr) && !(tt instanceof SemName)) {
                    throw new Report.Error("SEMANTIC ERROR at: " + t.location() + " The parameters of the function must be of type (int, char, ptr or bool)");
                }
                if ((tt instanceof SemName) && !(((SemName)tt).type() instanceof SemChar)  && !(((SemName)tt).type() instanceof SemInt)  && !(((SemName)tt).type() instanceof SemBool) )
                    throw new Report.Error("SEMANTIC ERROR at: "+ t.location()+" The parameters of the function must be of type (int, char, ptr or bool)");
            } else if ((int)arg == 44) {
               // System.out.println(t.location() + "44PAPA = "+tt+" "+t.getClass());
                nisto = tt;
            }
            
        }
        
        return nisto;
    }

    // DECLARATIONS

    @Override
    public SemType visit(AstCompDecl compDecl, Object arg) {
        //System.out.println("compdeclid = "+compDecl.id()+" arg = "+arg);
        SemType st = compDecl.type.accept(this, arg);
        vek.add(st);
        return st;
        
    }

    @Override
    public SemType visit(AstFunDecl funDecl, Object arg) {
        //System.out.println("fundeclid = "+funDecl.id()+" arg = "+arg);

        if (funDecl.pars != null)
            funDecl.pars.accept(this, 33);
       
            SemType tt = funDecl.type.accept(this, arg);
            if (!(tt instanceof SemInt) && !(tt instanceof SemChar) && !(tt instanceof SemBool) && !(tt instanceof SemPtr) && !(tt instanceof SemVoid)) {
                throw new Report.Error("SEMANTIC ERROR at: "+ (funDecl.type).location()+" The return type of the function must be of type int, char, ptr, bool or void");
            }
            if ((tt instanceof SemName) && !(((SemName)tt).type() instanceof SemChar)  && !(((SemName)tt).type() instanceof SemInt)  && !(((SemName)tt).type() instanceof SemBool)  && !(((SemName)tt).type() instanceof SemVoid))
            throw new Report.Error("SEMANTIC ERROR at: "+ (funDecl.type).location()+" The return type of the function must be of type int, char, ptr, bool or void");
            SemAn.ofType.put(funDecl, tt);
            
        
        if (funDecl.expr != null) {

            SemType t1 = funDecl.expr.accept(this, arg);
            //System.out.println(t1);
            //System.out.println(tt);
            //System.out.println(funDecl.location());
            if (tt.getClass() != t1.getClass() && !((funDecl.name).equals("main"))) 
                throw new Report.Error("SEMANTIC ERROR at: " + funDecl.location()+" The return type of the function is different than type that expression of the function returns.");

        }

        return tt;
    }

    @Override
    public SemType visit(AstParDecl parDecl, Object arg) {
        //System.out.println("pardeclid = "+parDecl.id()+" arg = "+arg);
        if (parDecl.type != null) {
            SemType at = parDecl.type.accept(this, arg);            
            return at;
        }
        return null;
    }

    @Override
    public SemType visit(AstTypeDecl typeDecl, Object arg) {
        //System.out.println("typedeclid = "+typeDecl.id()+" arg = "+arg);
        if (typeDecl.type != null) {
        SemType tip = typeDecl.type.accept(this, arg);
        //System.out.println("tip = "+tip);
            if (tip instanceof SemType) {
                
                SemName ime = new SemName(typeDecl.name);
                ime.define(tip);
                if ((int)arg != -1) SemAn.declaresType.put(typeDecl, ime);
                
                return ime;
            } else {
                throw new Report.Error("SEMANTIC ERROR at: " + typeDecl.location() +" That type doesn't exist.");
            }

        }
        return null;
    }

    @Override
    public SemType visit(AstVarDecl varDecl, Object arg) {
        //System.out.println("vardeclid = "+varDecl.id()+" arg = "+arg);
        if ((int)arg == -1) {
            return varDecl.type.accept(this, arg);
            
        } else {
            if (varDecl.type != null) {

                SemType promen;
                promen = varDecl.type.accept(this, arg);
                if (promen instanceof SemType && !(promen instanceof SemVoid)) {
                SemAn.ofType.put(varDecl, promen);
                    return promen;
                } else {
                    throw new Report.Error("SEMANTIC ERROR at: "+ varDecl.location()+" This type is not declared yet");
                }

            }
            return null;
        }
    }

    // EXPRESSIONS

    @Override
    public SemType visit(AstArrExpr arrExpr, Object arg) {
        //System.out.println("arexprid = "+arrExpr.id()+" arg = "+arg);
        arrExpr.arr.accept(this, arg);
        arrExpr.idx.accept(this, arg);
        SemType st = SemAn.ofType.get(arrExpr.arr);
        SemType tip = SemAn.ofType.get(arrExpr.idx);
        //System.out.println(arrExpr.location());
        //System.out.println(st);
        //System.out.println(tip);
        if (!(tip instanceof SemInt) && !(tip instanceof SemName)) throw new Report.Error("SEMANTIC ERROR at: " + arrExpr.location()+ " The index is not a integer");
        else if ((tip instanceof SemName) && !(((SemName)tip).type() instanceof SemInt)) throw new Report.Error("SEMANTIC ERROR at: " + arrExpr.location()+ " The index is not a integer");
        if (!(st instanceof SemArr) && !(st instanceof SemName)) throw new Report.Error("SEMANTIC ERROR: "+ arrExpr.location()+" This is not an array");
        else if ((st instanceof SemName) && !(((SemName)st).type() instanceof SemArr)) throw new Report.Error("SEMANTIC ERROR: "+ arrExpr.location()+" This is not an array");
        
        SemAn.ofType.put(arrExpr, ((SemArr)st).elemType);
        return ((SemArr)st).elemType;
    }

    @Override
    public SemType visit(AstAtomExpr atomExpr, Object arg) {
        //System.out.println("atomexprid = "+atomExpr.id()+" arg = "+arg);
        switch (atomExpr.type) {
            case INT:
                TypeResolver.vrednost = Integer.valueOf(atomExpr.value);
                SemType tip = new SemInt();
                SemAn.ofType.put(atomExpr, tip);
                return tip;

            case BOOL:
                SemType tip1 = new SemBool();
                TypeResolver.vred = Boolean.valueOf(atomExpr.value);
                SemAn.ofType.put(atomExpr, tip1);
                return tip1;
                
            case VOID:
                SemType tip2 = new SemVoid();
                SemAn.ofType.put(atomExpr, tip2);
                return tip2;
                
            case CHAR:
                SemType tip3 = new SemChar();
                SemAn.ofType.put(atomExpr, tip3);
                return tip3;
                
            case STRING:
                SemType tip4 = new SemPtr(new SemChar());
                SemAn.ofType.put(atomExpr, tip4);
                return tip4;
                
            case POINTER:
                SemType tip5 = new SemPtr(new SemVoid());
                SemAn.ofType.put(atomExpr, tip5);
                return tip5;
            default: throw new Report.Error("SEMANTIC ERROR at:"+atomExpr.location()+" Does not exist an atom expression of that type.");
        }
    }

    @Override
    public SemType visit(AstBinExpr binExpr, Object arg) {
       // System.out.println("binexprid = "+binExpr.id()+" arg = "+arg);
        boolean a1 = vred;
        long i1 = vrednost;
        boolean a2;
        boolean a3;
        SemType ex1 = binExpr.fstExpr.accept(this, arg);
        long i2 = vrednost;
        a2 = vred;
        SemType ex2 = binExpr.sndExpr.accept(this, arg);
        long i3 = vrednost;
        a3 = vred;
        boolean ima1 = false;
        boolean ima2 = false;
        boolean b1 = false;
        boolean b2 = false;
        SemInt ti = new SemInt();
        SemBool tb = new SemBool();
        //System.out.println("VLEZE15");
        //System.out.println(ex1);
        //System.out.println(ex2);
        //System.out.println(binExpr.location());
        switch (binExpr.oper) {
            case OR:
                if (((ex1 instanceof SemBool) && (ex2 instanceof SemBool)) || ((ex1 instanceof SemName) && (ex2 instanceof SemName) && (((SemName)ex1).type() instanceof SemBool) && (((SemName)ex2).type() instanceof SemBool))) {
                    a1 = a2|a3;
                    SemAn.ofType.put(binExpr, new SemBool());
                    return tb;
                }
                else {
                    throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type bool");
                }

            case AND:
                if (((ex1 instanceof SemBool) && (ex2 instanceof SemBool)) || ((ex1 instanceof SemName) && (ex2 instanceof SemName) && (((SemName)ex1).type() instanceof SemBool) && (((SemName)ex2).type() instanceof SemBool))) {
                    a1 = a2&a3;
                    SemAn.ofType.put(binExpr, new SemBool());
                    return tb;
                }
                else {
                    throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type bool");
                }

            case ADD:
                if (((ex1 instanceof SemInt) && (ex2 instanceof SemInt)) || ((ex1 instanceof SemName) && (ex2 instanceof SemName) && (((SemName)ex1).type() instanceof SemInt) && (((SemName)ex2).type() instanceof SemInt))) {
                    i1 = i2+i3;
                    SemAn.ofType.put(binExpr, new SemInt());
                    return ti;
                }
                else {
                    
                    throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type int");
                
                }

            case SUB:
                if (((ex1 instanceof SemInt) && (ex2 instanceof SemInt)) || ((ex1 instanceof SemName) && (ex2 instanceof SemName) && (((SemName)ex1).type() instanceof SemInt) && (((SemName)ex2).type() instanceof SemInt))) {
                    i1 = i2-i3;
                    SemAn.ofType.put(binExpr, new SemInt());
                    return ti;
                }
                else {
                    throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type int");
                }

            case MOD:
                if (((ex1 instanceof SemInt) && (ex2 instanceof SemInt)) || ((ex1 instanceof SemName) && (ex2 instanceof SemName) && (((SemName)ex1).type() instanceof SemInt) && (((SemName)ex2).type() instanceof SemInt))) {
                    i1 = i2%i3;
                    SemAn.ofType.put(binExpr, new SemInt());
                    return ti;
                }
                else {
                    throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type int");
                }
            case MUL:
                if (((ex1 instanceof SemInt) && (ex2 instanceof SemInt)) || ((ex1 instanceof SemName) && (ex2 instanceof SemName) && (((SemName)ex1).type() instanceof SemInt) && (((SemName)ex2).type() instanceof SemInt))) {
                    i1 = i2*i3;
                    SemAn.ofType.put(binExpr, new SemInt());
                    //System.out.println(ex1);
                    //System.out.println(ex2);
                    return ti;
                }
                else {
                    throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type int");
                }
                
            case DIV:
                if (((ex1 instanceof SemInt) && (ex2 instanceof SemInt)) || ((ex1 instanceof SemName) && (ex2 instanceof SemName) && (((SemName)ex1).type() instanceof SemInt) && (((SemName)ex2).type() instanceof SemInt))) {
                    i1 = i2/i3;
                    
                    SemAn.ofType.put(binExpr, new SemInt());
                    return ti;
                }
                else {
                    throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type int");
                }
            case EQU:
            b1 = (ex1 instanceof SemInt) | (ex1 instanceof SemChar) | (ex1 instanceof SemBool) | (ex1 instanceof SemPtr);
             b2 = (ex2 instanceof SemInt) | (ex2 instanceof SemChar) | (ex2 instanceof SemBool) | (ex2 instanceof SemPtr);
             ima1 = false;
             ima2 = false;
            if (ex1 instanceof SemName) {
                ima1 = true;
            }

            if (ex2 instanceof SemName) {
                ima2 = true;
            }
            
            if ((b1 || (ima1 && ((((SemName)ex1).type()) instanceof SemInt) || ((((SemName)ex1).type()) instanceof SemChar) || ((((SemName)ex1).type()) instanceof SemBool) || ((((SemName)ex1).type()) instanceof SemPtr))) && (b2 || (ima2 && (((((SemName)ex2).type()) instanceof SemInt) || ((((SemName)ex2).type()) instanceof SemChar) || ((((SemName)ex2).type()) instanceof SemBool) || ((((SemName)ex2).type()) instanceof SemPtr))))) {
                SemAn.ofType.put(binExpr, new SemBool());
                return tb;
            } else throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type (char,ptr,int, bool)");
            case NEQ:
             b1 = (ex1 instanceof SemInt) | (ex1 instanceof SemChar) | (ex1 instanceof SemBool) | (ex1 instanceof SemPtr);
             b2 = (ex2 instanceof SemInt) | (ex2 instanceof SemChar) | (ex2 instanceof SemBool) | (ex2 instanceof SemPtr);
             ima1 = false;
             ima2 = false;
            if (ex1 instanceof SemName) {
                ima1 = true;
            }

            if (ex2 instanceof SemName) {
                ima2 = true;
            }
            
            if ((b1 || (ima1 && ((((SemName)ex1).type()) instanceof SemInt) || ((((SemName)ex1).type()) instanceof SemChar) || ((((SemName)ex1).type()) instanceof SemBool) || ((((SemName)ex1).type()) instanceof SemPtr))) && (b2 || (ima2 && (((((SemName)ex2).type()) instanceof SemInt) || ((((SemName)ex2).type()) instanceof SemChar) || ((((SemName)ex2).type()) instanceof SemBool) || ((((SemName)ex2).type()) instanceof SemPtr))))) {
                SemAn.ofType.put(binExpr, new SemBool());
                return tb;
            } else throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type (char,ptr,int, bool)");
            case GEQ:
            b1 = (ex1 instanceof SemInt) | (ex1 instanceof SemChar)   | (ex1 instanceof SemPtr);
            b2 = (ex2 instanceof SemInt) | (ex2 instanceof SemChar)  | (ex2 instanceof SemPtr);
            ima1 = false;
            ima2 = false;
           if (ex1 instanceof SemName) {
               ima1 = true;
           }

           if (ex2 instanceof SemName) {
               ima2 = true;
           }
           
           if ((b1 || (ima1 && ((((SemName)ex1).type()) instanceof SemInt) || ((((SemName)ex1).type()) instanceof SemChar) || ((((SemName)ex1).type()) instanceof SemPtr))) && (b2 || (ima2 && (((((SemName)ex2).type()) instanceof SemInt) || ((((SemName)ex2).type()) instanceof SemChar) || ((((SemName)ex2).type()) instanceof SemPtr))))) {
            SemAn.ofType.put(binExpr, new SemBool());
            return tb;
            } else throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type (char,ptr,int)");
                
            case LEQ:
             b1 = (ex1 instanceof SemInt) | (ex1 instanceof SemChar)   | (ex1 instanceof SemPtr);
             b2 = (ex2 instanceof SemInt) | (ex2 instanceof SemChar)  | (ex2 instanceof SemPtr);
             ima1 = false;
             ima2 = false;
            if (ex1 instanceof SemName) {
                ima1 = true;
            }

            if (ex2 instanceof SemName) {
                ima2 = true;
            }
            
            if ((b1 || (ima1 && ((((SemName)ex1).type()) instanceof SemInt) || ((((SemName)ex1).type()) instanceof SemChar) || ((((SemName)ex1).type()) instanceof SemPtr))) && (b2 || (ima2 && (((((SemName)ex2).type()) instanceof SemInt) || ((((SemName)ex2).type()) instanceof SemChar) || ((((SemName)ex2).type()) instanceof SemPtr))))) {
                SemAn.ofType.put(binExpr, new SemBool());
                return tb;
            } else throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type (char,ptr,int)");
                
            case GTH:
            b1 = (ex1 instanceof SemInt) | (ex1 instanceof SemChar)   | (ex1 instanceof SemPtr);
            b2 = (ex2 instanceof SemInt) | (ex2 instanceof SemChar)  | (ex2 instanceof SemPtr);
            ima1 = false;
            ima2 = false;
           if (ex1 instanceof SemName) {
               ima1 = true;
           }

           if (ex2 instanceof SemName) {
               ima2 = true;
           }
           
           if ((b1 || (ima1 && ((((SemName)ex1).type()) instanceof SemInt) || ((((SemName)ex1).type()) instanceof SemChar) || ((((SemName)ex1).type()) instanceof SemPtr))) && (b2 || (ima2 && (((((SemName)ex2).type()) instanceof SemInt) || ((((SemName)ex2).type()) instanceof SemChar) || ((((SemName)ex2).type()) instanceof SemPtr))))) {
            SemAn.ofType.put(binExpr, new SemBool());
            return tb;
            } else throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type (char,ptr,int)");
                
            case LTH:
            b1 = (ex1 instanceof SemInt) | (ex1 instanceof SemChar)   | (ex1 instanceof SemPtr);
            b2 = (ex2 instanceof SemInt) | (ex2 instanceof SemChar)  | (ex2 instanceof SemPtr);
            ima1 = false;
            ima2 = false;
           if (ex1 instanceof SemName) {
               ima1 = true;
           }

           if (ex2 instanceof SemName) {
               ima2 = true;
           }
           
           if ((b1 || (ima1 && ((((SemName)ex1).type()) instanceof SemInt) || ((((SemName)ex1).type()) instanceof SemChar) || ((((SemName)ex1).type()) instanceof SemPtr))) && (b2 || (ima2 && (((((SemName)ex2).type()) instanceof SemInt) || ((((SemName)ex2).type()) instanceof SemChar) || ((((SemName)ex2).type()) instanceof SemPtr))))) {
            SemAn.ofType.put(binExpr, new SemBool());
            return tb;
            } else throw new Report.Error("SEMANTIC ERROR at: "+binExpr.location()+" The expressions are not of type (char,ptr,int)");
              
               
            default:
                break;
        }
       
            
        return null;
    }

    @Override
    public SemType visit(AstCallExpr callExpr, Object arg) {
        //System.out.println("callexprid = "+callExpr.id()+" arg = "+arg);
        callExpr.args.accept(this, arg);
        AstDecl ad = SemAn.declaredAt.get(callExpr);
        SemAn.ofType.put(callExpr, SemAn.ofType.get(ad));
        return SemAn.ofType.get(ad);
    }

    @Override
    public SemType visit(AstCastExpr castExpr, Object arg) {
        //System.out.println("castexprid = "+castExpr.id()+" arg = "+arg);
        SemType exp;
        exp = castExpr.expr.accept(this, arg);
        SemType tip;
        tip = castExpr.type.accept(this, arg);
        SemType ret = tip;
        SemAn.ofType.put(castExpr, ret);
        return ret;
    }

    @Override
    public SemType visit(AstNameExpr nameExpr, Object arg) {
        //System.out.println("nameexprid = "+nameExpr.id()+" arg = "+arg);
        AstDecl ad = SemAn.declaredAt.get(nameExpr);
        //System.out.println(ad.getClass());
        if (ad instanceof AstVarDecl) {
            SemType tip = ((AstVarDecl)ad).type.accept(this, -1);
            SemAn.ofType.put(nameExpr, tip);
            return tip;
        } else if (ad instanceof AstParDecl) {
            //System.out.println("VLEZE");
            SemType tip = ((AstParDecl)ad).type.accept(this, -1);
            SemAn.ofType.put(nameExpr, tip);
            return tip;
        } else if (ad instanceof AstCompDecl) {
            //System.out.println("adsadadsad");
            SemType tip = ((AstCompDecl)ad).type.accept(this, -1);
            //System.out.println(tip);
            //System.out.println(":aads");
            SemAn.ofType.put(nameExpr, tip);
            return tip;
        }
       
        return null;
    }

    @Override
    public SemType visit(AstPfxExpr pfxExpr, Object arg) {
        //System.out.println("pfxexprid = "+pfxExpr.id()+" arg = "+arg);
        if (pfxExpr.expr != null) {
            long mom = TypeResolver.vrednost;
            TypeResolver.vrednost = 0;
            SemType tip;
            tip = pfxExpr.expr.accept(this, arg);
            //System.out.println(tip);
            //System.out.println(pfxExpr.location());
            switch (pfxExpr.oper) {
                case ADD:
                    if (!(tip instanceof SemInt) && !(tip instanceof SemName)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a integer");
                    else if ((tip instanceof SemName) && !(((SemName)tip).type() instanceof SemInt)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a integer");
                    TypeResolver.vrednost = mom + TypeResolver.vrednost;
                    SemAn.ofType.put(pfxExpr, tip);
                    return tip;
                case SUB:
                if (!(tip instanceof SemInt) && !(tip instanceof SemName)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a integer");
                    else if ((tip instanceof SemName) && !(((SemName)tip).type() instanceof SemInt)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a integer");
                    SemAn.ofType.put(pfxExpr, tip);
                    return tip;
                case NOT:
                if (!(tip instanceof SemBool) && !(tip instanceof SemName)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a boolean");
                    else if ((tip instanceof SemName) && !(((SemName)tip).type() instanceof SemBool)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a boolean");
                    if (TypeResolver.vred == true) TypeResolver.vred = false;
                    else TypeResolver.vred = true;
                    SemAn.ofType.put(pfxExpr, tip);
                    return tip;
                case PTR:
                    SemPtr tip1 = new SemPtr(tip);
                    SemAn.ofType.put(pfxExpr, tip1);
                    return tip1;
                case NEW:
                    if (!(tip instanceof SemInt) && !(tip instanceof SemName)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a integer");
                    else if ((tip instanceof SemName) && !(((SemName)tip).type() instanceof SemInt)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a integer");
                    SemPtr tip2 = new SemPtr(new SemVoid());
                    SemAn.ofType.put(pfxExpr, tip2);
                    return tip2;
                case DEL:
                    if (!(tip instanceof SemPtr) && !(tip instanceof SemName)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a pointer");
                    else if ((tip instanceof SemName) && !(((SemName)tip).type() instanceof SemPtr)) throw new Report.Error("SEMANTIC ERROR at: "+pfxExpr.location()+" This is not a pointer");
                    SemVoid sv = new SemVoid();
                    SemAn.ofType.put(pfxExpr, sv);
                    return sv;
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public SemType visit(AstRecExpr recExpr, Object arg) {
        //System.out.println("recexprid = "+recExpr.id()+" arg = "+arg);
        
        recExpr.rec.accept(this, arg);
        SemType tip = recExpr.comp.accept(this, arg);
        SemAn.ofType.put(recExpr, tip);
        return tip;
    }

    @Override
    public SemType visit(AstSfxExpr sfxExpr, Object arg) {
        //System.out.println("sfxexprid = "+sfxExpr.id()+" arg = "+arg);
        
        SemType tip = sfxExpr.expr.accept(this, arg);
        if (tip instanceof SemPtr) {
            SemAn.ofType.put(sfxExpr, ((SemPtr)tip).baseType);
            //System.out.println("VLEZESFX");
            //System.out.println(((SemPtr)tip).baseType);
            return ((SemPtr)tip).baseType;
        } else throw new Report.Error("SEMANTIC ERROR at: "+sfxExpr.location()+" This is not a pointer");

    }

    @Override
    public SemType visit(AstStmtExpr stmtExpr, Object arg) {
        //System.out.println("stmtexprid = "+stmtExpr.id()+" arg = "+arg);
        
         SemType st = stmtExpr.stmts.accept(this, 44);
         SemAn.ofType.put(stmtExpr, st);
        return st;
    }

    @Override
    public SemType visit(AstWhereExpr whereExpr, Object arg) {
        //System.out.println("whereexprid = "+whereExpr.id()+" arg = "+arg);
            whereExpr.decls.accept(this, arg);
           SemType tip =  whereExpr.expr.accept(this, arg);
           SemAn.ofType.put(whereExpr, tip);
        return tip;
    }

    // STATEMENTS

    @Override
    public SemType visit(AstAssignStmt assignStmt, Object arg) {
       // System.out.println("assignstmtid = "+assignStmt.id()+" arg = "+arg);
        
        SemType ex1 = assignStmt.dst.accept(this, arg);
        SemType ex2 = assignStmt.src.accept(this, arg);
        //System.out.println(assignStmt.dst.getClass());
        //System.out.println(ex1);
        //System.out.println(ex2);
        if (ex1.getClass() != ex2.getClass() || (ex1 instanceof SemVoid))
            throw new Report.Error("SEMANTIC ERROR at: "+ assignStmt.location() + " Types of statements are different");
        SemVoid sv = new SemVoid();
        SemAn.ofType.put(assignStmt, sv);
        return sv;
    }


    @Override
    public SemType visit(AstExprStmt exprStmt, Object arg) {
        //System.out.println("exprstmtid = "+exprStmt.id()+" arg = "+arg);
        
            SemType st =  exprStmt.expr.accept(this, arg);

            return st;
    }

    @Override
    public SemType visit(AstIfStmt ifStmt, Object arg) {
        //System.out.println("ifstmtid = "+ifStmt.id()+" arg = "+arg);
        
         SemType st =  ifStmt.cond.accept(this, arg);
         if (!(st instanceof SemBool)) throw new Report.Error("SEMANTIC ERROR at: "+ifStmt.location()+" The condition of the if statement is not of type boolean");
        if (ifStmt.thenStmt != null)
            ifStmt.thenStmt.accept(this, arg);
        if (ifStmt.elseStmt != null)
            ifStmt.elseStmt.accept(this, arg);
        SemVoid sv = new SemVoid();
        SemAn.ofType.put(ifStmt, sv);
        return sv;
    }

    @Override
    public SemType visit(AstWhileStmt whileStmt, Object arg) {
        //System.out.println("whilestmtid = "+whileStmt.id()+" arg = "+arg);
        
        SemType st  = whileStmt.cond.accept(this, arg);
        if (!(st instanceof SemBool)) throw new Report.Error("SEMANTIC ERROR at: "+whileStmt.location()+" The condition of the while statement is not of type boolean");
        if (whileStmt.bodyStmt != null)
            whileStmt.bodyStmt.accept(this, arg);
            SemVoid sv = new SemVoid();
        SemAn.ofType.put(whileStmt, sv);
        return null;
    }

    // TYPES

    @Override
    public SemType visit(AstArrType arrType, Object arg) {
        //System.out.println("arrtypeid = "+arrType.id()+" arg = "+arg);
        if ((int)arg == -1) {
            arrType.numElems.accept(this, arg);
            return SemAn.isType.get(arrType);

        } else {
        SemType tip1;
        long temp = TypeResolver.vrednost;
        TypeResolver.vrednost = 0;
            tip1 = arrType.elemType.accept(this, arg);
            if (tip1 instanceof SemVoid) throw new Report.Error("SEMANTIC ERROR at: "+arrType.location()+"You can't define an array of type void");
        if (arrType.numElems != null)
            arrType.numElems.accept(this, arg);
        SemArr ret = new SemArr(tip1, TypeResolver.vrednost);
        TypeResolver.vrednost = temp;
        SemAn.isType.put(arrType, ret);
        return ret;
        }
    }

    @Override
    public SemType visit(AstAtomType atomType, Object arg) {
        //System.out.println("atomtypeid = "+atomType.id()+" arg = "+arg);
       
        switch (atomType.type) {
                case INT: 
                    SemInt a = new SemInt();
                    if ((int)arg != -1) SemAn.isType.put(atomType, a);
                    return a;
                    
                case CHAR: 
                    SemChar b = new SemChar();
                    if ((int)arg != -1) SemAn.isType.put(atomType, b);
                    return b;
                    
                case BOOL: 
                    SemBool c = new SemBool();
                    if ((int)arg != -1) SemAn.isType.put(atomType, c);
                    return c;
                    
                case VOID: 
                    SemVoid d = new SemVoid();
                    if ((int)arg != -1) SemAn.isType.put(atomType, d);
                    return d;
                    
            
                default: throw new Report.Error("SEMANTIC ERROR at: " + atomType.location()+ " There isn't atomtype with that name");
                    
            }
        
       
    }

    @Override
    public SemType visit(AstNameType nameType, Object arg) {
        //System.out.println("nametypeid = "+nameType.id()+" arg = "+arg);
        AstDecl ad = SemAn.declaredAt.get(nameType);
        //System.out.println(nameType.name);
       // System.out.println(ad);
        if (ad instanceof AstTypeDecl) {
            SemType tip = SemAn.declaresType.get(((AstTypeDecl)ad));
            //System.out.println(tip);
            //System.out.println(((SemName)tip).name);
            //System.out.println(((AstTypeDecl)ad).name);
            //System.out.println(((AstTypeDecl)ad).type.getClass());
            SemAn.isType.put(nameType, tip);
            return tip;
        } else if (ad instanceof AstCompDecl) {
            SemType tip = SemAn.declaresType.get(((AstTypeDecl)ad));
            SemAn.isType.put(nameType, tip);
            return tip;
        }
        
        return null;
    }

    @Override
    public SemType visit(AstPtrType ptrType, Object arg) {
       // System.out.println("ptrtypeid = "+ptrType.id()+" arg = "+arg);
        if (ptrType.baseType != null) {
            SemType tip = ptrType.baseType.accept(this, arg);
            SemPtr ret = new SemPtr(tip);
            SemAn.isType.put(ptrType,ret);
            return ret;
        }
        return null;
    }

    @Override
    public SemType visit(AstRecType recType, Object arg) {
        //System.out.println("rectypeid = "+recType.id()+" arg = "+arg);
        vek = new Vector<SemType>();
        recType.comps.accept(this, arg);
        SemRec sr = new SemRec(vek);
        SemAn.isType.put(recType, sr);
        return sr;
    }

}
