package CParser.parser;

import CParser.inter.*;
import CParser.lexer.*;
import CParser.symbols.Array;
import CParser.symbols.Env;
import CParser.symbols.Type;

import java.io.IOException;

public class Parser {
    private Lexer lex;
    private Token look;
    private boolean whetherFor = false;
    Env top = null;
    int used = 0;
    public static StringBuffer isError = new StringBuffer("");
    public static boolean ErrorSign = false;
    public Parser(Lexer l)
    {
        lex = l;
        move();
    }
    void move() {
        if(look!=null)
        System.out.println("current:"+look.transforToString());
        look = lex.scan();
    }
    void error(String s){
        System.out.println("isError:"+isError);
        System.out.println("出错sacasc");
        System.out.println("isError:"+isError);
        ErrorSign = true;
        Stmt.Error = true;
        Expr.Error = true;
       // lex.toTheEnd();
        isError.append("near line"+lex.line+":"+s+"\n");

        throw new Error("near line"+lex.line+":"+s);


        //isError.append("near line"+lex.line+":"+s);
    }
    void match(int t){
        if(look.tag==t)
            move();
        else {
            error("syntax error");
        }
    }
    public String ConsolePrint(){
        if(ErrorSign==true)
            return isError.toString();
        else return "编译成功";
    }
    public void recover(){
        ErrorSign = false;
        isError = new StringBuffer("");
    }
    public void program() {
        Stmt s = block();
        System.out.println("s"+s);
        int begin = s.newlabels();
        int after = s.newlabels();
        s.emitlabels(begin);

        s.gen(begin,after);
        s.emitlabels(after);
    }

    Stmt block() {
        //System.out.println("调用block");
        match('{');
        Env savedEnv = top;
        top=new Env(top);
        decls();
        Stmt s = stmts();
        match('}');
        top=savedEnv;
        System.out.println("ss:"+s);
        return s;
    }
    void decls(){
        while(look.tag == Tag.BASIC){
            Type p = type();
            Token tok = look;
            match(Tag.ID);
            match(';');
            Id id = new Id((Word)tok,p,used);
            top.put(tok,id);
            used = used+p.width;
        }
    }
    Type type() {
       Type p = (Type)look;
       match(Tag.BASIC);
       if(look.tag !='[')
           return p;
       else return dims(p);
    }
    Type dims(Type p) {
        match('[');
        Token tok = look;
        match(Tag.NUM);
        match(']');
        if(look.tag=='[')
            p=dims(p);
        return new Array(((Num)tok).value,p);
    }

    Stmt stmts() {
        //System.out.println("look.tag:"+look.transforToString());
        if(look.tag=='}')
            return Stmt.Null;
        if(ErrorSign)
            return null;
        else return new Seq(stmt(),stmts());
    }

