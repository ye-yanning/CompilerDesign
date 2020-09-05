package CParser.inter;

public class Stmt extends Node{
    public static boolean Error = false;
    public Stmt(){}
    public static Stmt Null = new Stmt();
    public void gen(int b,int a){}
    int after=0;
    public static Stmt Enclosing = Stmt.Null;      //用于break语句
}
