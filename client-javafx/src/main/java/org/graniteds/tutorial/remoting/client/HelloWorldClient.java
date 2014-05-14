package org.graniteds.tutorial.remoting.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.granite.client.javafx.tide.JavaFXApplication;
import org.granite.client.tide.Context;
import org.granite.client.tide.impl.ComponentImpl;
import org.granite.client.tide.impl.SimpleContextManager;
import org.granite.client.tide.server.Component;
import org.granite.client.tide.server.ServerSession;
import org.granite.client.tide.server.TideFaultEvent;
import org.granite.client.tide.server.TideResponder;
import org.granite.client.tide.server.TideResultEvent;


public class HelloWorldClient extends Application {

    /**
     * Main method which lauches the JavaFX application
     */
    public static void main(String[] args) {
        Application.launch(HelloWorldClient.class, args);
    }

    // tag::client-setup[]
    private static Context context;

    @Override
    public void start(Stage stage) throws Exception {
    	context = new SimpleContextManager(new JavaFXApplication(this, stage)).getContext(); // <1>
    	
        final ServerSession serverSession = context.set(
                new ServerSession("/helloworld", "localhost", 8080)); // <2>
        final Component helloWorldService = context.set("helloWorldService",
                new ComponentImpl(serverSession)); // <3>

        serverSession.start(); // <4>
        // end::client-setup[]

        // tag::client-ui[]
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 10, 25, 10));

        Text title = new Text("Hello Example");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title, 0, 0, 2, 1);

        grid.add(new Label("Enter your name:"), 0, 1);

        final TextField nameField = new TextField();
        grid.add(nameField, 1, 1);

        final Button sendButton = new Button("Send");
        grid.add(sendButton, 2, 1);

        final Label resultLabel = new Label();
        grid.add(resultLabel, 0, 2, 2, 1);

        Scene scene = new Scene(grid, 350, 155);
        stage.setTitle("GraniteDS Remoting Tutorial");
        stage.setScene(scene);
        stage.show();
        // end::client-ui[]
        
        // tag::client-call[]
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
	            helloWorldService.call("hello", nameField.getText(), // <1>
                    new TideResponder<String>() { // <2>
						@Override
						public void result(TideResultEvent<String> tre) { // <3>
	                		resultLabel.setText(tre.getResult());
	                		nameField.setText(null);
						}
						@Override
						public void fault(TideFaultEvent tfe) { // <4>
                            System.err.println("-----------------------------");
                            System.err.println("Fault: " + tfe.getFault().getCode() + ": "
                                    + tfe.getFault().getFaultDescription());
                            System.err.println("-----------------------------");
						}
	            	}
                );
			}
        });
        
        nameField.setOnAction(sendButton.getOnAction());
        // end::client-call[]
    }
    
    // tag::client-close[]
    @Override
    public void stop() throws Exception {
        context.byType(ServerSession.class).stop();
    }
    // end::client-close[]
}
