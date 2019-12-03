package org.ruixun;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suitao
 * @description
 */
public class Hello {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("aaa.jpg");
        strings.add("bbb.jpg");
        strings.forEach(s -> {
            File file = new File("aaa.jpg");
            file.delete();
        });
    }

}
