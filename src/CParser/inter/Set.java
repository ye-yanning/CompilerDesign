package CParser.inter;
import CParser.symbols.Env;
import CParser.symbols.Type;
import CParser.parser.Parser;
import java.util.regex.Pattern;

public class Set extends Stmt {
    public Id id;
    public  Expr expr;
    public Set(Id i,Expr x){
        id=i;
        expr=x;
        if(Error)
            return;
    if(check(id.type,expr.type)==null){
        System.out.println(id.transforToString()+" "+id.type.width+" "+expr.type.width+" "+expr.transforToString());
        error("wtf type error");
    }

    }
    public Type check(Type p1,Type p2){
        if(Type.numeric(p1)&&Type.numeric(p2))
            return p2;
        else if(p1 ==Type.Bool&&p2 == Type.Bool)
            return p2;
        else return null;
    }

    public void gen(int b,int a){
        //System.out.println("expr.string:"+expr.transforToString());
        if(Error)
            return;
        String target = expr.gen().transforToString();

        emit(id.transforToString()+"="+target);
        if(quad[quadNum]==null)
        quad[quadNum]=new quadruple();
        quad[quadNum].result=id.transforToString();
        System.out.println("target:"+target);
                if(whetherNum(target))
                {

                    quad[quadNum].arg1 = target;
                    quad[quadNum].op= ":=";
                    quad[quadNum].arg2="-";
                    //System.out.println("test:"+quad[quadNum].op+" "+quad[quadNum].arg1+" "+quad[quadNum].arg2);
                }
        //System.out.println("test:"+quad[quadNum].op+" "+quad[quadNum].arg1+" "+quad[quadNum].arg2);


        //System.out.println("quadNum:"+quadNum+" id.transforToString():"+id.transforToString());
        quadNum++;
    }
    //判断右侧是算式还是变量或数，如果是变量或者数则返回正
    boolean whetherNum(String target){
        String regN = "[0-9|/.]";
        for(int i =0;i<target.length();i++)
        {
            switch (target.charAt(i)){
                case '+':
                case '*':
                case '/':
                case '%':
                    return false;
                case '-':
                    if(i!=0)
                        return false;

            }
        }
            return true;
    }
}
