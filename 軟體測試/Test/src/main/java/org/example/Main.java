package org.example;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello, World!");
    }

    public int add(int x, int y) {
        if(y>=0) {
            for(int i=0; i<y; i++) {
                x+=1;
            }
        }
        else {
            for(int i=0; i>y; i--) {
                x-=1;
            }
        }
        return x;
    }

    public int add(int[] data) {
        int cnt = 0;
        for(int i=0; i<data.length; i++) {
            cnt += data[i];
        }
        return cnt;
    }

    public int div(int[] data1, int[] data2) {
        int data3 = 0;
        for(int i=0; i<data1.length; i++) {
            data3 += data1[i] / data2[i];
        }
        return data3;
    }
}