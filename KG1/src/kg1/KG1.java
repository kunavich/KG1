/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kg1;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author Lord
 */


public class KG1 extends Application {
    
    private String[] s={"CMYK","RGB","HSV"};
    private int i=1;
   
    
    private void init(Stage primaryStage) {
        Group root = new Group();
        
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(500);
        final ColorPicker colorPicker = new ColorPicker(Color.GRAY);
        ToolBar standardToolbar = ToolBarBuilder.create().items(colorPicker).build();

        
        final Button CB1 = new Button(s[0]);
        final Button CB2 = new Button(s[1]);
        final Button CB3 = new Button(s[2]);
        Color c = colorPicker.getValue();
        double red=c.getRed();
        double green=c.getGreen();
        double blau=c.getBlue();
        final Text coloredText = new Text("RGB\n "+red*255+"\n "+green*255+"\n "+blau*255);
        
        Font font = new Font(20);
        coloredText.setFont(font);
        
        CB3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) 
            {
                Color newColor = colorPicker.getValue();
                                      
                double red=newColor.getRed();
                double green=newColor.getGreen();
                double blau=newColor.getBlue();
                
                coloredText.setText(my_func(red,green,blau,2));
            
            }
           
       });
       CB2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) 
            {
                Color newColor = colorPicker.getValue();
                                      
                double red=newColor.getRed();
                double green=newColor.getGreen();
                double blau=newColor.getBlue();
                
                coloredText.setText(my_func(red,green,blau,1));
            
            }
           
       });

        
       CB1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) 
            {
                Color newColor = colorPicker.getValue();
                                      
                double red=newColor.getRed();
                double green=newColor.getGreen();
                double blau=newColor.getBlue();
                
                coloredText.setText(my_func(red,green,blau,0));
            
            }
           
       });

        colorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
                Color newColor = colorPicker.getValue();
                                      
                double red=newColor.getRed();
                double green=newColor.getGreen();
                double blau=newColor.getBlue();
                
                coloredText.setText(my_func(red,green,blau,0));
            }
        });

        VBox coloredObjectsVBox = VBoxBuilder.create().alignment(Pos.CENTER).spacing(20).children(coloredText, CB1,CB2,CB3).build();        
        VBox outerVBox = VBoxBuilder.create().alignment(Pos.CENTER).spacing(150).padding(new Insets(0, 0, 120, 0)).children(standardToolbar, coloredObjectsVBox).build();
        root.getChildren().add(outerVBox);
    }

    private String createRGBString(Color c) {
        return "-fx-base: rgb(" + (c.getRed() * 255) + "," + (c.getGreen() * 255) + "," + (c.getBlue() * 255) + ");";
    }

    private String my_func(double r,double g,double b,int i)
    {
        
        if(i==0)
        {
            double k;
            double c;
            double y;
            double m;
            k=Math.min(r, Math.min(g,b));
            
            c=(1-r-k)/(1-k);
            m=(1-g-k)/(1-k);
            y=(1-b-k)/(1-k);
            String s="";
            if(c<0)
                s+= ("error to low caen\n"+c+"\n");
            if(m<0)
                s+=("error to low magenta\n"+m+"\n");
            if(y<0)
                s+= ("error to low yellow\n"+y+"\n");
            if(s.equals(""))
                return("CMYK\n "+c+"\n "+m+"\n "+y+"\n "+k);
            return s;
            //CMYK 
        }
        if(i==1)
        {
            //RGB 
            return("RGB\n "+r*255+"\n "+g*255+"\n "+b*255);
        }
        if(i==2)
        {
            
            //HSV
            double max=Math.max(r, Math.max(g,b));
            double min=Math.min(r, Math.min(g,b));
            double h=0;
            double s=0;
            double v=max;
            if(max==0)
            {
                s=0;
            }
            else
            {
                s=(max-min)/max;
            }
           if(max==0)
           {
               h=0;
           }
           else if(max==r)
           {
               h=((g-b)/(max-min))%6*60;
           }
           else if(max==g)
           {
               h=((b-r)/(max-min) +2)*60;
           }
           else if(max==b)
           {
               h=((r-g)/(max-min) +4)*60;
           }
           
           return("HSV\n "+h+"\n "+s+"\n "+v);
        }
        return("RGB\n "+r*255+"\n "+g*255+"\n "+b*255);
    }
    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
