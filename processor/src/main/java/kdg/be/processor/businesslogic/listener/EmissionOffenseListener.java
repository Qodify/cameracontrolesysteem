package kdg.be.processor.businesslogic.listener;

import kdg.be.processor.businesslogic.adapter.CameraServiceAdapter;
import kdg.be.processor.businesslogic.adapter.LicensePlateServiceAdapter;
import kdg.be.processor.businesslogic.service.EmissionOffenseService;
import kdg.be.processor.domain.cameramessage.CameraMessage;
import kdg.be.processor.domain.offense.Offense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Optional;

@Component
public class EmissionOffenseListener implements OffenseListener {

    private LicensePlateServiceAdapter licensePlateServiceAdapter;
    private CameraServiceAdapter cameraServiceAdapter;
    private final EmissionOffenseService emissionOffenseService;
    private AbstractMap<CameraMessage, Integer> retryMap;
    @Value("${filePath}")
    private String retryfilepath;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmissionOffenseListener.class);


    @Autowired
    public EmissionOffenseListener(LicensePlateServiceAdapter licensePlateServiceAdapter,
                                   CameraServiceAdapter cameraServiceAdapter,
                                   EmissionOffenseService emissionOffenseService) {
        this.licensePlateServiceAdapter = licensePlateServiceAdapter;
        this.cameraServiceAdapter = cameraServiceAdapter;
        this.emissionOffenseService = emissionOffenseService;
        retryMap = new HashMap<>();
    }

    @Override
    public Optional<Offense> onMessageReceived(CameraMessage cm) {

        try {
            return emissionOffenseService.calcOffense(licensePlateServiceAdapter.get(cm.getLicenseplate()),
                    cameraServiceAdapter.get(cm.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }


    @Scheduled(fixedRate = 10000)
    void retryFailedCameraMessages() {
        FileWriter fw;
        if (!retryMap.isEmpty()) {
            for (CameraMessage cm : retryMap.keySet()) {
                if (cm.getRetries() > 1) {
                    try {
                        LOGGER.info("write failed msg to file!: " + cm.toString());
                        fw = new FileWriter(retryfilepath);
                        fw.write(cm.toString() + '\n');
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    retryMap.remove(cm);
                } else {
                    onMessageReceived(cm);
                    cm.retry();
                    retryMap.replace(cm, cm.getRetries());
                }
            }
        }
    }

}
