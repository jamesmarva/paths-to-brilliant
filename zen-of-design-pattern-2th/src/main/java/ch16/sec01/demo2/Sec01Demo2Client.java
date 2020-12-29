package ch16.sec01.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.5 21:50
 */
public class Sec01Demo2Client {


    public static void main(String[] args) {
        Random random = new Random();

        List<IWomen> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(new Women(1 + random.nextInt(3), "Shopping"));
        }

        Handler father = new FatherHandler();
        Handler husband = new HusbandHandler();
        Handler son = new SonHandler();

        father.setNext(husband);
        husband.setNext(son);

        for (IWomen women : list) {
            father.handleMessage(women);
        }
    }
}
