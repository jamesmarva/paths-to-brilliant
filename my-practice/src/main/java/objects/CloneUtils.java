package objects;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.2 13:40
 */
public class CloneUtils {

    public static final Logger log = LoggerFactory.getLogger(CloneUtils.class);

    public static <T> T clone (T obj) {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        ) {
            objOut.writeObject(obj);
            System.out.println(byteOut.size());
            try (ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                 ObjectInputStream ojbIn = new ObjectInputStream(byteIn);)  {
                return (T) ojbIn.readObject();
            }
        } catch (Exception e) {
            System.out.println(e);
            log.error("Catch exception from clone:", e);
        }
        return null;
    }
}