    Stmt stmt() {
        Expr x;
        Stmt s,s1,s2;
        Stmt saveStmt;

        switch (look.tag){
            case ';':
                move();
                return Stmt.Null;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                x=bool();
                match(')');
                s1 = stmt();
                if(look.tag != Tag.ELSE) return new If(x,s1);
                match(Tag.ELSE);
                s2 = stmt();
                return new Else(x,s1,s2);

            case Tag.WHILE:
                While whilenode = new While();
                saveStmt = Stmt.Enclosing;
                Stmt.Enclosing=whilenode;
                match(Tag.WHILE);
                match('(');
                x=bool();
                match(')');
                s1 = stmt();
                whilenode.init(x,s1);
                Stmt.Enclosing = saveStmt;
                return whilenode;

            case Tag.DO:
                Do donode = new Do();
                saveStmt = Stmt.Enclosing;
                Stmt.Enclosing = donode;
                match(Tag.DO);
                s1 = stmt();
                match(Tag.WHILE);
                match('(');
                x=bool();
                match(')');
                match(';');
                donode.init(s1,x);
                Stmt.Enclosing = saveStmt;
                return donode;

            case Tag.BREAK:
                match(Tag.BREAK);
                match(';');
                return new Break();
            case Tag.FOR:
                whetherFor = true;
                For fornode = new For();
                saveStmt = Stmt.Enclosing;
                Stmt.Enclosing = fornode;
                match(Tag.FOR);
                match('(');
                s=stmt();
                match(';');
                x=bool();
                match(';');
                s1=stmt();
                match(')');
                s2=stmt();
                fornode.init(x,s,s1,s2);
                Stmt.Enclosing = saveStmt;
                whetherFor = false;
                return fornode;
            case '{':
                return block();
                default:
                    return assign();
        }
    }
    Stmt assign() {
       // System.out.println("按需分配");
        Stmt stmt;
        Token t = look;
        //System.out.println(look.transforToString());
        match(Tag.ID);
        Id id = top.get(t);
        if(id==null){
            error(t.transforToString()+" undeclared");
            return null;
        }

        if(look.tag=='='){
            move();
            //System.out.println("=:"+look.transforToString());
            stmt = new Set(id,bool());
        }
        else{
            Access x = offset(id);
            match('=');
            stmt=new SetElem(x,bool());
        }
        if(!whetherFor)
        match(';');
        return stmt;
    }
    Expr bool() {
        Expr x = join();
        while(look.tag==Tag.OR){
            Token tok = look;
            move();
            x = new Or(tok,x,join());
        }
        return x;
    }
    Expr join() {
        Expr x = equality();
        while(look.tag==Tag.AND){
            Token tok = look;
            move();
            x= new And(tok,x,equality());
        }
        return x;
    }

    Expr equality() {
        Expr x = rel();
        while(look.tag==Tag.EQUAL || look.tag ==Tag.NE){
            Token tok = look;
            move();
            x=new Rel(tok,x,rel());
        }
        return x;
    }
    Expr rel() {
        Expr x = expr();
        switch (look.tag){
            case '<':
            case Tag.LE:
            case '>':
            case Tag.GE:
                Token tok = look;
                move();
                return new Rel(tok,x,expr());
                default:
                    return x;
        }
    }
    Expr expr() {
        Expr x = term();
        while(look.tag=='+'||look.tag=='-'){
            Token tok = look;
            move();
            x=new Arith(tok,x,term());
        }
        return x;
    }

    Expr term(){
        Expr x =unary();
        while(look.tag=='*'||look.tag=='/'){
            Token tok = look;
            move();
            x=new Arith(tok,x,unary());
        }
        return x;
    }
    Expr unary(){
        if(look.tag =='-'){
            move();
            return new Unary(Word.minus,unary());
        }
        else if(look.tag=='!'){
            Token tok = look;
            move();
            return new Not(tok,unary());
        }
        else return factor();
    }

    Expr factor() {
        Expr x = null;
        switch (look.tag){
            case '(':
                move();
                x = bool();
                match(')');
                return x;
            case Tag.NUM:
                x = new Constant(look,Type.Int);
                move();
                return x;
            case Tag.REAL:
                x= new Constant(look,Type.Float);
                move();
                return x;
            case Tag.TRUE:
                x=Constant.True;
                move();
                return x;
            case Tag.FALSE:
                x=Constant.False;
                move();
                return x;
                default:
                    error("syntax error");
                    return x;
            case Tag.ID:
                String s = look.transforToString();
                Id id = top.get(look);
                if(id==null)
                {
                    error(look.transforToString()+" undeclared");
                    return null;
                }
                move();
                if(look.tag!='[')
                    return id;
                else return offset(id);
        }
    }
    Access offset(Id a) {
        Expr i;
        Expr w;
        Expr t1,t2;
        Expr loc;
        Type type = a.type;
        match('[');
        i = bool();
        match(']');
        if(Stmt.Error)
            return null;
        type = ((Array)type).of;
        w = new Constant(type.width);
        t1 = new Arith(new Token('*'),i,w);
        loc = t1;
        while(look.tag=='['){
            match('[');
            i= bool();
            match(']');
            type = ((Array)type).of;
            w= new Constant(type.width);
            t1 = new Arith(new Token('*'),i,w);
            t2 = new Arith(new Token('+'),loc,t1);
            loc = t2;
        }
        return new Access(a,loc,type);
    }

}
