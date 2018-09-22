package in.dragonbra.bptfserver;

import in.dragonbra.bptfserver.scheduled.BptfScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BptfServerApplication implements CommandLineRunner {

    private final BptfScheduledTasks bptfScheduledTasks;

    @Autowired
    public BptfServerApplication(BptfScheduledTasks bptfScheduledTasks) {
        this.bptfScheduledTasks = bptfScheduledTasks;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BptfServerApplication.class, args);

        if (args.length > 0) {
            ctx.close();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            for (String arg : args) {
                switch (arg) {
                    case "getschema":
                        bptfScheduledTasks.downloadItemSchema();
                        break;
                    case "getprices":
                        bptfScheduledTasks.downloadPrices();
                        break;
                }
            }
        }
    }
}
