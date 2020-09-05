import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class literal {

    String test = new String("");
    Set <String> k = new HashSet();
    Set s_ao = new HashSet();
    Set s_ro = new HashSet();
    Set s_divide = new HashSet();
    Set id = new HashSet();
    Map signal = new HashMap();
    Set n = new HashSet();

    class word{
        int type;
        Object detail;
        int from;
        int to;
        int row;
    }
    word []literalWord = new word[1000];
    int totalWord=0;
    void CPPinitial()
    {
        String []kinitial = new String[]{"int","double","float","while","for","continue","switch","case","if","else","break","true","do"};
        String []ao=new String[]{"+","-","*","/","+=","-=","*=","/=","="};
        String []rela =new String[]{"<",">","<=",">=","=="};
        String []ro=new String[]{"{","}",";","(",")","]","["};
        String []divide=new String[]{"//","*/","/*"};
        for(int i=0;i<kinitial.length;i++)
        k.add(kinitial[i]);
        for(int j=0;j<ao.length;j++)
            signal.put(ao[j],2);
        for(int j=0;j<ro.length;j++)
            signal.put(ro[j],3);
        for(int j=0;j<divide.length;j++)
            signal.put(divide[j],4);
        for(int j=0;j<rela.length;j++)
            signal.put(rela[j],5);

    }
    void infoPrint() {
        System.out.println("-----------------------");
        System.out.println("关键字Set");
        System.out.println(k);
        System.out.println("算数运算符Set");
        System.out.println(s_ao);
        System.out.println("/关系运算符Set");
        System.out.println(s_ro);
        System.out.println("分隔符Set");
        System.out.println(s_divide);
        System.out.println("标识符Set");
        System.out.println(id);
        System.out.println("无符号数Set");
        System.out.println(n);
    }

    void WordPrint()
    {
        System.out.println("-------------------全单词输出-------------------");
        for(int i=0;i<totalWord;i++)
        {
            System.out.print("re: "+literalWord[i].detail+"  类型代码:"+literalWord[i].type+" 所在行数:"+literalWord[i].row+"   ");
            switch (literalWord[i].type)
            {
                case 6:
                    System.out.println("类型:无符号整数");
                    break;
                case 2:
                    System.out.println("类型:算术运算符");
                    break;
                case 3:
                    System.out.println("类型:分隔符");
                    break;
                case 8:
                    System.out.println("类型:关键字");
                    break;
                case 7:
                    System.out.println("类型:标识符");
                    break;
                case 5:
                    System.out.println("类型:关系运算符");
                    break;
            }
        }
        System.out.println("-------------------全单词输出-------------------");
    }
    void FileRead(){
        File testFile = new File("F:\\编译原理课设\\test.txt");
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(testFile));
            String tempString = null;
            int line = 1;
            while((tempString=reader.readLine())!=null)
            {
                System.out.println("tempString:        "+tempString);

                test=tempString;
                L=line;
                Process();
                System.out.println("test:"+test);
                line++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(reader!=null)
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

        }
    }
    int L=0;
    String regA = "[a-zA-Z]";
    String regB="[\\+\\-\\*\\/\\=]";
    String regC="[\\(\\)\\{\\}\\;\\[\\]]";
    String regD="[0-9]";
    String regE="[\\<\\>]";
    void Process(){
    int indexT=0;
    int index1=0;
    int index2=0;
    String str1=" ";
    int type=-1;

    while(indexT<test.length())
    {
        System.out.println("Row:"+L+" "+"indexT:"+indexT+" 当前:"+test.charAt(indexT)+" test:"+test);
        //System.out.println("substring(indexT,indexT):"+test.substring(indexT,indexT+1));
        while(test.charAt(indexT)==' '||test.charAt(indexT)=='\n'||test.charAt(indexT)=='\t')
            indexT++;

        if(Pattern.matches(regB,test.substring(indexT,indexT+1)))
        {
            System.out.println("执行1");
            if(indexT!=test.length()-1&&test.charAt(indexT+1)=='=')
            {
                index1=indexT;
                indexT++;
                str1=test.substring(index1,++indexT);
                type=(int)signal.get(str1);
                System.out.println("str:"+str1+" type:"+type);
            }
            else{
                str1=test.substring(indexT,++indexT);
                System.out.println("str:"+str1+" type:"+type);
                type=(int)signal.get(str1);

            }
        }
        else if(Pattern.matches(regE,test.substring(indexT,indexT+1)))
        {
            if(indexT!=test.length()-1&&test.charAt(indexT+1)=='=')
            {
                index1=indexT;
                indexT++;
                str1=test.substring(index1,++indexT);
                type=(int)signal.get(str1);
                System.out.println("str:"+str1+" type:"+type);
            }
            else{
                System.out.println("<>");
                str1=test.substring(indexT,++indexT);
                System.out.println("str:"+str1+" type:"+type);
                type=(int)signal.get(str1);
            }
        }
        else if(Pattern.matches(regC,test.substring(indexT,indexT+1)))
        {
            str1=test.substring(indexT,indexT+1);
            indexT++;

            type=(int)signal.get(str1);
            System.out.println("str:"+str1+" type:"+type);

        }

        else if(Pattern.matches(regA,test.substring(indexT,indexT+1)))
        {
            System.out.println("执行2");
            System.out.println("substring(indexT,indexT):"+test.substring(indexT,indexT+1));
            index1=indexT;
            while(Pattern.matches(regA,test.substring(indexT,indexT+1)))
                indexT++;
            str1=test.substring(index1,indexT);
            System.out.println("str:"+str1);
            if(!k.contains(str1))
            {
                id.add(str1);
                type=7;
            }
            else type=8;

        }
        else if(Pattern.matches(regD,test.substring(indexT,indexT+1)))
        {
            int dot=0;
            index1=indexT;
            while(Pattern.matches(regD,test.substring(indexT,indexT+1)))
                indexT++;
            str1=test.substring(index1,indexT);
            System.out.println("str:"+str1);
            n.add(str1);
            type=6;
        }
        literalWord[totalWord] = new word();
        literalWord[totalWord].detail=str1;
        literalWord[totalWord].from=index1;
        literalWord[totalWord].to=indexT;
        literalWord[totalWord].type=type;
        literalWord[totalWord++].row=L;
    }
    }
    public static void main(String[] args)
    {
        literal l = new literal();
        l.CPPinitial();
        l.FileRead();
        l.infoPrint();
        l.Process();
        l.WordPrint();
        System.out.println("sa");
    }
}
