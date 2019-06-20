package kdg.be.simulator.generator;

import kdg.be.simulator.controller.GeneratorListener;
import kdg.be.simulator.fileReader.CSVReader;
import kdg.be.simulator.models.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static kdg.be.simulator.fileReader.CSVReader.parseLine;

@Component
@ConditionalOnProperty(name = "MessageGeneratorType", havingValue = "file")
public class FileMessageGenerator implements MessageGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileMessageGenerator.class);

    @Value("${filePath:.//src//main//resources//textfiles//overtredingen.csv}")
    private String filePath;
    private LinkedList<GeneratorListener> listeners;


    public FileMessageGenerator() {
        listeners = new LinkedList<>();
    }

    @Override
    public void generate() throws InterruptedException {
        Scanner scanner = null;
        do {
            if (scanner == null) {
                try {
                    scanner = new Scanner(new File(filePath));
                } catch (FileNotFoundException e) {
                    LOGGER.error(e.getMessage());
                }
            }

            if (scanner != null) {
                List<String> line = parseLine(scanner.nextLine());
                if (line != null) {
                    if (line.get(1).matches("^[0-9]-[A-Z]{3}-[0-9]{3}")) {

                        CameraMessage cm = new CameraMessage(Integer.parseInt(line.get(0)), line.get(1),
                                LocalDateTime.now().plusSeconds(Long.parseLong(line.get(2)) / 1000));

                        if (Long.parseLong(line.get(2)) <= 0) {
                            Thread.sleep(1);

                        } else {
                            Thread.sleep(Duration.between(LocalDateTime.now(), cm.getTimestamp()).toMillis());
                        }
                        notifyListeners(cm);
                    } else {
                        LOGGER.error(line.get(1) + "Is not a valid licenseplate. LicensePlate must match ^[0-9]-[A-" +
                                "Z]{3}-[0-9]{3}" + " Check your csv file.");
                    }
                }
            } else {
                LOGGER.error("scanner is null: " + getClass().getSimpleName());
            }

        } while (Objects.requireNonNull(scanner).hasNext());
    }


    @Override
    public void addListener(GeneratorListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyListeners(CameraMessage message) {
        listeners.forEach(listener -> listener.onMessageGenerated(message));

    }


}



