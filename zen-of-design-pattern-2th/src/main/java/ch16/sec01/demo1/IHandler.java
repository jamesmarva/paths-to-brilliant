package ch16.sec01.demo1;

/**
 * 有处理权的人的接口
 *
 * @author 王涵威
 * @date 20.12.5 19:47
 */
public interface IHandler {

    /**
     * 当一个女性需要询问自己动作的时候，就要处理这个请求。
     *
     * @param women 古时候女性
     */
    void handleMessage(IWomen women);
}
