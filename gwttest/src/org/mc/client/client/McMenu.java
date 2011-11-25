package org.mc.client.client;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class McMenu {
	private Command command2 = new Command() {
		   public void execute() {
			   final DialogBox dbox = new DialogBox();
			   VerticalPanel dialogBoxContents = new VerticalPanel();
			   HorizontalPanel hpanel = new HorizontalPanel();
			   
			   final Button select = new Button("Select");
			   final Button cancel = new Button("Cancel");
			   final Label messageLabel = new Label();
			   messageLabel.setText("Query in progress ...");

			   final ListBox listbox = new ListBox(false);
			   listbox.setWidth("100%");
			   ConfiguratorServiceAsync stockPriceSvc = GWT.create(ConfiguratorService.class);
			   AsyncCallback<Collection<String>> callback = new AsyncCallback<Collection<String>>() {
				      public void onFailure(Throwable caught) {
				    	  caught.printStackTrace();
				    	  cancel.setEnabled(true);
				    	  messageLabel.setText("Query error");
				      }

				      public void onSuccess(Collection<String> result) {
				    	  for(String registry: result) {
				    		 listbox.addItem(registry);
				    	  }
				    	  select.setEnabled(true);
				    	  cancel.setEnabled(true);
				    	  messageLabel.setText("Select Registry:");
				      }
				    };
//				    stockPriceSvc.getAllRegistrysForUser(username, callback);
		   
			   listbox.setVisibleItemCount(10);
			   
			   select.addClickHandler(new ClickHandler() {
				   @Override
					public void onClick(ClickEvent event) {
					    dbox.hide();
//					    updateSourceContainers(listbox.getValue(listbox.getSelectedIndex()));
					}
			   });
			   
			   
			   cancel.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dbox.hide();
					}
			   });
			   
			   select.setEnabled(false);
			   cancel.setEnabled(false);
			   hpanel.add(select);
			   hpanel.add(cancel);
			   
			   dialogBoxContents.add(messageLabel);
			   dialogBoxContents.add(listbox);
			   dialogBoxContents.add(hpanel);
			   
			   dbox.setWidget(dialogBoxContents);
			   dbox.center();
		   }
		};

}