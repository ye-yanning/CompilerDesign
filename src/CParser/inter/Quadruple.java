package CParser.inter;

public class Quadruple {
    String[][] quadruple = new String[1000][4];
    int quadNum;
    void InfoPrint(){
        for(int i=0;i<quadNum;i++)
        {
            System.out.println(i+" : "+quadruple[0]+","+quadruple[1]+","+quadruple[2]+","+quadruple[3]);
        }
    }
}
