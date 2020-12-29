package ch16.sec01.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.5 19:59
 */
public class Sec01Demo1Client {

    public static void main(String[] args) {
        //
        Random random = new Random();
        List<IWomen> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(new Women(random.nextInt(3), "Shopping"));
        }

        IHandler father = new Father();

        IHandler husband = new Husband();

        IHandler son = new Son();

        for (IWomen women : list) {
            if (women.getType() == Women.State.NOT_MARRIED) {
                System.out.println("\n---------- daught request father -----------");
                father.handleMessage(women);
            } else if (women.getType() == Women.State.MARRIED) {
                System.out.println("\n---------- wife request husband ----------- ");
                husband.handleMessage(women);
            } else if (women.getType() == Women.State.HUSBAND_DEAD) {
                System.out.println("\n----------  mother request son ------------");
                son.handleMessage(women);
            } else {

            }
        }
    }
}
