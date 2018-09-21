import java.io.*;
import java.util.Scanner;

public class B {
    static int y=0;//высота
    static int v=0;//вакансии
    static int x=0;//ширина
    static int s=0;//общая площадь
    static int k=0;//просто к
    static boolean waswrong=false;//переменная для рекурсии
    static char [][] mass = new char[100][100];//массив для входных данных
    static int [][] masscopy = new int[100][100];//массив копия
    static int[][]resh=new int [100][100];//массив с правильным решением если есть
    static int variacii[][] = new int[2][100];//массив для вариаций возможных прямоугольников
    public static void main(String[] args) throws Exception{
        File f = new File("input.txt");
        FileReader reader = new FileReader(f);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            char[] array = sc.nextLine().toCharArray();
            x=array.length;
            for (int i=0; i<x;i++){
                mass[y][i]=array[i];
            }
            y++;
        }
        resh[0][0]=547;

        s=x*y;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j <x; j++) {
                if (mass[i][j]=='.'){
                    masscopy[i][j]=100;
                }
                if (mass[i][j]=='o'){
                    masscopy[i][j]=10;
                }
            }
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j <x; j++) {
                //System.out.print(mass[i][j]);
                if(mass[i][j] =='o'){v++;}
            }
            //System.out.println();
        }

        //System.out.println();
        // System.out.println(x);
        // System.out.println();
        // System.out.println(y);
        // System.out.println();
        // System.out.println(s);
        // System.out.println();
        // System.out.println(v);

        if((s%v)==0){
            int minis=s/v;//площадь на одну вакансию


            k=0;
            boolean douebing;
            for(int i = 1; i < (minis/2)+1; i++){
                douebing=true;//пепеменная для проверки повторного входа делителя в ранее встреченные коомбинации
                if((minis%i)==0){

                    for(int j = 0;j < k;j++){
                        if((variacii[0][j]==i)||(variacii[1][j]==i)){
                            douebing=false;
                        }

                    }
                    if(douebing==true){
                        if( ((i<=x)||(i<=y)) & (((minis/i)<=x)||((minis/i)<=y)) ){//проверка чтобы делители площадей не выходили за грани
                            if (i<(minis/i)){
                                variacii[0][k]=i;
                                variacii[1][k]=minis/i;
                            }
                            else {
                                variacii[1][k]=i;
                                variacii[0][k]=minis/i;
                            }

                            k++;
                        }
                    }
                }
            }
            for(int z = 0; z<k;z++){
                //System.out.println(variacii[0][z]+" "+variacii[1][z]);
                for (int i = 0; i < y; i++) {
                    for (int j = 0; j <x; j++) {
                        if (mass[i][j]=='.'){
                            masscopy[i][j]=100;
                        }
                        if (mass[i][j]=='o'){
                            masscopy[i][j]=1000;
                        }
                    }
                }
                recursia(masscopy, resh,false,1,variacii,0,0);
            }
               /* for (int i = 0; i < y; i++) {
                    for (int j = 0; j <x; j++) {
                        System.out.print(resh[i][j]+"  ");
                        //if(mass[i][j] =='o'){v++;}
                    }
                    System.out.println();
                }*/
            if (resh[0][0]!=547){
                printer(resh);}
        }
        else{
            //площадь не делится на количество вакансий решения нет
        }

    }

    public static int[][] builder(int[][] masscopy, int vcount, int dlina,int shir, int xc,int yc){
        for(int i=yc;(i<(yc+dlina));i++)
            for (int j = xc; j<xc+shir; j++) {
                if (masscopy[i][j]==100){
                    masscopy[i][j]=vcount;
                }
                if (masscopy[i][j]==1000){
                    masscopy[i][j]=10*vcount;
                }
            }
        return masscopy;
    }

    public static int[][] debuilder(int[][] masscopy, int vcount){
        for(int i=0;i<y;i++)
            for (int j = 0; j<x; j++) {
                if ((masscopy[i][j]/vcount)==1){
                    masscopy[i][j]=100;
                }
                if ((masscopy[i][j]/vcount)==10){
                    masscopy[i][j]=1000;
                }
            }
        return masscopy;
    }

    public static int wherefreex(int[][] masscopy){
        int a=0;
        int b=0;
        while ((masscopy[a][b]!=100)&(masscopy[a][b]!=1000)&(!((a==y)&(b==x)))){

            if(b>x){
                b=0;
                a++;
            }
            else {
                b++;}

        }
        if ((a==y)&(b==x)){
            return 345;
        }
        else {
            return b;
        }
    }

    public static int wherefreey(int[][] masscopy){
        int a=0;
        int b=0;
        while ((masscopy[a][b]!=100)&(masscopy[a][b]!=1000)&(!((a==y)&(b==x)))){

            if(b>x){
                b=0;
                a++;
            }
            else {
                b++;}
        }
        if ((a==y)&(b==x)){
            return 345;
        }
        else {
            return a;
        }
    }

    public static int watindex(int[][] masscopy, int vcount, int [][] variacii) {

        int tempxstart = 0;
        int tempxfinish = 0;
        int tempystart = 0;
        int tempyfinish = 0;
        int a = 0;
        int b = 0;
        while ((masscopy[a][b] != (vcount)) & (masscopy[a][b] != (vcount * 10))) {
            if (b > x) {
                b = 0;
                a++;
            } else {
                b++;
            }
        }
        tempxstart = b;
        tempystart = a;
        tempxfinish=tempxstart;
        while ((masscopy[a][b] == (vcount)) || (masscopy[a][b] == (vcount * 10))) {
            tempxfinish++;
            b++;

        }
        while ((masscopy[a][b - 1] == (vcount)) || (masscopy[a][b - 1] == (vcount * 10))) {

            a++;

        }
        tempyfinish = a;

        a=0;
        while((!(((tempxfinish-tempxstart)==(variacii[0][a]))&((tempyfinish-tempystart)==(variacii[1][a])))) &(!(((tempxfinish-tempxstart)==(variacii[1][a]))&((tempyfinish-tempystart)==(variacii[0][a]))))){
            //variacii[0][a] variacii[1][a]
            a++;
        }

        if((((tempxfinish-tempxstart)==(variacii[0][a]))&((tempyfinish-tempystart)==(variacii[1][a])))){//значит была построена вертикаль
            a++;
            a=a*13;

        }
        if((((tempxfinish-tempxstart)==(variacii[1][a]))&((tempyfinish-tempystart)==(variacii[0][a])))){//значит была построена горизонталь
            a++;
            a=a*17;
        }

        return a;
    }

    public static boolean canibuildgor(int[][]masscopy, int dlina, int shir, int xc, int yc){
        boolean ucan=true;
        int vac=0;
        boolean peresek = false;
        boolean esc = false;

        if(((xc+shir)>x)||((yc+dlina)>y)){esc=true;}


        for(int i=yc;(i<(yc+dlina));i++)
            for (int j = xc; j<xc+shir; j++) {
                if (masscopy[i][j] == 1000) {
                    vac++;
                }
                if ((masscopy[i][j] == 1)||(masscopy[i][j] == 2)||(masscopy[i][j] == 3)||(masscopy[i][j] == 4)||(masscopy[i][j] == 5)||(masscopy[i][j] == 6)||(masscopy[i][j] == 7)||(masscopy[i][j] == 8)||(masscopy[i][j] == 10)||(masscopy[i][j] == 20)||(masscopy[i][j] == 30)||(masscopy[i][j] == 40)||(masscopy[i][j] == 50)||(masscopy[i][j] == 60)||(masscopy[i][j] == 70)||(masscopy[i][j] == 80)||(masscopy[i][j] == 90)){
                    peresek=true;
                }
            }
        if((vac!=1)||(peresek)||(esc)){ucan=false;}
        return ucan;
    }

    public static boolean canibuildver(int[][]masscopy, int dlina, int shir, int xc, int yc){
        boolean ucan=true;
        int vac=0;
        boolean peresek = false;
        boolean esc = false;

        if(((xc+shir)>x)||((yc+dlina)>y)){esc=true;}

        for(int i=yc;(i<(yc+dlina));i++)
            for (int j = xc; j<xc+shir; j++) {
                if (masscopy[i][j] == 1000) {
                    vac++;
                }
                if ((masscopy[i][j] == 1)||(masscopy[i][j] == 2)||(masscopy[i][j] == 3)||(masscopy[i][j] == 4)||(masscopy[i][j] == 5)||(masscopy[i][j] == 6)||(masscopy[i][j] == 7)||(masscopy[i][j] == 8)||(masscopy[i][j] == 10)||(masscopy[i][j] == 20)||(masscopy[i][j] == 30)||(masscopy[i][j] == 40)||(masscopy[i][j] == 50)||(masscopy[i][j] == 60)||(masscopy[i][j] == 70)||(masscopy[i][j] == 80)||(masscopy[i][j] == 90)){
                    peresek=true;
                }
            }
        if((vac!=1)||(peresek)||(esc)){ucan=false;}
        return ucan;
    }

    public static void printer(int[][] resh){
        int tempxstart=0;
        int tempxfinish=0;
        int tempystart=0;
        int tempyfinish=0;
        int a=0;
        int b=0;
        for(int z=1;z<v+1;z++) {
            while ((resh[a][b] != (z)) & (resh[a][b] != (z * 10))) {
                if (b > x) {
                    b = 0;
                    a++;
                } else {
                    b++;
                }
            }
            tempxstart=b;
            tempystart=a;
            tempxfinish=tempxstart;
            while ((resh[a][b] == (z)) || (resh[a][b] == (z * 10))) {
                tempxfinish++;
                // System.out.println(b);
                b++;

            }
            while ((resh[a][b-1] == (z)) || (resh[a][b-1] == (z * 10))) {

                a++;

            }
            tempyfinish=a;
            //System.out.println(tempystart);
            //System.out.println(tempxstart);
            // System.out.println(tempyfinish);
            // System.out.println(tempxfinish);
            for (int i = tempystart; i < tempyfinish; i++) {
                for (int j = tempxstart; j <tempxfinish; j++) {
                    if((resh[i][j]/z)==1){System.out.print('.');}
                    if((resh[i][j]/z)==10){System.out.print('o');}

                }
                System.out.println();
            }
            System.out.println();
            tempxstart=0;
            tempxfinish=0;
            tempystart=0;
            tempyfinish=0;
            a=0;
            b=0;
        }

    }

    public static boolean isbetter(int[][] masscopy) {
        int a=0;
        int temp=0;
        int temp2=0;

        for(int i=0;i<x;i++){
            if((resh[0][i]==1)||(resh[0][i]==10)){
                temp++;

            }
            if((masscopy[0][i]==1)||(masscopy[0][i]==10)){
                temp2++;
            }
        }
        if(temp2>temp){
            return true;
        }
        else {return false;}
    }

    public static void recursia(int[][] masscopy, int[][] resh, boolean waswrong, int vcount,int[][] variacii, int xc, int yc){
        if(!((xc==0)&(yc==0)&(waswrong))) {
            if (waswrong == true) {
                int asd=watindex(masscopy,vcount,variacii);
                debuilder(masscopy,vcount);

                if((asd%13)==0){
                    asd=asd/13;
                    asd--;
                    while (!(canibuildgor(masscopy,variacii[0][asd],variacii[1][asd],wherefreex(masscopy), wherefreey(masscopy)))&(asd<k))
                    {
                        asd++;
                    }
                    if (asd<k){
                        builder(masscopy,vcount,variacii[0][asd],variacii[1][asd],wherefreex(masscopy), wherefreey(masscopy));
                        recursia(masscopy, resh,false, ++vcount, variacii, wherefreex(masscopy), wherefreey(masscopy));
                    }
                    else {
                        asd=0;
                        while (!(canibuildver(masscopy,variacii[1][asd],variacii[0][asd],wherefreex(masscopy), wherefreey(masscopy)))&(asd<k))
                        {
                            asd++;
                        }
                        if (asd<k){
                            builder(masscopy,vcount,variacii[1][asd],variacii[0][asd],wherefreex(masscopy), wherefreey(masscopy));
                            recursia(masscopy, resh,false, ++vcount, variacii, wherefreex(masscopy), wherefreey(masscopy));
                        }
                        else {
                            recursia(masscopy,resh,true,--vcount,variacii,wherefreex(masscopy), wherefreey(masscopy));
                        }
                    }
                }
                if((asd%17)==0){
                    asd=asd/17;
                    asd--;
                    while (!(canibuildver(masscopy,variacii[1][asd],variacii[0][asd],wherefreex(masscopy), wherefreey(masscopy)))&(asd<k))
                    {
                        asd++;
                    }
                    if (asd<k){
                        builder(masscopy,vcount,variacii[1][asd],variacii[0][asd],wherefreex(masscopy), wherefreey(masscopy));
                        recursia(masscopy, resh,false, ++vcount, variacii, wherefreex(masscopy), wherefreey(masscopy));
                    }
                    else {
                        recursia(masscopy,resh,true,--vcount,variacii,wherefreex(masscopy), wherefreey(masscopy));
                    }
                }


            } else {
                if ((wherefreex(masscopy) == 345)||(wherefreex(masscopy) == 345)) {
                    if (resh[0][0]==547){
                        for (int i = 0; i < y; i++) {
                            for (int j = 0; j <x; j++) {
                                resh[i][j]=masscopy[i][j];

                            }
                        }
                    }
                    else
                    {


                        if(isbetter(masscopy)){
                            for (int i = 0; i < y; i++) {
                                for (int j = 0; j <x; j++) {
                                    resh[i][j]=masscopy[i][j];

                                }
                            }
                        }
                    }
                    return;
                } else {
                    int asd=0;
                    while (!(canibuildgor(masscopy,variacii[0][asd],variacii[1][asd],xc,yc))&(asd<k))
                    {
                        asd++;
                    }
                    if (asd<k){
                        builder(masscopy,vcount,variacii[0][asd],variacii[1][asd],xc,yc);
                        recursia(masscopy, resh,false, ++vcount, variacii, wherefreex(masscopy), wherefreey(masscopy));
                    }
                    else {
                        asd=0;
                        while (!(canibuildver(masscopy,variacii[1][asd],variacii[0][asd],xc,yc))&(asd<k))
                        {
                            asd++;
                        }
                        if (asd<k){
                            builder(masscopy,vcount,variacii[1][asd],variacii[0][asd],xc,yc);
                            recursia(masscopy, resh,false, ++vcount, variacii, wherefreex(masscopy), wherefreey(masscopy));
                        }
                        else {
                            recursia(masscopy, resh,true, --vcount, variacii, xc, yc);
                        }
                    }
                }
            }
        }
        else {return;}
    }

}
