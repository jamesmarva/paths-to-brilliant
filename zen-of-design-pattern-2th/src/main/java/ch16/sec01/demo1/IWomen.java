package ch16.sec01.demo1;

/**
 *
 * 古代女性接口，假设是生活在 “三从四德” 的时代
 * @author 王涵威
 * @date 20.12.5 19:29
 */
public interface IWomen {

    /**
     * 获取个人的情况
     * @return 状况
     */
    int getType();


    /**
     * 获得个人请示，逛街？约会？看电影？
     * @return 请示结果
     *
     */
    String getRequest();
}
