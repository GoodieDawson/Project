/**
 * 
 */
package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author Goodie Blake Dawson
 *
 */
public class Main_Menu_Controller {

    @FXML
    void start(ActionEvent event) {
    	MonitoringIO.start();
    }

}
