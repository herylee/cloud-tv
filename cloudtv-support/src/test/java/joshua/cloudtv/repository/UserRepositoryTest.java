package joshua.cloudtv.repository;


import joshua.cloudtv.repository.UserRepository;
import joshua.cloudtv.utils.JsonKit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring-mybaits.xml"})
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("one", "one");
        map.put("two", "two");
        list.add(map);

        JsonKit.printJson(list);
    }
}
