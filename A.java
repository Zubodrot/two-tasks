import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        System.out.println("Чумаков Дмитрий");
        boolean b = true;
        int x=1;
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String strArr[] = str.split(" ");
        int numArr[] = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            numArr[i] = Integer.parseInt(strArr[i]);
        }
        while (b!=false) {
            b=false;
            for (int i=0;i<numArr.length;i++){
                if (x==numArr[i]){
                    b=true;
                }
            }
            x++;
        }
        System.out.println(x-1);

    }
}

