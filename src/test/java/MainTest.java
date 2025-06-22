import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;
@Disabled("При желании можно запустить в ручную")
class MainTest {

    @Test
    @Timeout(value=22,unit= TimeUnit.SECONDS)
    void mainTest_fail_if_xecution_time_more_22_second() throws Exception {
        Main.main(new String[]{});
    }
}