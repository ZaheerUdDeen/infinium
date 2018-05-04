/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author Zephyr
 */
public class PharmacyController implements Initializable {

    @FXML
    private TextField pID;
    @FXML
    private ListView<HBox> pidSearch;
    Client client;
    WebTarget target;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         client =ClientBuilder.newClient();
       
    }    
private void MakePresciptionListView(String ID){
        pidSearch.getItems().clear();
        target=client.target("http://localhost:3000/api/TreatmentDrugs/"+ID);
        String pDetails=target.request(MediaType.APPLICATION_JSON).get(String.class);
        JSONParser parser = new JSONParser();
        
         JSONObject json1;
        try {
            
            json1 = (JSONObject) parser.parse(pDetails);
       
            VBox vb1=new VBox();
            Label drID=new Label("Prescription ID: ");
            Label drName=new Label("Name: ");
            Label drF=new Label("Fromulae: ");
            Label drMg=new Label("Mg ");
            Label drDf=new Label("Dosage Form ");
            Label drDd=new Label("Details: ");


            vb1.getChildren().add(drID);
            vb1.getChildren().add(drName);
            vb1.getChildren().add(drF);
            vb1.getChildren().add(drMg);
            vb1.getChildren().add(drDf);
            vb1.getChildren().add(drDd);


            VBox vb=new VBox();
            Label dID=new Label(json1.get("treatmentDrugsID").toString());
            Label dN=new Label(json1.get("drugName").toString());
            Label dF=new Label(json1.get("formulae").toString());
            Label dMg=new Label(json1.get("mfg").toString());
            Label dDf=new Label(json1.get("dosageForm").toString());
            Label dD=new Label(json1.get("drugDetail").toString());




            vb.getChildren().add(dID);
            vb.getChildren().add(dN);
            vb.getChildren().add(dF);
            vb.getChildren().add(dMg);
            vb.getChildren().add(dDf);
            vb.getChildren().add(dD);


            HBox hb=new HBox();
             hb.getChildren().add(vb1);
            hb.getChildren().add(vb);

            pidSearch.getItems().add(hb);
            
        
            
        } catch (ParseException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    @FXML
    private void searchForPID(ActionEvent event) {
        
         MakePresciptionListView(pID.getText().toString());
    }
    
}
