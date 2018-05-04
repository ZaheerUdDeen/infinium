/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;




import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author zephyr
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ListView<HBox> dortorList;
    @FXML
    private TextField docID;
    @FXML
    private TextField doctorIDDelete;
     WebTarget target;
    @FXML
    private TextField docContact;
    @FXML
    private AnchorPane pharmacyPanel;
    @FXML
    private AnchorPane patientPanel;
    @FXML
    private AnchorPane labPanel;
    @FXML
    private AnchorPane hospitalPanel;
    @FXML
    private TextField pid;
    @FXML
    private TextField drugName;
    @FXML
    private TextField formulae;
    @FXML
    private TextField mfg;
    @FXML
    private TextField dosageForm;
    @FXML
    private TextField drugDetails;
    @FXML
    private ListView<HBox> prsListView;
    private void post(String endPoint,JSONObject json){
        target=client.target("http://localhost:3000/api/"+endPoint);
        System.out.println("here"+json.toString());
        target.request(MediaType.APPLICATION_JSON).post(Entity.json(json));
        MakeDoctorrListView();
    }
    Client client;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        client =ClientBuilder.newClient();
        MakeDoctorrListView();
        MakePresciptionListView();
    }    

    @FXML
    private void addDoctors(ActionEvent event) {
        
         JSONObject json = new JSONObject();
         json.put("$class", "org.acme.Doctor");
         json.put("doctorID", docID.getText().toString());
         json.put("contact", docContact.getText().toString());
         
         JSONArray jArray=new JSONArray();
         JSONObject scedule = new JSONObject();
          
         scedule.put("$class","org.acme.DoctorSchedule");
         scedule.put("AM12to1",true);
         scedule.put("AM1to2",true);
         scedule.put("AM2to3",true);
         scedule.put("AM3to4",true);
         scedule.put("AM4to5",true);
         scedule.put("AM5to6",true);
         scedule.put("AM6to7",true);
         scedule.put("AM7to8",true);
         scedule.put("AM8to9",true);
         scedule.put("AM9to10",true);
         scedule.put("AM10to11",true);
         scedule.put("AM11to12",true);
         scedule.put("PM12to1",true);
         scedule.put("PM1to2",true);
         scedule.put("PM2to3",true);
         scedule.put("PM3to4",true);
         scedule.put("PM4to5",true);
         scedule.put("PM5to6",true);
         scedule.put("PM6to7",true);
         scedule.put("PM7to8",true);
         scedule.put("PM8to9",true);
         scedule.put("PM9to10",true);
         scedule.put("PM10to11",true);
         scedule.put("PM11to12",true);
         
         scedule.put("id","1");
         
         jArray.add(json);
         json.put("doctorSchedule", scedule);
         post("Doctor",json);
        
    }
    
    
    
  private void MakeDoctorrListView(){
        dortorList.getItems().clear();
        target=client.target("http://localhost:3000/api/Doctor");
        String traders=target.request(MediaType.APPLICATION_JSON).get(String.class);
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(traders);
            
            for(int i=0;i<jsonArray.size();i++){
                JSONObject json1=(JSONObject) jsonArray.get(i);
               
            
        VBox vb1=new VBox();
        Label drID=new Label("Doctor ID: ");
        Label drCt=new Label("Contact# ");
        
        
        vb1.getChildren().add(drID);
        vb1.getChildren().add(drCt);
       
        
        VBox vb=new VBox();
        Label dID=new Label(json1.get("doctorID").toString());
        Label dCN=new Label(json1.get("contact").toString());
       
        
        
        
        vb.getChildren().add(dID);
        vb.getChildren().add(dCN);
        
        HBox hb=new HBox();
         hb.getChildren().add(vb1);
        hb.getChildren().add(vb);
        
        dortorList.getItems().add(hb);
            
        }
            
        } catch (ParseException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    
    @FXML
    private void deleteDoctor(ActionEvent event) {
    }

    @FXML
    private void loadPharmacy(Event event) {
        
        try {
                pharmacyPanel.getChildren().clear();
               
                pharmacyPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/pharmacy.fxml")));
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }        
                
    }

    @FXML
    private void loadPatient(Event event) {
        try {
                patientPanel.getChildren().clear();
                
                patientPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/patient.fxml")));
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }

    @FXML
    private void loadLabs(Event event) {
        try {
                labPanel.getChildren().clear();
                
                labPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/lab.fxml")));
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }

    @FXML
    private void loadHospital(Event event) {
        try {
                hospitalPanel.getChildren().clear();
               
                hospitalPanel.getChildren().add(FXMLLoader.load(getClass().getResource("/view/hospital.fxml")));
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
private void MakePresciptionListView(){
        prsListView.getItems().clear();
        target=client.target("http://localhost:3000/api/TreatmentDrugs");
        String traders=target.request(MediaType.APPLICATION_JSON).get(String.class);
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(traders);
            
            for(int i=0;i<jsonArray.size();i++){
                JSONObject json1=(JSONObject) jsonArray.get(i);
               
            
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
        
        prsListView.getItems().add(hb);
            
        }
            
        } catch (ParseException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    @FXML
    private void addPrescription(ActionEvent event) {
        JSONObject json = new JSONObject();
         json.put("$class", "org.acme.TreatmentDrugs");
         
         json.put("treatmentDrugsID", pid.getText().toString());
         json.put("drugName", drugName.getText().toString());
         
         json.put("formulae", formulae.getText().toString());
         json.put("mfg", mfg.getText().toString());
         json.put("dosageForm", dosageForm.getText().toString());
         json.put("drugDetail", drugDetails.getText().toString());
         
         
        post("TreatmentDrugs",json);
        MakePresciptionListView();
    }
    
}
